package BookingREST.Businesses;

public class BusinesessData {
    public Businesses createBusinesses(int promoter, int strategy, int sector,String alias){
        Businesses businesses = new Businesses();
        businesses.setPromoter_id(promoter);
        businesses.setStrategy_id(strategy);
        businesses.setSector_id(sector);
        businesses.setShort_description("Створимо цей заклад на благо людства");
        businesses.setDescription("Стара піцерія");
        businesses.setAlias(alias);
        businesses.setIs_verified(true);
        businesses.setUrl("https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java/3.12.0");
        businesses.setBackground("media/201805/jAgUxCmshMJuFrFl.png");
        businesses.setLogo("media/201805/jAgUxCmshMJuFrFl.png");
        businesses.setLink_instagram("https://www.instagram.com/original.cv/?hl=ru");
        businesses.setLink_facebook("https://www.facebook.com/max.lutkovec");
        return businesses;
    }
}
