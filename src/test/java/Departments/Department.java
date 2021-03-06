package Departments;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Department {
    private int typeId;
    @JsonProperty("isMain")
    private boolean isMain;
    @JsonProperty("isActive")
    private boolean isActive;
    private String name;
    private int addressId;
    private List<Object> tables;
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

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<Object> getTables() {
        return tables;
    }

    public void setTables(List<Object> tables) {
        this.tables = tables;
    }
}
