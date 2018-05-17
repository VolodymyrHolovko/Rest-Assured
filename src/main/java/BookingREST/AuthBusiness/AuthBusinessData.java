package BookingREST.AuthBusiness;

public class AuthBusinessData {
    public AuthBusiness authBisiness() {
        AuthBusiness authAdmin = new AuthBusiness();
        authAdmin.setLogin("info@eservia.com");
        authAdmin.setPassword("12345678");
        return authAdmin;

    }
}
