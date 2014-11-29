package ru.yandex.autoschool.splinter.models;

import org.javalite.activejdbc.Model;

import java.security.Principal;
import java.sql.Timestamp;

/**
 * @author Etki {@literal <etki@etki.name>}
 * @version %I%, %G%
 * @since 1.0
 */
public class User extends Model implements Principal {
    @Override
    public String getName() {
        return getString("name");
    }
    public Integer getId() {
        return getInteger("id");
    }
    public String getLogin() {
        return getString("login");
    }
    public void setLogin(String login) {
        setString("login", login);
    }
    public String getEmail() {
        return getString("email");
    }
    public void setEmail(String email) {
        setString("email", email);
    }
    public String getPassword() {
        return getString("password");
    }
    public void setPassword(String password) {
        setString("password", password);
    }
    public String getRole() {
        return getString("role");
    }
    public void setRole(String role) {
        setString("role", role);
    }
    public Timestamp getCreatedAt() {
        return getTimestamp("created_at");
    }
    public Timestamp getUpdatedAt() {
        return getTimestamp("updated_at");
    }
    public void setName(String name) {
        setString("name", name);
    }
    public String getSirname() {
        return getString("sirname");
    }
    public void setSirname(String sirname) {
        setString("sirname", sirname);
    }

    public String getSessionHash() {
        return getString("session_hash");
    }
    public void setSessionHash(String session_hash) {
        setString("name", session_hash);
    }
    public void beforeCreate() {
        encryptPassword();
    }
    private void encryptPassword() {
        // more functionality to come
        // ¯\_(ツ)_/¯
    }
}
