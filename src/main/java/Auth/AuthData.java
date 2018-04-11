package Auth;

public class AuthData {
    public Auth password(){
       Auth auth = new Auth();
       auth.setUsername("cadmin@eservia.com");
       auth.setPassword("manager");
       auth.setAuthType("WebAdminPanel");
       return  auth;
    }
    public Auth getToken(String sessionId){
        Auth auth = new Auth();
        auth.setPositionId(1);
        auth.setSessionId(sessionId);
        return  auth;
    }
}
