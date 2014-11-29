package ru.yandex.autoschool.splinter.models;

import org.javalite.activejdbc.Model;

import java.sql.Timestamp;

/**
 * @author Etki {@literal <etki@etki.name>}
 * @version %I%, %G%
 * @since 1.0
 */
@SuppressWarnings("unused")
public class Post extends Model {
    public Integer getId() {
        return getInteger("id");
    }
    public String getTitle() {
        return getString("title");
    }
    public void setTitle(String title) {
        setString("title", title);
    }
    public String getContent() {
        return getString("content");
    }
    public void setContent(String content) {
        setString("content", content);
    }
    public Timestamp getUpdatedAt() {
        return getTimestamp("updated_at");
    }
    public Timestamp getCreatedAt() {
        return getTimestamp("created_at");
    }
}
