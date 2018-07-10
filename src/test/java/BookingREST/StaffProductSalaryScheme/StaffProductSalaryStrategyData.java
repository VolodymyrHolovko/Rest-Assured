package BookingREST.StaffProductSalaryScheme;

import BookingREST.AddressProductSalaryStrategy.AddressProductSalaryStrategy;

public class StaffProductSalaryStrategyData {
    public StaffProductSalaryStrategy createStaffStrategy(int businessId, int salarySchemeId){
        StaffProductSalaryStrategy staffProductSalaryStrategy = new StaffProductSalaryStrategy();
        staffProductSalaryStrategy.setBusiness_id(businessId);
        staffProductSalaryStrategy.setSalary_scheme_id(salarySchemeId);
        staffProductSalaryStrategy.setPercent(20);
        return  staffProductSalaryStrategy;
    }

    public StaffProductSalaryStrategy updateStrategy(){
        StaffProductSalaryStrategy staffProductSalaryStrategy= new StaffProductSalaryStrategy();
        staffProductSalaryStrategy.setPercent(30);
        return  staffProductSalaryStrategy;
    }
}
