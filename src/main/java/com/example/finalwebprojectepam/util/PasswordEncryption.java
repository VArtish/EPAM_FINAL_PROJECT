package com.example.finalwebprojectepam.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncryption {
    private static final String ENCRYPTION_TYPE = "MD5";

    public static String encryption(String password) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance(ENCRYPTION_TYPE);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] bytes = md5.digest(password.getBytes());
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
            stringBuilder.append(b);
        }
        return stringBuilder.toString();
    }
}