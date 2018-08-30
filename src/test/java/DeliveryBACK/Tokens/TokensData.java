package DeliveryBACK.Tokens;

public class TokensData {
    public Tokens getLogisticsToken(){
        Tokens tokens = new Tokens();
        tokens.setLogin("logist@gmail.com");
        tokens.setPassword("111zzz111");
        return tokens;
    }

    public Tokens getAdminToken(){
        Tokens tokens = new Tokens();
        tokens.setLogin("admin@gmail.com");
        tokens.setPassword("111zzz111");
        return tokens;
    }

    public Tokens getSuperAdminToken(){
        Tokens tokens = new Tokens();
        tokens.setLogin("superadmin@gmail.com");
        tokens.setPassword("111zzz111");
        return tokens;
    }

    public Tokens getCourier(){
        Tokens tokens = new Tokens();
        tokens.setLogin("+380961122333");
        tokens.setPassword("111zzz111");
        return tokens;
    }
}
