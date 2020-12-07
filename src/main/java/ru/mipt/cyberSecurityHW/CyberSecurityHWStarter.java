package ru.mipt.cyberSecurityHW;

import ru.mipt.cyberSecurityHW.cryptographers.Cryptographer;
import ru.mipt.cyberSecurityHW.decryptors.MoveProbableWordDecryptor;

import java.util.Scanner;

import static ru.mipt.cyberSecurityHW.CryptographerTypeHandler.chooseCryptographer;

public class CyberSecurityHWStarter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter path to your file: ");
        String inputFilePath = scanner.nextLine();
        System.out.println("Witch encryption you want to use (Shuffle, Cesar, Vigenere)? ");
        String cryptographerType = scanner.nextLine();
        Cryptographer currentCryptographer = chooseCryptographer(cryptographerType);
        System.out.println("Do you want to encrypt?(y/n) ");
        String encryptOrDecrypt = scanner.nextLine();
        if (encryptOrDecrypt.toLowerCase().equals("y")){
            System.out.println("Enter cipher key: ");
            String key = scanner.nextLine();
            if (currentCryptographer != null) {
                System.out.println(currentCryptographer.encrypt(inputFilePath, key));
            }
        } else {
            System.out.println("Do you know the key?(y/n) ");
            String answer = scanner.nextLine();
            if (answer.toLowerCase().equals("y")) {
                System.out.println("Enter cipher key: ");
                String key = scanner.nextLine();
                if (currentCryptographer != null) {
                    System.out.println(currentCryptographer.decrypt(inputFilePath, key));
                }
            } else {
                System.out.println("Enter probable word: ");
                String probableWord = scanner.nextLine();
                System.out.println("Enter key length: ");
                int keySize = scanner.nextInt();
                MoveProbableWordDecryptor moveProbableWordDecryptor = new MoveProbableWordDecryptor();
                moveProbableWordDecryptor.decrypt(inputFilePath, probableWord, keySize, cryptographerType)
                        .entrySet()
                        .forEach(System.out::println);
            }
        }
    }
}
