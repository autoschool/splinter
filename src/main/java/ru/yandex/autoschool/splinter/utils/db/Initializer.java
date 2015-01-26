package ru.yandex.autoschool.splinter.utils.db;

import ru.yandex.autoschool.splinter.exceptions.PasswordEncryptException;
import ru.yandex.autoschool.splinter.models.Comment;
import ru.yandex.autoschool.splinter.models.Post;
import ru.yandex.autoschool.splinter.models.User;
import ru.yandex.autoschool.splinter.service.PasswordService;

/**
 * @author Etki {@literal <etki@etki.name>}
 * @version %I%, %G%
 * @since 1.0
 */
public class Initializer {
    public static void initialize()
    {
        initUser("admin", "admin@splinter.org", "password", "Glorious", "Admin", User.ROLE_ADMIN);
        initUser("reader", "reader@splinter.org", "password", "Pathetic", "Reader", User.ROLE_READER);
        initPost("Just a test post", "Can you read me?");
    }
    private static Integer initUser(String login, String email, String password, String name, String surname, String role) {
        User user = User.findByLogin(login);
        if (user == null) {
            user = new User();
            user.setLogin(login);
            user.setEmail(email);
            user.setName(name);
            user.setSirname(surname);
            user.setRole(role);
            try {
                user.setPassword(PasswordService.getInstance().encrypt(password));
            } catch (PasswordEncryptException e) {
                user.setPassword(password);
            }
            user.saveIt();
        }
        return user.getId();
    }
    private static Integer initPost(String title, String content) {
        Post post = Post.findFirst("title = ?", title);
        if (post == null) {
            post = new Post();
            post.setTitle(title);
            post.setContent(content);
            post.saveIt();
        }
        return post.getId();
    }
    @SuppressWarnings("unused")
    private static Integer initComment(Integer postId, String content)
    {
        Comment comment = Comment.findFirst("post_id = ? AND content = ?", postId, content);
        if (comment == null) {
            comment = new Comment();
            comment.setContent(content);
            comment.saveIt();
        }
        return (Integer) comment.getId();
    }
}
