package BookingREST.AddressProductSalaryStrategy;

public class AddressProductSalaryStrategyData {
    public AddressProductSalaryStrategy createStrategy(int businessId, int salarySchemeId){
        AddressProductSalaryStrategy addressProductSalaryStrategy = new AddressProductSalaryStrategy();
        addressProductSalaryStrategy.setBusiness_id(businessId);
        addressProductSalaryStrategy.setSalary_scheme_id(salarySchemeId);
        addressProductSalaryStrategy.setPercent(20);
        return  addressProductSalaryStrategy;
    }

    public AddressProductSalaryStrategy updateStrategy(){
        AddressProductSalaryStrategy addressProductSalaryStrategy = new AddressProductSalaryStrategy();
        addressProductSalaryStrategy.setPercent(30);
        return  addressProductSalaryStrategy;
    }
}
