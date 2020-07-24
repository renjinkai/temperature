package com.skyform.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

public class Base64Utils {

    public static String decode(String base64String) {
        byte[] decoded = Base64.decodeBase64(base64String);
        return Hex.encodeHexString(decoded);
    }

    public static void main(String[] args) {

    }
}
