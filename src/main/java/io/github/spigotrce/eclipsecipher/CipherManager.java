package io.github.spigotrce.eclipsecipher;

import java.util.Arrays;
import java.util.Random;

public class CipherManager {
    private final Random random; // The randomizer used to generate next cipher
    private int paddingLength; // The dynamic length of the padding

    public CipherManager(long key) {
        random = new Random();
        random.setSeed(key);
        paddingLength = random.nextInt(16);
    }

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

    public String decrypt(String cipher) {
        StringBuilder builder = new StringBuilder(); // Create a new StringBuilder
        for (char c : Arrays.copyOfRange(cipher.toCharArray(), 0, cipher.length() - paddingLength)) { // Iterate over the cipher characters
            int index = EclipseCipher.CHARACTERS.indexOf(c); // Get the index from the charset
            if (index == -1) // If character doesn't exist in the charset
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
