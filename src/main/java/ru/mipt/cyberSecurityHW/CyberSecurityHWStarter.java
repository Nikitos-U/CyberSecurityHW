package ru.mipt.cyberSecurityHW;

import ru.mipt.cyberSecurityHW.cryptographers.CesarCryptographer;
import ru.mipt.cyberSecurityHW.cryptographers.Cryptographer;
import ru.mipt.cyberSecurityHW.cryptographers.ShuffleCryptographer;
import ru.mipt.cyberSecurityHW.cryptographers.VigenereCryptographer;

import java.util.Scanner;

public class CyberSecurityHWStarter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter path to your file: ");
        String inputFilePath = scanner.nextLine();
        System.out.println("Witch encryption you want to use (Shuffle, Cesar, Vigener)? ");
        String cryptographerType = scanner.nextLine();
        CryptographerTypeHandler cryptographerTypeHandler = new CryptographerTypeHandler();
        Cryptographer currentCryptographer = cryptographerTypeHandler.chooseCryptographer(cryptographerType);
        System.out.println("Enter cipher key: ");
        String key = scanner.nextLine();
        currentCryptographer.encrypt(inputFilePath, key);

        CesarCryptographer cesarCryptographer = new CesarCryptographer();
        ShuffleCryptographer shuffleCryptographer = new ShuffleCryptographer();
        VigenereCryptographer vigenereCryptographer = new VigenereCryptographer();

        cesarCryptographer.encrypt("./src/main/resources/cesarCipherTest.txt", "2");
        cesarCryptographer.decrypt("./src/main/resources/cesarCipherTest.txt", "2");
        shuffleCryptographer.encrypt("./src/main/resources/shuffleCipherTest.txt", "1,3,2,4");
        shuffleCryptographer.decrypt("./src/main/resources/shuffleDecipherTest.txt", "1,3,2,4");
        vigenereCryptographer.encrypt("Nikitos top", "Olololo");
        vigenereCryptographer.decrypt("xtytHzGKECA", "Olololo");
    }
}
