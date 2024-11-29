package io.github.spigotrce.eclipsecipher;

import java.security.SecureRandom;
import java.util.Random;

public class EclipseCipher {
    public static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/ ";

    public static String generateKey() {
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder stringBuilder = new StringBuilder(2048);

        for (int i = 0; i < 2048; i++) {
            int randomIndex = secureRandom.nextInt(EclipseCipher.CHARACTERS.length());
            stringBuilder.append(EclipseCipher.CHARACTERS.charAt(randomIndex));
        }

        return stringBuilder.toString();
    }

    public static String deserialize(int i) {
        StringBuilder r = new StringBuilder();
        while (i > 0) {
            r.append(CHARACTERS.charAt(i % CHARACTERS.length()));
            i /= CHARACTERS.length();
        }
        return r.reverse().toString();
    }

    public static int serialize(String t) {
        int r = 0;
        for (char c : t.toCharArray())
            r = r * CHARACTERS.length() + CHARACTERS.indexOf(c);
        return r;
    }

    public static CipherManager newCipherManager(long key) {
        return new CipherManager(key);
    }
}
