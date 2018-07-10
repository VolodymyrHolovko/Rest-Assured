package BookingREST.StaffServiceSalaryStrategy;

import BookingREST.StaffProductSalaryScheme.StaffProductSalaryStrategy;

public class StaffServiceSalaryStrategiesData {
    public StaffServiceSalaryStrategies createStaffServiceStrategy(int businessId, int salarySchemeId){
        StaffServiceSalaryStrategies staffProductSalaryStrategy= new StaffServiceSalaryStrategies();
        staffProductSalaryStrategy.setBusiness_id(businessId);
        staffProductSalaryStrategy.setSalary_scheme_id(salarySchemeId);
        staffProductSalaryStrategy.setPercent(20);
        return  staffProductSalaryStrategy;
    }

    public StaffServiceSalaryStrategies updateStrategy(){
        StaffServiceSalaryStrategies staffProductSalaryStrategy= new StaffServiceSalaryStrategies();
        staffProductSalaryStrategy.setPercent(30);
        return  staffProductSalaryStrategy;
    }
}
