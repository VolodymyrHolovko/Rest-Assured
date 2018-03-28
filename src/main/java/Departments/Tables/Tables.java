package Departments.Tables;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Tables {
    private String code;
    @JsonProperty ("bookingAvailable")
    private boolean bookingAvailable;
    private int departmentId;
    private int capacity;
    private int id;

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
