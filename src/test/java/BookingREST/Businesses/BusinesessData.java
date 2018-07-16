package BookingREST.Businesses;

public class BusinesessData {
    public Businesses createBusinesses(int promoter, int strategy, int sector,String alias){
        Businesses businesses = new Businesses();
        businesses.setPromoter_id(promoter);
        businesses.setStrategy_id(strategy);
        businesses.setSector_id(sector);
        businesses.setName("maximum");
        businesses.setShort_description("Створимо цей заклад на благо людства");
        businesses.setDescription("Стара піцерія");
        businesses.setAlias(alias);
        businesses.setIs_verified(false);
        businesses.setUrl("https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java/3.12.0");
        businesses.setBackground("https://staging.eservia.com/image/media/201805/jAgUxCmshMJuFrFl.png");
        businesses.setLogo("https://staging.eservia.com/image/media/201805/jAgUxCmshMJuFrFl.png");
        businesses.setLink_instagram("https://www.instagram.com/original.cv/?hl=ru");
        businesses.setLink_facebook("https://www.facebook.com/max.lutkovec");
        return businesses;
    }

    public Businesses updateBusiness(){
        Businesses businesses = new Businesses();
        businesses.setName("maximum1");
        businesses.setShort_description("Створимо цей заклад на благо людства1");
        businesses.setDescription("Стара піцерія1");
        businesses.setUrl("https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java1/3.12.0");
        businesses.setBackground("https://staging.eservia.com/image/media/2018051/jAgUxCmshMJuFrFl.png");
        businesses.setLogo("https://staging.eservia.com/image/media/2018051/jAgUxCmshMJuFrFl.png");
        businesses.setLink_instagram("https://www.instagram.com/original.cv1/?hl=ru");
        businesses.setLink_facebook("https://www.facebook.com/max.lutkovec1");
        return  businesses;
    }

    public Businesses promoterBusinesses( int strategy, int sector,String alias){
        Businesses businesses = new Businesses();
        businesses.setStrategy_id(strategy);
        businesses.setSector_id(sector);
        businesses.setName("maximum");
        businesses.setShort_description("Створимо цей заклад на благо людства");
        businesses.setDescription("Стара піцерія");
        businesses.setAlias(alias);
        businesses.setIs_verified(false);
        businesses.setUrl("https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java/3.12.0");
        businesses.setBackground("https://staging.eservia.com/image/media/201805/jAgUxCmshMJuFrFl.png");
        businesses.setLogo("https://staging.eservia.com/image/media/201805/jAgUxCmshMJuFrFl.png");
        businesses.setLink_instagram("https://www.instagram.com/original.cv/?hl=ru");
        businesses.setLink_facebook("https://www.facebook.com/max.lutkovec");
        return businesses;
    }
}
