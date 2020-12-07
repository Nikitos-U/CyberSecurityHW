package ru.mipt.cyberSecurityHW.decryptors;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.mipt.cyberSecurityHW.cryptographers.Cryptographer;

import java.util.ArrayList;
import java.util.HashMap;
import static java.lang.String.valueOf;
import static ru.mipt.cyberSecurityHW.CryptographerTypeHandler.chooseCryptographer;

@Data
@NoArgsConstructor
public class MoveProbableWordDecryptor {

    public HashMap<String, String> decrypt(String fileName, String probableWord, int keySize, String encryptionType) {
        HashMap<String, String> probableKeys = new HashMap<>();
        Cryptographer currentCryptographer = chooseCryptographer(encryptionType);
        if (encryptionType.toLowerCase().contains("cesar")) {
            decryptCesar(fileName, probableWord, currentCryptographer, probableKeys);
            return probableKeys;
        } else if (encryptionType.toLowerCase().contains("shuffle")) {
            decryptSuffle(fileName, probableWord, keySize, currentCryptographer, probableKeys);
            return probableKeys;
        } else if (encryptionType.toLowerCase().contains("vigenere")) {
            decryptVigenere(fileName, probableWord, keySize, currentCryptographer, probableKeys);
        }
        return probableKeys;
    }

    private void decryptCesar(String fileName, String probableWord, Cryptographer currentCryptographer, HashMap<String, String> probableKeys) {
        for (int i = 0; i < 64; i++) {
            String probableText;
            assert currentCryptographer != null;
            probableText = currentCryptographer.decrypt(fileName, valueOf(i));
            if (probableText.contains(probableWord)) {
                probableKeys.put(valueOf(i), probableText);
            }
        }
    }

    private void decryptSuffle(String fileName, String probableWord, int keySize, Cryptographer currentCryptographer, HashMap<String, String> probableKeys) {
        StringBuilder probableKey = new StringBuilder();
        for (int i = 1; i < keySize + 1; i++) {
            probableKey.append(i);
        }
        int[] factorials = new int[keySize + 1];
        factorials[0] = 1;
        for (int i = 1; i <= keySize; i++) {
            factorials[i] = factorials[i - 1] * i;
        }

        for (int i = 0; i < factorials[probableKey.length()]; i++) {
            StringBuilder onePermutation = new StringBuilder();
            String temp = probableKey.toString();
            int positionCode = i;
            for (int position = probableKey.length(); position > 0; position--) {
                int selected = positionCode / factorials[position - 1];
                onePermutation.append(temp.charAt(selected));
                positionCode = positionCode % factorials[position - 1];
                temp = temp.substring(0, selected) + temp.substring(selected + 1);
            }
            String key = String.join(",", onePermutation.toString().split(""));
            assert currentCryptographer != null;
            String probableText = currentCryptographer.decrypt(fileName, key);
            if (probableText.contains(probableWord)) {
                probableKeys.put(key, probableText);
            }
        }
    }

    private void decryptVigenere(String fileName, String probableWord, int keySize, Cryptographer currentCryptographer, HashMap<String, String> probableKeys) {
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ ,.-";
        ArrayList<String> keys = new ArrayList<>();
        permutation(new char[keySize], 0, alphabet, keys);
        for (String key : keys) {
            String probableLine = currentCryptographer.decrypt(fileName, key);
            if (probableLine.contains(probableWord)){
                probableKeys.put(key, probableLine);
            }
        }

    }

    private static void permutation(char[] perm, int position, String alphabet, ArrayList<String> keys) {
        if (position == perm.length) {
            keys.add(new String(perm));
        } else {
            for (int i = 0; i < alphabet.length(); i++) {
                perm[position] = alphabet.charAt(i);
                permutation(perm, position + 1, alphabet, keys);
            }
        }
    }
}
