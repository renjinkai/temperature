package com.skyform.utils;

import java.util.UUID;

public class UuidUtil {

    public static String genUUID(){
        return UUID.randomUUID().toString().substring(0,8).toLowerCase();
    }

    public static void main(String[] args) {
        System.out.println(UuidUtil.genUUID());
    }
}
