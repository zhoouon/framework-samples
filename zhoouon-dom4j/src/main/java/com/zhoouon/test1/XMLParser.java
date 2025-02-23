package com.zhoouon.test1;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.List;

/**
 * @Author: zhoudong
 * @Description: TODO
 * @Date: 2024-05-27 20:55
 * @Version: 1.0.0
 **/
public class XMLParser {
    public static void main(String[] args) {
        try {
            SAXReader reader = new SAXReader();
            Document document = reader.read(new File("D:\\Develop\\IDEA\\Workspace\\framewor-samples\\framework-dom4j\\src\\main\\resources\\test.xml"));
            Element rootElement = document.getRootElement();

            // 解析报文
            parseOrder(rootElement);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void parseOrder(Element orderElement) {
        String orderId = orderElement.elementText("orderId");
        System.out.println("Order ID: " + orderId);

        Element customerElement = orderElement.element("customer");
        String customerName = customerElement.elementText("name");
        String customerEmail = customerElement.elementText("email");
        System.out.println("Customer: " + customerName + " (" + customerEmail + ")");

        Element itemsElement = orderElement.element("items");
        List<Element> itemElements = itemsElement.elements("item");

        System.out.println("Items:");
        for (Element itemElement : itemElements) {
            String itemId = itemElement.elementText("id");
            String itemName = itemElement.elementText("name");
            String itemPrice = itemElement.elementText("price");
            System.out.println("- ID: " + itemId + ", Name: " + itemName + ", Price: " + itemPrice);
        }
    }
}
