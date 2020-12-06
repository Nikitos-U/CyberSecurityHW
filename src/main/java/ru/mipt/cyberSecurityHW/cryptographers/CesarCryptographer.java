package ru.mipt.cyberSecurityHW.cryptographers;

import lombok.Data;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.FileReader;

@Data
public class CesarCryptographer implements Cryptographer  {
    private FileReader inputFile;
    private BufferedReader reader;


    @SneakyThrows
    public void encrypt(String filename, String key) {
        int cipherKey = Integer.parseInt(key);
        inputFile = new FileReader(filename);
        reader = new BufferedReader(inputFile);
        String line;
        StringBuilder encryptedLine = new StringBuilder();
        while (!((line = reader.readLine()) == null)) {
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) != ' ') {
                    int letterCode = line.charAt(i) + cipherKey;
                    int encryptedLetterCode = (letterCode - 1040) % 64 + 1040;
                    encryptedLine.append((char) encryptedLetterCode);
                } else {
                    encryptedLine.append(line.charAt(i));
                }
            }
        }
        System.out.println(encryptedLine);
    }

    @SneakyThrows
    @Override
    public void decrypt(String filename, String key) {
        int cipherKey = Integer.parseInt(key);
        inputFile = new FileReader(filename);
        reader = new BufferedReader(inputFile);
        String line;
        StringBuilder encryptedLine = new StringBuilder();
        while (!((line = reader.readLine()) == null)) {
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) != ' ') {
                    int letterCode = line.charAt(i) - cipherKey;
                    int encryptedLetterCode = 1103 - (1104 - letterCode) % 64;
                    encryptedLine.append((char) encryptedLetterCode);
                } else {
                    encryptedLine.append(line.charAt(i));
                }
            }
        }
        System.out.println(encryptedLine);
    }
}
