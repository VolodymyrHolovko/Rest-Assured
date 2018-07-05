package BookingREST.WorkingDays;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WorkingDays {
    private String rule;
    private int business_id;
    private String object_type;
    private int object_id;
    @JsonProperty("is_exclusion")
    private boolean is_exclusion;
    private int id;
    private String updated_at;

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        return "WorkingDays{" +
                "rule='" + rule + '\'' +
                ", business_id=" + business_id +
                ", object_type='" + object_type + '\'' +
                ", object_id=" + object_id +
                ", is_exclusion=" + is_exclusion +
                ", id=" + id +
                ", updated_at='" + updated_at + '\'' +
                '}';
    }
}
