package BookingREST.AuthBusiness;

public class AuthBusinessData {
    public AuthBusiness authBisiness() {
        AuthBusiness authAdmin = new AuthBusiness();
        authAdmin.setLogin("info@eservia.com");
        authAdmin.setPassword("12345678");
        return authAdmin;
    }
    public AuthBusiness createdPromoter(String username, String password){
        AuthBusiness authAdmin = new AuthBusiness();
        authAdmin.setLogin(username);
        authAdmin.setPassword(password);
        return authAdmin;
    }
}
