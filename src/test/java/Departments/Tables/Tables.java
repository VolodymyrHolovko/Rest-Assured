package Departments.Tables;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Tables {
    private String code;
    @JsonProperty ("bookingAvailable")
    private boolean bookingAvailable;
    private int departmentId;
    private int capacity;
    private int id;
    private String beaconId;
    private String errorDescription;
    private int statusId;

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public String getBeaconId() {
        return beaconId;
    }

    public void setBeaconId(String beaconId) {
        this.beaconId = beaconId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public boolean isBookingAvailable() {
        return bookingAvailable;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setBookingAvailable(boolean bookingAvailable) {
        this.bookingAvailable = bookingAvailable;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
