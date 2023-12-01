package net.javaguides.sms.utils;

import java.util.UUID;

public class TokenGenerator {

    public static String generateUniqueToken() {
        return UUID.randomUUID().toString();
    }
}

