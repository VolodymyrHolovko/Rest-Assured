package BookingREST.Strategy;

public class StrategyData {
   public Strategy addPromoters(String name) {
       Strategy addStrategy = new Strategy();
        addStrategy.setStrategy(name);
        addStrategy.setStatus(0);
       return addStrategy;
   }

}
