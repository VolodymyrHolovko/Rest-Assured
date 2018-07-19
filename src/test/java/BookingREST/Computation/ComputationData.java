package BookingREST.Computation;

public class ComputationData {
    public Computation createComputation(int businesId,int staffId){
        Computation computation = new Computation();
        computation.setBusiness_id(businesId);
        computation.setStaff_id(staffId);
        computation.setStarted_at("2017-09-21 14:32:28");
        computation.setEnded_at("2017-09-21 19:32:28");
        computation.setCurrency("UAH");
        return computation;
    }

    public Computation createComputation2(int businesId,int staffId){
        Computation computation = new Computation();
        computation.setBusiness_id(businesId);
        computation.setStaff_id(staffId);
        computation.setStarted_at("2017-09-23 14:32:28");
        computation.setEnded_at("2017-09-23 19:32:28");
        computation.setCurrency("UAH");
        return computation;
    }
}
