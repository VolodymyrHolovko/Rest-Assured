package Booking;

import java.util.List;

public class Booking {
    private int departmentId;
    private List<Integer> tableIds;
    private int peopleCount;
    private String requestDescription;
    private String bookingDateTime;
    private String bookingEndTime;
    private int addressId;

    public int getDepartmentId() {
        return departmentId;
    }

    public List<Integer> getTableIds() {
        return tableIds;
    }

    public int getPeopleCount() {
        return peopleCount;
    }

    public String getRequestDescription() {
        return requestDescription;
    }

    public String getBookingDateTime() {
        return bookingDateTime;
    }

    public String getBookingEndTime() {
        return bookingEndTime;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public void setTableIds(List<Integer> tableIds) {
        this.tableIds = tableIds;
    }

    public void setPeopleCount(int peopleCount) {
        this.peopleCount = peopleCount;
    }

    public void setRequestDescription(String requestDescription) {
        this.requestDescription = requestDescription;
    }

    public void setBookingDateTime(String bookingDateTime) {
        this.bookingDateTime = bookingDateTime;
    }

    public void setBookingEndTime(String bookingEndTime) {
        this.bookingEndTime = bookingEndTime;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }
}
