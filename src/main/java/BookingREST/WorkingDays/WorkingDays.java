package BookingREST.WorkingDays;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WorkingDays {
    private String rule;
    private int business_id;
    private String object_type;
    private int object_id;
    @JsonProperty("is_exclusion")
    private boolean is_exclusion;

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public int getBusiness_id() {
        return business_id;
    }

    public void setBusiness_id(int business_id) {
        this.business_id = business_id;
    }

    public String getObject_type() {
        return object_type;
    }

    public void setObject_type(String object_type) {
        this.object_type = object_type;
    }

    public int getObject_id() {
        return object_id;
    }

    public void setObject_id(int object_id) {
        this.object_id = object_id;
    }

    public boolean isIs_exclusion() {
        return is_exclusion;
    }

    public void setIs_exclusion(boolean is_exclusion) {
        this.is_exclusion = is_exclusion;
    }
}
