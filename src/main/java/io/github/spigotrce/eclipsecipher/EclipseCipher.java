package io.github.spigotrce.eclipsecipher;

import java.security.SecureRandom;
import java.util.Random;

public class EclipseCipher {
    // Charset
    public static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/ ";

    public static String generateKey() {
        SecureRandom secureRandom = new SecureRandom(); // The randomizer used to generate the secret key
        StringBuilder stringBuilder = new StringBuilder(2048); // Create a new StringBuilder

        for (int i = 0; i < 2048; i++) // Iterate for length of the key
            // Append a new secure char
            stringBuilder.append(EclipseCipher.CHARACTERS.charAt(secureRandom.nextInt(EclipseCipher.CHARACTERS.length())));

        // Return the key
        return stringBuilder.toString();
    }

    public static String getCharacters(int seed, int length) {
        Random random = new Random(); // The randomizer used to generate the characters
        random.setSeed(seed); // Set the seed of the randomizer

        StringBuilder stringBuilder = new StringBuilder(length); // Create a new StringBuilder

        for (int i = 0; i < length; i++) // Iterate for length of the key
            // Append a new char
            stringBuilder.append(EclipseCipher.CHARACTERS.charAt(random.nextInt(EclipseCipher.CHARACTERS.length())));

        // Return the string
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
