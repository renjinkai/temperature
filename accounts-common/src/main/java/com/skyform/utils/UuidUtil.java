package com.skyform.utils;

import java.util.Random;
import java.util.UUID;

public class UuidUtil {

    public static String genUUID(){
        return UUID.randomUUID().toString().substring(0,8).toLowerCase();
    }

    public static String genNum(){
        Random random = new Random();
        String result= "";
        for(int i=0;i<8;i++){
            //首字母不能为0
            result += (random.nextInt(9)+1);
        }
        return result;
    }

    public static void main(String[] args) {
        Random random = new Random();
        String result="";
        for(int i=0;i<8;i++){
            //首字母不能为0
            result += (random.nextInt(9)+1);
        }
        System.out.println(result);
    }
}
