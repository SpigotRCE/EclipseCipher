package io.github.spigotrce.eclipsecipher;

import java.util.Random;

public class CipherManager {
    private final Random stringShifter;

    public CipherManager(long key) {
        stringShifter = new Random();
        stringShifter.setSeed(key);
    }

    public String encrypt(String text) {
        StringBuilder builder = new StringBuilder();
        for (char c : text.toCharArray()) {
            int index = EclipseCipher.CHARACTERS.indexOf(c);
            if (index == -1)
                builder.append(c);
            else {
                int shift = stringShifter.nextInt(EclipseCipher.CHARACTERS.length());
                builder.append(EclipseCipher.CHARACTERS.charAt((index + shift) % EclipseCipher.CHARACTERS.length()));
            }
        }
        return builder.toString();
    }

    public String decrypt(String cipher) {
        StringBuilder builder = new StringBuilder();
        for (char c : cipher.toCharArray()) {
            int index = EclipseCipher.CHARACTERS.indexOf(c);
            if (index == -1)
                builder.append(c);
            else {
                int shift = stringShifter.nextInt(EclipseCipher.CHARACTERS.length());
                builder.append(EclipseCipher.CHARACTERS.charAt((index - shift + EclipseCipher.CHARACTERS.length()) % EclipseCipher.CHARACTERS.length()));
            }
        }
        return builder.toString();
    }
}
