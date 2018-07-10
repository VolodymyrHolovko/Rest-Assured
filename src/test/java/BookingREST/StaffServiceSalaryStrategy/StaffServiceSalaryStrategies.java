package BookingREST.StaffServiceSalaryStrategy;

public class StaffServiceSalaryStrategies {
    private int business_id;
    private int salary_scheme_id;
    private int percent;
    private int id;
    private String deleted_at;

    public int getBusiness_id() {
        return business_id;
    }

    public void setBusiness_id(int business_id) {
        this.business_id = business_id;
    }

    public int getSalary_scheme_id() {
        return salary_scheme_id;
    }

    public void setSalary_scheme_id(int salary_scheme_id) {
        this.salary_scheme_id = salary_scheme_id;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(String deleted_at) {
        this.deleted_at = deleted_at;
    }
}
