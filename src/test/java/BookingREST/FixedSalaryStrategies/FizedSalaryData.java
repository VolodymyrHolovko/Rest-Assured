package BookingREST.FixedSalaryStrategies;

public class FizedSalaryData {
    public FixedSalary createFixedSalary(int businesId,int salaryScheme,int aggregation){
        FixedSalary fixedSalary = new FixedSalary();
        fixedSalary.setBusiness_id(businesId);
        fixedSalary.setSalary_scheme_id(salaryScheme);
        fixedSalary.setAggregation(aggregation);
        fixedSalary.setCurrency("USD");
        fixedSalary.setAmount(20);
        return fixedSalary;
    }

    public FixedSalary updateFixedSalary(int businesId,int salaryScheme,int aggregation){
        FixedSalary fixedSalary = new FixedSalary();
        fixedSalary.setBusiness_id(businesId);
        fixedSalary.setSalary_scheme_id(salaryScheme);
        fixedSalary.setAggregation(aggregation);
        fixedSalary.setCurrency("UAH");
        fixedSalary.setAmount(30);
        return fixedSalary;
    }
}
