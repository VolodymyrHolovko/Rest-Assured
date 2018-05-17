package BookingREST.Promoter;

public class PromoterData {
    public Promoter addPromoters(String firstName, String lastName, String email, String phone) {
        Promoter addPromoter = new Promoter();
        addPromoter.setFirst_name(firstName);
        addPromoter.setLast_name(lastName);
        addPromoter.setEmail(email);
        addPromoter.setPhone(phone);
        addPromoter.setPassword("12345678");
        addPromoter.setPhoto("http://staging.eservia.com/image/media/201805/3YnaXlHgoss1oryE.jpg");
        addPromoter.setStatus(1);
        return  addPromoter;
    }
}
