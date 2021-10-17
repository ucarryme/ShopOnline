package com.cuit.common.utils;
import java.util.UUID;
/**
 * 生成UUID工具类
 */
public class UUIDUtil {
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
    public static void main(String[] args) {
        System.out.println(getUUID());
    }
}

