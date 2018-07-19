package BookingREST.Fine;

public class FineData {
    public Fine createFine(int businesId, int staffId){
        Fine fine = new Fine();
        fine.setBusiness_id(businesId);
        fine.setStaff_id(staffId);
        fine.setAccrual_at("2017-09-23 17:32:28");
        fine.setCurrency("UAH");
        fine.setAmount(2000);
        fine.setComment("То всьо шо ти заробив? Не позорься");
        return  fine;
    }

    public Fine updateFine(int businesId, int staffId){
        Fine fine = new Fine();
        fine.setBusiness_id(businesId);
        fine.setStaff_id(staffId);
        fine.setAccrual_at("2017-10-23 17:32:28");
        fine.setCurrency("USD");
        fine.setAmount(200);
        fine.setComment("То всьо шо ти заробив лошара? Не позорься");
        return  fine;
    }

}
