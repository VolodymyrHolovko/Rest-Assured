package Customers;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CustomerObjectResponse {
    List<CustomersObject> data;

    public List<CustomersObject> getData() {
        return data;
    }

    public void setData(List<CustomersObject> data) {
        this.data = data;
    }
    @JsonProperty
    private  boolean isSuccess;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }
}
