package com.zhoouon.encrypt.pgp;

import com.sun.xml.wss.impl.misc.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bouncycastle.bcpg.ArmoredOutputStream;
import org.bouncycastle.bcpg.CompressionAlgorithmTags;
import org.bouncycastle.bcpg.HashAlgorithmTags;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openpgp.PGPCompressedData;
import org.bouncycastle.openpgp.PGPCompressedDataGenerator;
import org.bouncycastle.openpgp.PGPEncryptedData;
import org.bouncycastle.openpgp.PGPEncryptedDataGenerator;
import org.bouncycastle.openpgp.PGPEncryptedDataList;
import org.bouncycastle.openpgp.PGPException;
import org.bouncycastle.openpgp.PGPLiteralData;
import org.bouncycastle.openpgp.PGPObjectFactory;
import org.bouncycastle.openpgp.PGPOnePassSignature;
import org.bouncycastle.openpgp.PGPOnePassSignatureList;
import org.bouncycastle.openpgp.PGPPrivateKey;
import org.bouncycastle.openpgp.PGPPublicKey;
import org.bouncycastle.openpgp.PGPPublicKeyEncryptedData;
import org.bouncycastle.openpgp.PGPPublicKeyRing;
import org.bouncycastle.openpgp.PGPPublicKeyRingCollection;
import org.bouncycastle.openpgp.PGPSecretKey;
import org.bouncycastle.openpgp.PGPSecretKeyRing;
import org.bouncycastle.openpgp.PGPSecretKeyRingCollection;
import org.bouncycastle.openpgp.PGPSignature;
import org.bouncycastle.openpgp.PGPSignatureList;
import org.bouncycastle.openpgp.PGPUtil;
import org.bouncycastle.openpgp.jcajce.JcaPGPObjectFactory;
import org.bouncycastle.openpgp.operator.PGPDigestCalculator;
import org.bouncycastle.openpgp.operator.bc.BcKeyFingerprintCalculator;
import org.bouncycastle.openpgp.operator.bc.BcPGPContentVerifierBuilderProvider;
import org.bouncycastle.openpgp.operator.bc.BcPublicKeyDataDecryptorFactory;
import org.bouncycastle.openpgp.operator.jcajce.JcaKeyFingerprintCalculator;
import org.bouncycastle.openpgp.operator.jcajce.JcaPGPContentSignerBuilder;
import org.bouncycastle.openpgp.operator.jcajce.JcaPGPDigestCalculatorProviderBuilder;
import org.bouncycastle.openpgp.operator.jcajce.JcaPGPKeyPair;
import org.bouncycastle.openpgp.operator.jcajce.JcePBESecretKeyDecryptorBuilder;
import org.bouncycastle.openpgp.operator.jcajce.JcePBESecretKeyEncryptorBuilder;
import org.bouncycastle.openpgp.operator.jcajce.JcePGPDataEncryptorBuilder;
import org.bouncycastle.openpgp.operator.jcajce.JcePublicKeyKeyEncryptionMethodGenerator;
import org.bouncycastle.util.io.Streams;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Date;
import java.util.Iterator;

/**
 * @Author: zhoudong
 * @Description: PGP加密解密方式
 * @Date: 2024/7/26 14:25
 * @Version: 1.0.0
 **/
public class PGPUtils {

    private static final String PROVIDER_BC = "BC";
    private static final String RSA = "RSA";
    private static final Log log = LogFactory.getLog(PGPUtils.class);

    static {
        if (Security.getProvider(PROVIDER_BC) == null) {
            Security.addProvider(new BouncyCastleProvider());
        }
    }

    /**
     * 生成PGP密钥对
     *
     * @param keySize 生成多少位的密钥，如2048
     * @param passPhrase 私钥密码，可为空
     * @param identity 公钥的userId
     * @param pubKeyFile 公钥文件目录
     * @param priKeyFile 私钥文件目录
     * @param path 文件目录
     * @throws Exception
     */
    public static void generatePGPKeyPair(int keySize, String passPhrase, String identity, String pubKeyFile, String priKeyFile, String path) throws Exception {
        createDirectory(path);
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSA, PROVIDER_BC);
        keyPairGenerator.initialize(keySize);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        OutputStream priOs = null;
        OutputStream pubOs = null;
        try {
            // key format without armored
            priOs = new FileOutputStream(priKeyFile);
            pubOs = new FileOutputStream(pubKeyFile);

            // the pass phrase to open private key
            char[] passPhrase_ = passPhrase.toCharArray();
            // Hash algorithm using SHA1 as certificate
            PGPDigestCalculator sha1Calc = new JcaPGPDigestCalculatorProviderBuilder().build().get(HashAlgorithmTags.SHA1);
            // Generate RSA key pair
            JcaPGPKeyPair jcaPGPKeyPair = new JcaPGPKeyPair(PGPPublicKey.RSA_GENERAL, keyPair, new Date());
            PGPSecretKey pgpSecretKey = new PGPSecretKey(PGPSignature.DEFAULT_CERTIFICATION,
                    jcaPGPKeyPair,
                    identity,
                    sha1Calc,
                    null,
                    null,
                    new JcaPGPContentSignerBuilder(jcaPGPKeyPair.getPublicKey().getAlgorithm(), HashAlgorithmTags.SHA1),
                    new JcePBESecretKeyEncryptorBuilder(PGPEncryptedData.CAST5, sha1Calc).setProvider(PROVIDER_BC).build(passPhrase_));
            String privateKeyBase64Str = Base64.encode(pgpSecretKey.getEncoded());
            System.out.println("PGP密钥Base64串：\r\n" + privateKeyBase64Str);
            priOs.write(privateKeyBase64Str.getBytes());

            PGPPublicKey publicKey = pgpSecretKey.getPublicKey();
            String publicKeyBase64Str = Base64.encode(publicKey.getEncoded());
            System.out.println("PGP公钥Base64串：\r\n" + publicKeyBase64Str);
            System.out.println("PGP公钥信息：\r\n" + publicKey.getKeyID());
            System.out.println("PGP公钥信息：\r\n" + publicKey.getUserIDs().next());
            System.out.println("PGP公钥信息：\r\n" + publicKey.getValidSeconds());
            pubOs.write(publicKeyBase64Str.getBytes());
        } catch (Exception e) {
            log.error("生成密钥失败", e);
        } finally {
            IOUtils.closeQuietly(priOs);
            IOUtils.closeQuietly(pubOs);
        }
    }

    public static void createDirectory(String path) throws Exception {
        File file = new File(path);
        if (!file.exists() && !file.isDirectory()) {
            file.mkdirs();
        }
    }

    public static byte[] compressFile(String fileName, int algorithm) throws IOException {
        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        PGPCompressedDataGenerator comData = new PGPCompressedDataGenerator(algorithm);
        PGPUtil.writeFileToLiteralData(comData.open(bOut), PGPLiteralData.BINARY, new File(fileName));
        comData.close();

        return bOut.toByteArray();
    }

    /**
     * Search a secret key ring collection for a secret key corresponding to keyID if it
     * <p>
     * exists.
     *
     * @param pgpSec a secret key ring collection.
     * @param keyID? keyID we want.
     * @param pass?  passphrase to decrypt secret key with.
     * @return the private key.
     * @throws PGPException
     */
    public static PGPPrivateKey findSecretKey(PGPSecretKeyRingCollection pgpSec, long keyID, char[] pass)
            throws PGPException {
        PGPSecretKey pgpSecKey = pgpSec.getSecretKey(keyID);
        if (pgpSecKey == null) {
            return null;
        }

        return pgpSecKey.extractPrivateKey(new JcePBESecretKeyDecryptorBuilder().setProvider("BC").build(pass));
    }

    public static PGPPublicKey readPublicKey(String fileName) throws IOException, PGPException {
        InputStream keyIn = new BufferedInputStream(new FileInputStream(fileName));
        PGPPublicKey pubKey = readPublicKey(keyIn);
        keyIn.close();

        return pubKey;
    }

    /**
     * A simple routine that opens a key ring file and loads the first available key
     * <p>
     * suitable for encryption.
     *
     * @param input data stream containing the public key data
     * @return the first public key found.
     * @throws IOException
     * @throws PGPException
     */
    public static PGPPublicKey readPublicKey(InputStream input) throws IOException, PGPException {
        PGPPublicKeyRingCollection pgpPub = new PGPPublicKeyRingCollection(
                PGPUtil.getDecoderStream(input), new JcaKeyFingerprintCalculator());

// we just loop through the collection till we find a key suitable for encryption, in the real
// world you would probably want to be a bit smarter about this.

        Iterator keyRingIter = pgpPub.getKeyRings();
        while (keyRingIter.hasNext()) {
            PGPPublicKeyRing keyRing = (PGPPublicKeyRing) keyRingIter.next();
            Iterator keyIter = keyRing.getPublicKeys();
            while (keyIter.hasNext()) {
                PGPPublicKey key = (PGPPublicKey) keyIter.next();
                if (key.isEncryptionKey()) {
                    return key;
                }
            }
        }

        throw new IllegalArgumentException("Can't find encryption key in key ring.");
    }

    public static PGPSecretKey readSecretKey(String fileName) throws IOException, PGPException {
        InputStream keyIn = new BufferedInputStream(new FileInputStream(fileName));
        PGPSecretKey secKey = readSecretKey(keyIn);
        keyIn.close();

        return secKey;
    }

    /**
     * A simple routine that opens a key ring file and loads the first available key
     * <p>
     * suitable for signature generation.
     *
     * @param input stream to read the secret key ring collection from.
     * @return a secret key.
     * @throws IOException? on a problem with using the input stream.
     * @throws PGPException if there is an issue parsing the input stream.
     */
    public static PGPSecretKey readSecretKey(InputStream input) throws IOException, PGPException {
        PGPSecretKeyRingCollection pgpSec = new PGPSecretKeyRingCollection(
                PGPUtil.getDecoderStream(input), new JcaKeyFingerprintCalculator());

// we just loop through the collection till we find a key suitable for encryption, in the real
// world you would probably want to be a bit smarter about this.

        Iterator keyRingIter = pgpSec.getKeyRings();
        while (keyRingIter.hasNext()) {
            PGPSecretKeyRing keyRing = (PGPSecretKeyRing) keyRingIter.next();
            Iterator keyIter = keyRing.getSecretKeys();
            while (keyIter.hasNext()) {
                PGPSecretKey key = (PGPSecretKey) keyIter.next();
                if (key.isSigningKey()) {
                    return key;
                }
            }
        }

        throw new IllegalArgumentException("Can't find signing key in key ring.");
    }

    /**
     * 解密文件并跳过核签
     * @param inputFileName 加密的文件
     * @param priKeyFile 私钥文件，即base64串
     * @param passwd 私钥密码
     * @param outputFileName 输出文件名
     * @throws Exception
     */
    public static void decryptFileSkipSign(String inputFileName, String priKeyFile, char[] passwd, String outputFileName) throws Exception {
        InputStream in = null;
        InputStream keyIn = null;
        try {
            in = new BufferedInputStream(new FileInputStream(inputFileName));
            keyIn = new ByteArrayInputStream(Base64.decode(priKeyFile));  // 自身私钥，用于解密
            decryptFile(in, keyIn, passwd, outputFileName, null, true);
        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            IOUtils.closeQuietly(keyIn);
            IOUtils.closeQuietly(in);
        }
    }

    /**
     * 解密文件
     * @param inputFileName 加密的文件
     * @param priKeyPath 私钥路径
     * @param passwd 私钥密码
     * @param outputFileName 输出文件名
     * @param pubKeyPath 公钥路径
     * @param skipCheckSign 跳过核签
     * @throws Exception
     */
    public static void decryptFile(String inputFileName, String priKeyPath, char[] passwd, String outputFileName, String pubKeyPath, boolean skipCheckSign) throws Exception {
        InputStream in = null;
        InputStream keyIn = null;
        InputStream signIn = null;
        try {
            in = new BufferedInputStream(new FileInputStream(inputFileName));
            keyIn = new BufferedInputStream(new FileInputStream(priKeyPath));  // 自身私钥，用于解密
            signIn = new BufferedInputStream(new FileInputStream(pubKeyPath)); // 对方公钥，用于验签
            decryptFile(in, keyIn, passwd, outputFileName, signIn, skipCheckSign);
        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            IOUtils.closeQuietly(keyIn);
            IOUtils.closeQuietly(in);
            IOUtils.closeQuietly(signIn);
        }
    }

    /**
     * decrypt the passed in message stream
     */
    public static void decryptFile(InputStream in, InputStream keyIn, char[] passwd, String defaultFileName, InputStream signIn, boolean skipCheckSign) throws Exception {
        ByteArrayOutputStream actualOutput = null;
        OutputStream fOut = null;
        BufferedInputStream bis = null;
        try {
            in = PGPUtil.getDecoderStream(in);
            JcaPGPObjectFactory pgpF = new JcaPGPObjectFactory(in);
            PGPEncryptedDataList enc;
            Object o = pgpF.nextObject();

            // the first object might be a PGP marker packet.
            if (o instanceof PGPEncryptedDataList) {
                enc = (PGPEncryptedDataList) o;
            } else {
                enc = (PGPEncryptedDataList) pgpF.nextObject();
            }

            // find the secret key
            Iterator it = enc.getEncryptedDataObjects();
            PGPPrivateKey sKey = null;
            PGPPublicKeyEncryptedData pbe = null;
            PGPSecretKeyRingCollection pgpSec = new PGPSecretKeyRingCollection(
                    PGPUtil.getDecoderStream(keyIn), new JcaKeyFingerprintCalculator());
            while (sKey == null && it.hasNext()) {
                pbe = (PGPPublicKeyEncryptedData) it.next();
                sKey = findSecretKey(pgpSec, pbe.getKeyID(), passwd);
            }

            if (sKey == null) {
                throw new IllegalArgumentException("secret key for message not found.");
            }

            InputStream clear = pbe.getDataStream(new BcPublicKeyDataDecryptorFactory(sKey));
            PGPObjectFactory plainFact = new PGPObjectFactory(clear, new BcKeyFingerprintCalculator());

            PGPOnePassSignatureList onePassSignatureList = null;
            PGPSignatureList signatureList = null;
            PGPCompressedData compressedData = null;
            String outFileName = null;
            Object message = plainFact.nextObject();
            actualOutput = new ByteArrayOutputStream();
            while (message != null) {
                if (message instanceof PGPCompressedData) {
                    compressedData = (PGPCompressedData) message;
                    plainFact = new PGPObjectFactory(compressedData.getDataStream(), new BcKeyFingerprintCalculator());
                    message = plainFact.nextObject();
                }

                if (message instanceof PGPLiteralData) {
                    PGPLiteralData ld = (PGPLiteralData) message;
                    outFileName = ld.getFileName();
                    if (outFileName.length() == 0) {
                        outFileName = defaultFileName;
                    } else {
                        outFileName = defaultFileName;
                    }

                    InputStream unc = ld.getInputStream();
                    Streams.pipeAll(unc, actualOutput);
                } else if (message instanceof PGPOnePassSignatureList) {
                    onePassSignatureList = (PGPOnePassSignatureList) message;
                } else {
                    signatureList = (PGPSignatureList) message;
                }
                message = plainFact.nextObject();
            }

            byte[] outputBytes = actualOutput.toByteArray();
            if (onePassSignatureList == null || signatureList == null) {
                log.info("Poor PGP. Signatures not found.");
            } else if (onePassSignatureList != null && !skipCheckSign) {
                // 实际上不验签、验签失败也不会影响文件解密
                for (int i = 0; i < onePassSignatureList.size(); i++) {
                    PGPOnePassSignature ops = onePassSignatureList.get(i);
                    PGPPublicKeyRingCollection pgpRing = new PGPPublicKeyRingCollection(PGPUtil.getDecoderStream(signIn), new BcKeyFingerprintCalculator());
                    PGPPublicKey publicKey = pgpRing.getPublicKey(ops.getKeyID());
                    // PGPPublicKey publicKey = readPublicKey(signIn);
                    if (publicKey != null) {
                        ops.init(new BcPGPContentVerifierBuilderProvider(), publicKey);
                        ops.update(outputBytes);
                        PGPSignature signature = signatureList.get(i);
                        if (ops.verify(signature)) {
                            log.info("验证签名成功");
                        } else {
                            log.info("验证签名失败");
                        }
                    }
                }
            }
            fOut = new BufferedOutputStream(new FileOutputStream(outFileName));
            bis = new BufferedInputStream(new ByteArrayInputStream(outputBytes));
            byte[] b = new byte[1024];
            int len = -1;
            while ((len = bis.read(b)) != -1) {
                fOut.write(b, 0, len);
            }

        } catch (PGPException e) {
            log.error("PGPException.", e);
            if (e.getUnderlyingException() != null) {
                log.error("UnderlyingException.", e.getUnderlyingException());
            }
            throw new Exception(e);
        } catch (IOException e) {
            log.error("IOException.", e);
            throw new Exception(e);
        } finally {
            IOUtils.closeQuietly(actualOutput);
            IOUtils.closeQuietly(fOut);
            IOUtils.closeQuietly(bis);
        }
    }

    public static void encryptFile(String outputFileName, String inputFileName, String encKeyFileName, boolean armor, boolean withIntegrityCheck)
            throws IOException, NoSuchProviderException, PGPException {
        OutputStream out = new BufferedOutputStream(new FileOutputStream(outputFileName));
        PGPPublicKey encKey = readPublicKey(encKeyFileName);
        encryptFile(out, inputFileName, encKey, armor, withIntegrityCheck);
        out.close();
    }

    public static void encryptFile(OutputStream out, String fileName, PGPPublicKey encKey, boolean armor, boolean withIntegrityCheck)
            throws IOException, NoSuchProviderException {
        if (armor) {
            out = new ArmoredOutputStream(out);
        }

        try {
            byte[] bytes = compressFile(fileName, CompressionAlgorithmTags.ZIP);
            PGPEncryptedDataGenerator encGen = new PGPEncryptedDataGenerator(
                    new JcePGPDataEncryptorBuilder(PGPEncryptedData.CAST5).setWithIntegrityPacket(withIntegrityCheck).setSecureRandom(new SecureRandom()).setProvider("BC"));
            encGen.addMethod(new JcePublicKeyKeyEncryptionMethodGenerator(encKey).setProvider("BC"));
            OutputStream cOut = encGen.open(out, bytes.length);
            cOut.write(bytes);
            cOut.close();

            if (armor) {
                out.close();
            }

        } catch (PGPException e) {
            log.error("PGPException.", e);
            if (e.getUnderlyingException() != null) {
                log.error("UnderlyingException.", e.getUnderlyingException());
            }
        }
    }

    public static void main(String[] args) throws Exception {
        String path = "D:\\pgp\\";
        String pubKeyFile = path + "public-file.key";
        String priKeyFile = path + "private-file.key";
        // 生成PGP密钥对
        //try {
        //    generatePGPKeyPair(2048, "", "sdebank", pubKeyFile, priKeyFile, path);
        //} catch (Exception e) {
        //    System.out.println("生成PGP密钥对失败");
        //    e.printStackTrace();
        //}

        boolean encryp = false; //加密：true? 解密：false
        // 加密
        String fileName = "test.xml";
        if (encryp) {
            String outPath = path + fileName + ".pgp";
            String inputPath = path + fileName;
            String publicKeys = pubKeyFile; //公钥地址
            encryptFile(outPath, inputPath, publicKeys, true, true);
        } else {
            // 解密
            String password = "123"; //私钥的密码
            String privateKeys = priKeyFile; //私钥地址

            String inputPath = path + fileName + ".pgp"; //被加密的文件
            String outPath = path + "decrypt_" + fileName;
            System.out.println("要解密的文件：" + inputPath + "，解密出来的文件" + outPath);
            decryptFileSkipSign(inputPath, privateKeys, password.toCharArray(), outPath);
        }
    }

}
