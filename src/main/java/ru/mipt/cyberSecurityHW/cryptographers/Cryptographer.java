package ru.mipt.cyberSecurityHW.cryptographers;

public interface Cryptographer {
    public void encrypt(String filename, String key);
    public void decrypt(String filename, String key);
}
