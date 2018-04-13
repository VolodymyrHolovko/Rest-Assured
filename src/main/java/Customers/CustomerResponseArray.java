package Customers;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class CustomerResponseArray {
    ArrayList<Customers> data;
    @JsonProperty
    private  boolean isSuccess;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public ArrayList<Customers> getData() {
        return data;
    }

    public void setData(ArrayList<Customers> data) {
        this.data = data;
    }
}
