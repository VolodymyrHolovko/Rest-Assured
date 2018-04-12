package Customers;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Customer1 {
    @JsonProperty
    private boolean isBlocked;
    @JsonProperty
    private String id;
    private ArrayList<Integer> businesses;

    public String getId() {
        return id;
    }

    public ArrayList<Integer> getBusinesses() {
        return businesses;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setBusinesses(ArrayList<Integer> businesses) {
        this.businesses = businesses;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }
}
