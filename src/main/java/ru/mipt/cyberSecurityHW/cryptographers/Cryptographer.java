package ru.mipt.cyberSecurityHW.cryptographers;

public interface Cryptographer {
    public String encrypt(String filename, String key);
    public String decrypt(String filename, String key);
}
