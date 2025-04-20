package com.example.bank_card_management.service;

import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Service
public class EncryptionService {
    private static final String SECRET_KEY = "1234567890123456";

    private final Cipher encryptCipher;
    private final Cipher decryptCipher;

    public EncryptionService() throws Exception
    {
        SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
        encryptCipher = Cipher.getInstance("AES");
        encryptCipher.init(Cipher.ENCRYPT_MODE, key);

        decryptCipher = Cipher.getInstance("AES");
        decryptCipher.init(Cipher.DECRYPT_MODE, key);
    }

    public String encrypt(String plainText) throws Exception
    {
        byte[] encrypted = encryptCipher.doFinal(plainText.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }

    public String decrypt(String encryptedText) throws Exception
    {
        byte[] decoded = Base64.getDecoder().decode(encryptedText);
        return new String(decryptCipher.doFinal(decoded));
    }
}
