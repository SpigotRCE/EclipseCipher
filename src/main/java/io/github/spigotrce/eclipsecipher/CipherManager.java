package io.github.spigotrce.eclipsecipher;

import java.util.Arrays;
import java.util.Random;

public class CipherManager {
    private final Random random;
    private int paddingLength;

    public CipherManager(long key) {
        random = new Random();
        random.setSeed(key);
        paddingLength = random.nextInt(16);
    }

    public String encrypt(String text) {
        StringBuilder builder = new StringBuilder();
        for (char c : text.toCharArray()) {
            int index = EclipseCipher.CHARACTERS.indexOf(c);
            if (index == -1)
                builder.append(c);
            else {
                int shift = random.nextInt(EclipseCipher.CHARACTERS.length());
                builder.append(EclipseCipher.CHARACTERS.charAt((index + shift) % EclipseCipher.CHARACTERS.length()));
            }
        }
        builder.append(EclipseCipher.getCharacters(random.nextInt(), paddingLength));
        random.setSeed(random.nextInt());
        paddingLength = random.nextInt(16);
        return builder.toString();
    }

    public String decrypt(String cipher) {
        StringBuilder builder = new StringBuilder();
        for (char c : Arrays.copyOfRange(cipher.toCharArray(), 0, cipher.length() - paddingLength)) {
            int index = EclipseCipher.CHARACTERS.indexOf(c);
            if (index == -1)
                builder.append(c);
            else {
                int shift = random.nextInt(EclipseCipher.CHARACTERS.length());
                builder.append(EclipseCipher.CHARACTERS.charAt((index - shift + EclipseCipher.CHARACTERS.length()) % EclipseCipher.CHARACTERS.length()));
            }
        }
        random.nextInt();
        random.setSeed(random.nextInt());
        paddingLength = random.nextInt(16);
        return builder.toString();
    }
}
