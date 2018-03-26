package Departments;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Department {
    private int typeId;
    @JsonProperty("isMain")
    private boolean isMain;
    @JsonProperty("isActive")
    private boolean isActive;
    private String name;
    private int addressId;
    private List<Integer> tables;
    private int id;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Integer> getTables() {
        return tables;
    }

    public int getTypeId() {
        return typeId;
    }

    public boolean isMain() {
        return isMain;
    }

    public boolean isActive() {
        return isActive;
    }

    public String getName() {
        return name;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public void setMain(boolean main) {
        isMain = main;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public void setTables(List<Integer> tables) {
        this.tables = tables;
    }
}
