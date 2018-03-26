package Departments;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DepartmentErrors {
    @JsonProperty("isSuccess")
    private boolean data;
    private  String id;
    @JsonProperty("isSuccess")
    private boolean isSuccess;
    public boolean isData() {
        return data;
    }

    public void setData(boolean data) {
        this.data = data;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }
}
