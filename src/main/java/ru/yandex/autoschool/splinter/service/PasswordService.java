package ru.yandex.autoschool.splinter.service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import sun.misc.BASE64Encoder;
import ru.yandex.autoschool.splinter.exceptions.PasswordEncryptException;

/**
 * Created by pacahon on 21.01.15.
 */
public final class PasswordService {
    private static PasswordService instance;

    private PasswordService() {}

    public static synchronized PasswordService getInstance() {
        if(instance == null) {
            instance = new PasswordService();
        }

        return instance;
    }

    public synchronized String encrypt(String plaintext) throws PasswordEncryptException {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA");
        }
        catch(NoSuchAlgorithmException e) {
            throw new PasswordEncryptException(e.getMessage());
        }
        try {
            md.update(plaintext.getBytes("UTF-8"));
        }
        catch(UnsupportedEncodingException e) {
            throw new PasswordEncryptException(e.getMessage());
        }

        byte raw[] = md.digest();
        return (new BASE64Encoder()).encode(raw);
    }
}
