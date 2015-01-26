package ru.yandex.autoschool.splinter.models;

import org.javalite.activejdbc.Model;
import ru.yandex.autoschool.splinter.di.SimpleContainer;
import ru.yandex.autoschool.splinter.exceptions.PasswordEncryptException;
import ru.yandex.autoschool.splinter.service.PasswordService;

import java.security.Principal;
import java.sql.Timestamp;

/**
 * @author Etki {@literal <etki@etki.name>}
 * @version %I%, %G%
 * @since 1.0
 */
@SuppressWarnings("unused")
public class User extends Model implements Principal {
    final public static String ROLE_ADMIN = "admin";
    final public static String ROLE_READER = "reader";
    
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
        String plainPassword = getString("password");
        try {
            String encryptedPassword = PasswordService.getInstance().encrypt(plainPassword);
            setString("password", encryptedPassword);
        } catch (PasswordEncryptException e) {
            SimpleContainer.getLogger().error("Error password encrypt for new user");
            // FIXME: return error?
        }
    }

    public static User findByUnknownIdentifierAndPassword(String identifier, String passwordPlain)
    {
        String passwordHash = passwordPlain;
        try {
            passwordHash = PasswordService.getInstance().encrypt(passwordPlain);
        } catch (PasswordEncryptException e) {}

        return findFirst("(login = ? OR email = ?) AND password = ?", identifier, identifier, passwordHash);
    }
    public static User findByLogin(String login)
    {
        return findFirst("login = ?", login);
    }
}
