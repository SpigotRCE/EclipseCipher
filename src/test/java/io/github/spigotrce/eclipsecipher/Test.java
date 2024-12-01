package io.github.spigotrce.eclipsecipher;

public class Test {
    public static void main(String[] args) {
        String text = "Hello, World";
        long sharedKey = EclipseCipher.serialize(EclipseCipher.generateKey(1024));

        CipherManager encrypter = EclipseCipher.newCipherManager(sharedKey);
        CipherManager decrypter = EclipseCipher.newCipherManager(sharedKey);

        String encryptedText = encrypter.encrypt(text);
        String decryptedText = decrypter.decrypt(encryptedText);

        System.out.println("Original text: " + text);
        System.out.println("Shared key: " + sharedKey);
        System.out.println("Encrypted text: " + encryptedText);
        System.out.println("Decrypted text: " + decryptedText);

        System.out.println("Is equal: " + text.equals(decryptedText));
    }
}
