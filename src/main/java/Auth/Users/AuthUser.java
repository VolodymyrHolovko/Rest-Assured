package Auth.Users;

public class AuthUser {
    private String password;
    private String username;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername(String s) {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
