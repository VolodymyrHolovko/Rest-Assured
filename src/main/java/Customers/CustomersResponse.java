package Customers;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomersResponse {
    Customers data;
    Customers error;
    @JsonProperty
    private boolean isSuccess;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public Customers getError() {
        return error;
    }

    public void setError(Customers error) {
        this.error = error;
    }

    public Customers getData() {
        return data;
    }

    public void setData(Customers data) {
        this.data = data;
    }
}
