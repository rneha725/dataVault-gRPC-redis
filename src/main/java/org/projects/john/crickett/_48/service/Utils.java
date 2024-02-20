package org.projects.john.crickett._48.service;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HexFormat;
import java.util.Random;

public class Utils {
    public static String generateToken(String value) {
        HexFormat hexFormat = HexFormat.of();
        String hexCode = hexFormat
                .formatHex((new Date().getTime() + value + Random.from(() -> 222)).getBytes(StandardCharsets.UTF_8))
                .substring(0, 6);
        return hexCode;
    }
}
