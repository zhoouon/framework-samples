package com.zhoouon.minio.controller;

import com.zhoouon.minio.utils.AjaxResult;
import com.zhoouon.minio.utils.MinioUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @Author: zhoudong
 * @Description: minio文件上传控制器
 * @Date: 2024-06-30 18:47
 * @Version: 1.0.0
 **/
@CrossOrigin
@RestController
@RequestMapping("/api")
public class MinioFileUploadController {
    @Autowired
    private MinioUtils minioUtils;

    /**
     * @param file     文件
     * @param fileName 文件名称
     * @return {@link AjaxResult }
     * @Description 上传文件
     */
    @PostMapping("/upload")
    public AjaxResult uploadFile(@RequestParam("file") MultipartFile file, String fileName) {
        String objectName = minioUtils.upload(file, fileName);
        return AjaxResult.success("上传成功", objectName);
    }

    /**
     * @param fileName 对象名称
     * @return 文件流
     * @Description 下载文件
     */
    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadFile(@RequestParam("fileName") String fileName) {
        return minioUtils.download(fileName);
    }

    /**
     * @param fileName 文件名称
     * @return {@link AjaxResult }
     * @Description 得到文件url
     */
    @GetMapping("/getUrl")
    public AjaxResult getFileUrl(@RequestParam("fileName") String fileName) {
        String fileUrl = minioUtils.getFileUrl(fileName);
        return fileUrl == null
                ? AjaxResult.error("获取文件访问地址失败")
                : AjaxResult.success(Map.of("fileUrl", fileUrl));
    }
}
