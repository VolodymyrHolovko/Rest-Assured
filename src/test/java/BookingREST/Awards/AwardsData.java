package BookingREST.Awards;

public class AwardsData {
    public Awards createAwards(int businesId,int staffId){
        Awards awards = new Awards();
        awards.setBusiness_id(businesId);
        awards.setStaff_id(staffId);
        awards.setAccrual_at("2017-09-21 17:32:28");
        awards.setCurrency("UAH");
        awards.setAmount(200);
        awards.setComment("Прекрасна нагорода");
        return  awards;
    }

    public Awards updateAwards(int businesId,int staffId){
        Awards awards = new Awards();
        awards.setBusiness_id(businesId);
        awards.setStaff_id(staffId);
        awards.setAccrual_at("2017-10-21 17:32:28");
        awards.setCurrency("USD");
        awards.setAmount(2000);
        awards.setComment("Прекрасна нагорода пацанам");
        return  awards;
    }
}
