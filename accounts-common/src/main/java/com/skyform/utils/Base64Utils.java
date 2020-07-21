package com.skyform.utils;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.Date;

public class Base64Utils {

    public static String decodeBase64(String base64String) {
        try {
            return new String(Base64.decodeBase64(base64String),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            return e.getMessage();
        }
    }

    public static void main(String[] args) {
        String time = "1594883424029";
        System.out.println(new Timestamp(Long.valueOf(time)));
    }
}
