package ru.mipt.cyberSecurityHW;

import lombok.Data;
import ru.mipt.cyberSecurityHW.cryptographers.CesarCryptographer;
import ru.mipt.cyberSecurityHW.cryptographers.Cryptographer;
import ru.mipt.cyberSecurityHW.cryptographers.ShuffleCryptographer;
import ru.mipt.cyberSecurityHW.cryptographers.VigenereCryptographer;

@Data
public class CryptographerTypeHandler {
    public Cryptographer chooseCryptographer(String cryptographerType) {
        if (cryptographerType.toLowerCase().contains("cesar")){
            return new CesarCryptographer();
        } else if(cryptographerType.toLowerCase().contains("shuffle")){
            return new ShuffleCryptographer();
        } else if(cryptographerType.toLowerCase().contains("vigenere")){
            return new VigenereCryptographer();
        }
        return null;
    }
}
