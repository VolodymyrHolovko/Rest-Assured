package BookingREST.AuthBusiness;

public class AuthBusiness {
    private String login;
    private String password;
    private String token;
    private String access_token;

    public String getAccess_token() {
        return access_token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
