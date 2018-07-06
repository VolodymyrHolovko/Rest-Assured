package BookingREST.SalaryScheme;

public class SalarySchemeData {
    public SalaryScheme createSalaryScheme(int business, int staff){
        SalaryScheme salaryScheme = new SalaryScheme();
        salaryScheme.setBusiness_id(business);
        salaryScheme.setStaff_id(staff);
        salaryScheme.setStarted_at("2017-08-21 17:32:28");
        return salaryScheme;
    }

    public SalaryScheme updateSalaryScheme(int business, int staff){
        SalaryScheme salaryScheme = new SalaryScheme();
        salaryScheme.setBusiness_id(business);
        salaryScheme.setStaff_id(staff);
        salaryScheme.setStarted_at("2017-09-21 17:32:28");
        return salaryScheme;
    }
}
