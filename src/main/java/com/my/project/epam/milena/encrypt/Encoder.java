package com.my.project.epam.milena.encrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encoder {
    private Encoder() {
    }

    public static String encrypt(String line) throws NoSuchAlgorithmException {
        var md5 = MessageDigest.getInstance("MD5");
        byte[] digest = md5.digest(line.getBytes());

        var sb = new StringBuilder(digest.length * 2);
        for (byte b : digest) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
