package ru.yandex.autoschool.splinter.models;

import org.javalite.activejdbc.Model;

import java.sql.Timestamp;

/**
 * @author Etki {@literal <etki@etki.name>}
 * @version %I%, %G%
 * @since 1.0
 */
@SuppressWarnings("unused")
public class Comment extends Model {
    public String getContent() {
        return getString("content");
    }
    public void setContent(String content) {
        setString("content", content);
    }
    public Timestamp getCreatedAt() {
        return getTimestamp("created_at");
    }
}
