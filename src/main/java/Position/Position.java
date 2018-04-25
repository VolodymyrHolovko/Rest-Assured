package Position;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import org.json.simple.JSONObject;

import java.util.List;

public class Position {
    private String name;
    private String shortName;
    private String comment;
    private String scheduleType;
    private int salary;
    private int prepaidExpense;
    private int id;
    private String errorDescription;
    private String errorSource;


    public String getErrorSource() {
        return errorSource;
    }

    public void setErrorSource(String errorSource) {
        this.errorSource = errorSource;
    }

    public String getErrorDescription() {
        return errorDescription;
    }


    public String getName() { return name; };
    public void setName(String name) {this.name = name;};
    public String getShortName() { return shortName; };
    public void setShortName(String shortName) {this.shortName = shortName;};
    public String getComment() { return comment; };
    public void setComment(String comment) {this.comment = comment;};
    public String getScheduleType() { return scheduleType; };
    public void setScheduleType(String scheduleType) {this.scheduleType = scheduleType;};
    public int getSalary() { return salary; };
    public void setSalary(int salary) {this.salary = salary;};
    public int getPrepaidExpense() { return prepaidExpense; };
    public void setPrepaidExpense(int prepaidExpense) {this.prepaidExpense = prepaidExpense;};
    public int getId() {return id;};
    public void setId(int id) {this.id = id;};




        }
