package dto;

/**
 * Created by laonen on 25.01.2017.
 */
public class LoginDto {
    private long id;
    private String username;
    private String password;

    public LoginDto(long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public LoginDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
