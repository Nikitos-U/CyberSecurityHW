package ru.mipt.cyberSecurityHW.cryptographers;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.List.of;

public class ShuffleCryptographer implements Cryptographer {
    private FileReader inputFile;
    private BufferedReader reader;

    @SneakyThrows
    @Override
    public String encrypt(String filename, String key) {
        List<Integer> cipherKey = of(key.split(",")).stream().map(Integer::parseInt).collect(Collectors.toList());
        inputFile = new FileReader(filename);
        reader = new BufferedReader(inputFile);
        String line;
        StringBuilder result = new StringBuilder();
        while (!((line = reader.readLine()) == null)) {
            StringBuilder cipherBody = new StringBuilder(line);
            for (int i = 0; i < line.length() % cipherKey.size(); i++)
                cipherBody.append(line.charAt(i));

            for (int i = 0; i < line.length(); i += cipherKey.size()) {
                char[] transposition = new char[cipherKey.size()];

                for (int j = 0; j < cipherKey.size(); j++)
                    transposition[cipherKey.get(j) - 1] = cipherBody.charAt(i + j);

                for (int j = 0; j < cipherKey.size(); j++)
                    result.append(transposition[j]);
            }
        }
        inputFile.close();
        return result.toString();
    }

    @SneakyThrows
    @Override
    public String decrypt(String filename, String key) {
        inputFile = new FileReader(filename);
        reader = new BufferedReader(inputFile);
        List<Integer> cipherKey = of(key.split(",")).stream().map(Integer::parseInt).collect(Collectors.toList());
        String line;
        StringBuilder result = new StringBuilder();
        while (!((line = reader.readLine()) == null)) {
            for (int i = 0; i < line.length(); i += cipherKey.size()) {
                char[] transposition = new char[cipherKey.size()];

                for (int j = 0; j < cipherKey.size(); j++)
                    transposition[j] = line.charAt(i + cipherKey.get(j) - 1);

                for (int j = 0; j < cipherKey.size(); j++)
                    result.append(transposition[j]);
            }

        }
        inputFile.close();
        return result.toString();
    }
}
