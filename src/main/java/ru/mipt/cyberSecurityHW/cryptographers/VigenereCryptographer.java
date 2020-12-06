package ru.mipt.cyberSecurityHW.cryptographers;

public class VigenereCryptographer implements Cryptographer {

    public void encrypt(String filename, String key) {
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ ,.-";
        final int alphabetSize = alphabet.length();
        final int textSize = filename.length();
        final int keySize = key.length();
        final StringBuilder encryptedText = new StringBuilder(textSize);

        for (int i = 0; i < textSize; i++) {
            final char plainChar = filename.charAt(i);
            final char keyChar = key.charAt(i % keySize);
            final int plainPos = alphabet.indexOf(plainChar);
            if (plainPos == -1) {
                encryptedText.append(plainChar);
            } else {
                final int keyPos = alphabet.indexOf(keyChar);
                encryptedText.append(alphabet.charAt((plainPos + keyPos) % alphabetSize));

            }
        }

        System.out.println(encryptedText.toString());
    }

    public void decrypt(String filename, String key) {
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ ,.-";
        final int alphabetSize = alphabet.length();
        final int textSize = filename.length();
        final int keySize = key.length();
        final StringBuilder encryptedText = new StringBuilder(textSize);

        for (int i = 0; i < textSize; i++) {
            final char plainChar = filename.charAt(i);
            final char keyChar = key.charAt(i % keySize);
            final int plainPos = alphabet.indexOf(plainChar);
            if (plainPos == -1) {
                encryptedText.append(plainChar);
            } else {
                final int keyPos = alphabet.indexOf(keyChar);
                int shiftedPos = plainPos - keyPos;
                if (shiftedPos < 0) {
                    shiftedPos += alphabetSize;
                }
                encryptedText.append(alphabet.charAt(shiftedPos));

            }
        }
        System.out.println(encryptedText);
    }
}
