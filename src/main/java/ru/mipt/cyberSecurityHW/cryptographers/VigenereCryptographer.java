package ru.mipt.cyberSecurityHW.cryptographers;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.FileReader;

public class VigenereCryptographer implements Cryptographer {
    private final String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ ,.-";
    private FileReader inputFile;
    private BufferedReader reader;

    @SneakyThrows
    public String encrypt(String filename, String key) {
        inputFile = new FileReader(filename);
        reader = new BufferedReader(inputFile);
        String line;
        final int alphabetSize = alphabet.length();
        final int keySize = key.length();
        final StringBuilder encryptedText = new StringBuilder();
        while (!((line = reader.readLine()) == null)) {
            for (int i = 0; i < line.length(); i++) {
                final char plainChar = line.charAt(i);
                final char keyChar = key.charAt(i % keySize);
                final int plainPos = alphabet.indexOf(plainChar);
                if (plainPos == -1) {
                    encryptedText.append(plainChar);
                } else {
                    final int keyPos = alphabet.indexOf(keyChar);
                    encryptedText.append(alphabet.charAt((plainPos + keyPos) % alphabetSize));

                }
            }
        }
        inputFile.close();
        return encryptedText.toString();
    }

    @SneakyThrows
    public String decrypt(String filename, String key) {
        inputFile = new FileReader(filename);
        reader = new BufferedReader(inputFile);
        String line;
        final int alphabetSize = alphabet.length();
        final int keySize = key.length();
        final StringBuilder encryptedText = new StringBuilder();
        while (!((line = reader.readLine()) == null)) {
            for (int i = 0; i <line.length() ; i++) {
                final char plainChar = line.charAt(i);
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
        }
        inputFile.close();
        return encryptedText.toString();
    }
}
