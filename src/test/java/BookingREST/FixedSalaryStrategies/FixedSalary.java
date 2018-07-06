package BookingREST.FixedSalaryStrategies;

public class FixedSalary {
    private int business_id;
    private int salary_scheme_id;
    private int aggregation;
    private String currency;
    private int amount;
    private  int id;

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

    public int getAggregation() {
        return aggregation;
    }

    public void setAggregation(int aggregation) {
        this.aggregation = aggregation;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
