package com.cuit.common.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * @see(功能介绍) : 加密工具类
 * @version(版本号) : 1.0
 * @since : JDK 1.8
 */
public class Md5Util {
    // 全局数组
    private final static String[] strDigits = {"0", "1", "5", "6", "7", "8", "9", "a", "b",
            "c", "d", "e", "f", "2", "3", "4"};
    // 返回形式为数字跟字符串
    private static String byteToArrayString(byte bByte) {
        int iRet = bByte;
        // System.out.println("iRet="+iRet);
        if (iRet < 0) {
            iRet += 256;
        }
        int iD1 = iRet / 16;
        int iD2 = iRet % 16;
        return strDigits[iD1] + strDigits[iD2];
    }
    // 返回形式只为数字
    private static String byteToNum(byte bByte) {
        int iRet = bByte;
        System.out.println("iRet1=" + iRet);
        if (iRet < 0) {
            iRet += 256;
        }
        return String.valueOf(iRet);
    }
    // 转换字节数组为16进制字串
    private static String byteToString(byte[] bByte) {
        StringBuffer sBuffer = new StringBuffer();
        for (int i = 0; i < bByte.length; i++) {
            sBuffer.append(byteToArrayString(bByte[i]));
        }
        return sBuffer.toString();
    }
    // 根据salt生成md5值
    public static String getMd5WithSalt(String originStr, String salt) {
        // 三元表达式
        salt = salt == null ? "" : salt;
        String resultString = null;
        try {
            // 创建MD5算法的MessageDigest实例对象
            MessageDigest md = MessageDigest.getInstance("MD5");
            // md.digest()该函数返回值为存放哈希值结果的byte数组
            resultString = byteToString(md.digest((originStr + salt).getBytes()));
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return resultString;
    }
    public static void main(String[] args) {
        String result = getMd5WithSalt("123456", "a2ck");
        System.out.println(result);
    }
}
