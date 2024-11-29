package io.github.spigotrce.eclipsecipher;

import java.util.Arrays;
import java.util.Random;

public class CipherManager {
    private final Random stringShifter;
    private final int paddingLength;

    public CipherManager(long key) {
        stringShifter = new Random();
        stringShifter.setSeed(key);
        paddingLength = stringShifter.nextInt(512);
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
        builder.append(EclipseCipher.getCharacters(stringShifter.nextInt(), paddingLength));
        return builder.toString();
    }

    public String decrypt(String cipher) {
        StringBuilder builder = new StringBuilder();
        for (char c : Arrays.copyOfRange(cipher.toCharArray(), 0, cipher.length() - paddingLength)) {
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
