import java.io.Serializable;

/**
 * The Comment class represents a user comment in the application.
 * It implements the Serializable interface to support object serialization.
 */
public class Comment implements Serializable {
    private String user_username; // Username of the user who made the comment
    private String content; // Content of the comment

    public Comment(String user_username, String content) {
        this.user_username = user_username;
        this.content = content;
    }

    public String getUser_username() {
        return user_username;
    }

    public void setUser_username(String user_username) {
        this.user_username = user_username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
