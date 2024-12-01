package io.github.spigotrce.eclipsecipher;

import java.security.SecureRandom;
import java.util.Random;

/**
 * This class provides various utility methods for working with the EclipseCipher.
 */
public class EclipseCipher {
    // Charset
    public static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/ ";

    /**
     * Generates a secure random key of length 2048 characters.
     *
     * @return The generated secure random key.
     */
    public static String generateKey(int length) {
        SecureRandom secureRandom = new SecureRandom(); // The randomizer used to generate the secret key
        StringBuilder stringBuilder = new StringBuilder(length); // Create a new StringBuilder

        for (int i = 0; i < length; i++) // Iterate for the length of the key
            // Append a new secure char
            stringBuilder.append(EclipseCipher.CHARACTERS.charAt(secureRandom.nextInt(EclipseCipher.CHARACTERS.length())));

        // Return the key
        return stringBuilder.toString();
    }

    /**
     * Generates a string of characters using a given seed and length.
     *
     * @param seed   The seed for the random number generator.
     * @param length The length of the generated string.
     * @return       The generated string of characters.
     */
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

    /**
     * Deserializes an integer into a string using the CHARACTERS charset.
     *
     * @param i The integer to be deserialized.
     * @return  The deserialized string.
     */
    public static String deserialize(int i) {
        StringBuilder r = new StringBuilder();
        while (i > 0) {
            r.append(CHARACTERS.charAt(i % CHARACTERS.length()));
            i /= CHARACTERS.length();
        }
        return r.reverse().toString();
    }

    /**
     * Serializes a string into an integer using the CHARACTERS charset.
     *
     * @param t The string to be serialized.
     * @return  The serialized integer.
     */
    public static int serialize(String t) {
        int r = 0;
        for (char c : t.toCharArray())
            r = r * CHARACTERS.length() + CHARACTERS.indexOf(c);
        return r;
    }

    /**
     * Creates a new CipherManager instance with the given key.
     *
     * @param key The key for the CipherManager.
     * @return    The newly created CipherManager instance.
     */
    public static CipherManager newCipherManager(long key) {
        return new CipherManager(key);
    }
}
