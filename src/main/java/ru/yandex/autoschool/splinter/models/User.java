package ru.yandex.autoschool.splinter.models;

import org.javalite.activejdbc.Model;

import java.sql.Timestamp;

/**
 * @author Etki {@literal <etki@etki.name>}
 * @version %I%, %G%
 * @since 1.0
 */
@SuppressWarnings("unused")
public class User extends Model {
    public String getLogin() {
        return getString("login");
    }
    public void setLogin(String login) {
        setString("login", login);
    }
    public String getPassword() {
        return getString("password");
    }
    public void setPassword(String password) {
        setString("password", password);
    }
    public String getNote() {
        return getString("note");
    }
    public void setNote(String note) {
        setString("note", note);
    }
    public String getNickname() {
        return getString("nickname");
    }
    public void setNickname(String nickname) {
        setString("nickname", nickname);
    }
    public String getRole() {
        return getString("role");
    }
    public void setRole(String role) {
        setString("role", role);
    }
    public Timestamp getRegistrationDate() {
        return getTimestamp("registration_date");
    }
    public void beforeCreate() {
        encryptPassword();
    }
    private void encryptPassword() {
        // more functionality to come
        // ¯\_(ツ)_/¯
    }
}
