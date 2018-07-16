package BookingREST.Promoter;

public class PromoterData {
    public Promoter addPromoters(String firstName, String lastName, String email, String phone) {
        Promoter addPromoter = new Promoter();
        addPromoter.setFirst_name(firstName);
        addPromoter.setLast_name(lastName);
        addPromoter.setEmail(email);
        addPromoter.setPhone(phone);
        addPromoter.setPassword("12345678");
        addPromoter.setPhoto("https://staging.eservia.com/image/media/201805/U82NZUzFnOvCzOSf.jpg");
        addPromoter.setStatus(1);
        return  addPromoter;
    }
    public Promoter updatePromoters(String firstNameUpdate, String lastNameUpdate, String emailUpdate, String phoneUpdate) {
        Promoter updatePromoter = new Promoter();
        updatePromoter.setFirst_name(firstNameUpdate);
        updatePromoter.setLast_name(lastNameUpdate);
        updatePromoter.setEmail(emailUpdate);
        updatePromoter.setPhone(phoneUpdate);
        updatePromoter.setPhoto("https://staging.eservia.com/image/media/201805/5CMjoBr5A3tvTHRv");
        return updatePromoter;
    }
    public Promoter changePasswords() {
        Promoter changePassword = new Promoter();
        changePassword.setOld_password("12345678");
        changePassword.setPassword("123456789");
        return changePassword;
    }
}
