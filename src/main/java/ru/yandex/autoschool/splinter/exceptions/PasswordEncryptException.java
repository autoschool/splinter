package ru.yandex.autoschool.splinter.exceptions;

/**
 * Created by pacahon on 21.01.15.
 */
public class PasswordEncryptException extends Exception{

    public PasswordEncryptException() {
        super();
    }

    public PasswordEncryptException(String msg) {
        super(msg);
    }
}
