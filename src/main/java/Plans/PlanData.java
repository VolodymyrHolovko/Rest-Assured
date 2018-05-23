package Plans;

public class PlanData {
    public Plan CreatePlan(){
        Plan plan = new Plan();
        plan.setName("tempPlanName");
        plan.setDescription("tempPlanDescription");
        plan.setPrice(700);
        plan.setCurrency("UAH");
        plan.setInterval(1000);
        plan.setIs_unique(false);
        plan.setStatus(1);
        return plan;
    }

public Plan UpdatePlan(int planId){
    Plan plan = CreatePlan();
    plan.setName("updatedPlanName");
    plan.setDescription("updatedPlanDescription");
    plan.setPrice(800);
    plan.setCurrency("USD");
    plan.setInterval(3000);
    plan.setIs_unique(true);
    return plan;
    }





}
