package io.github.spigotrce.eclipsecipher;

import java.util.Arrays;
import java.util.Random;


/**
 * A class that manages encryption and decryption operations using a custom cipher.
 */
public class CipherManager {
    private final Random random; // The randomizer used to generate the next cipher
    private int paddingLength; // The dynamic length of the padding

    /**
     * Constructs a new CipherManager with the given key.
     *
     * @param key The key used to initialize the random seed.
     */
    public CipherManager(long key) {
        random = new Random();
        random.setSeed(key);
        paddingLength = random.nextInt(16);
    }

    /**
     * Encrypts the given text using a custom cipher.
     *
     * @param text The text to be encrypted.
     * @return     The encrypted text.
     */
    public String encrypt(String text) {
        StringBuilder builder = new StringBuilder(); // Create a new StringBuilder
        for (char c : text.toCharArray()) { // Iterate over the characters
            int index = EclipseCipher.CHARACTERS.indexOf(c); // Get the index from the charset
            if (index == -1) // If character doesn't exist in the charset
                builder.append(c); // Append the character as is
            else
                // Create a new ciphered char and append it to the builder
                builder.append(EclipseCipher.CHARACTERS.charAt((index + random.nextInt(EclipseCipher.CHARACTERS.length())) % EclipseCipher.CHARACTERS.length()));
        }

        builder.append(EclipseCipher.getCharacters(random.nextInt(), paddingLength)); // Append a new padding

        random.setSeed(random.nextInt()); // Reset the seed

        paddingLength = random.nextInt(16); // Reset the padding

        return builder.toString(); // Return the cipher
    }

    /**
     * Decrypts the given cipher using a custom cipher.
     *
     * @param cipher The cipher to be decrypted.
     * @return       The decrypted text.
     */
    public String decrypt(String cipher) {
        StringBuilder builder = new StringBuilder(); // Create a new StringBuilder
        for (char c : Arrays.copyOfRange(cipher.toCharArray(), 0, cipher.length() - paddingLength)) { // Iterate over the cipher characters
            int index = EclipseCipher.CHARACTERS.indexOf(c); // Get the index from the charset
            if (index == -1) // If a character doesn't exist in the charset
                builder.append(c); // Append the character as is
            else
                // Create a new deciphered char and append it to the builder
                builder.append(EclipseCipher.CHARACTERS.charAt((index - random.nextInt(EclipseCipher.CHARACTERS.length()) + EclipseCipher.CHARACTERS.length()) % EclipseCipher.CHARACTERS.length()));
        }

        random.nextInt(); // Compensate for the padding in the encrypt method

        random.setSeed(random.nextInt()); // Reset the seed

        paddingLength = random.nextInt(16); // Reset the padding

        return builder.toString(); // Return the cipher
    }
}
