package Booking.BookingSettings;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class BookingSettings {
    private int id;
    @JsonProperty("automaticBookingConfirmation")
    private boolean automaticBookingConfirmation;
    @JsonProperty("automaticBookingRejection")
    private boolean automaticBookingRejection;
    private int maxAmountOfDaysAdvanceForBooking;
    private int availableTimeForEditBooking;
    private int maxAmountPeopleForBooking;
    private int availableTimeForCreateBooking;
    private int minimumDurationOfBooking;
    private int serviceTimeAfterBookingEnd;
    @JsonProperty("bookingIsAllowed")
    private boolean bookingIsAllowed;
    private  String idTimeZone;
    private int addressId;
    private List<WorkSchedule> workScheduleList;

    public List<WorkSchedule> getWorkScheduleList() {
        return workScheduleList;
    }

    public void setWorkScheduleList(WorkSchedule workScheduleList) {
        this.workScheduleList = workScheduleList;
    }

    public int getId() {
        return id;
    }

    public boolean isAutomaticBookingConfirmation(boolean b) {
        return automaticBookingConfirmation;
    }

    public boolean isAutomaticBookingRejection() {
        return automaticBookingRejection;
    }

    public int getMaxAmountOfDaysAdvanceForBooking() {
        return maxAmountOfDaysAdvanceForBooking;
    }

    public int getAvailableTimeForEditBooking() {
        return availableTimeForEditBooking;
    }

    public int getMaxAmountPeopleForBooking() {
        return maxAmountPeopleForBooking;
    }

    public int getAvailableTimeForCreateBooking() {
        return availableTimeForCreateBooking;
    }

    public int getMinimumDurationOfBooking() {
        return minimumDurationOfBooking;
    }

    public int getServiceTimeAfterBookingEnd() {
        return serviceTimeAfterBookingEnd;
    }

    public boolean isBookingIsAllowed() {
        return bookingIsAllowed;
    }

    public String getIdTimeZone() {
        return idTimeZone;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAutomaticBookingConfirmation(boolean automaticBookingConfirmation) {
        this.automaticBookingConfirmation = automaticBookingConfirmation;
    }

    public void setAutomaticBookingRejection(boolean automaticBookingRejection) {
        this.automaticBookingRejection = automaticBookingRejection;
    }

    public void setMaxAmountOfDaysAdvanceForBooking(int maxAmountOfDaysAdvanceForBooking) {
        this.maxAmountOfDaysAdvanceForBooking = maxAmountOfDaysAdvanceForBooking;
    }

    public void setAvailableTimeForEditBooking(int availableTimeForEditBooking) {
        this.availableTimeForEditBooking = availableTimeForEditBooking;
    }

    public void setMaxAmountPeopleForBooking(int maxAmountPeopleForBooking) {
        this.maxAmountPeopleForBooking = maxAmountPeopleForBooking;
    }

    public void setAvailableTimeForCreateBooking(int availableTimeForCreateBooking) {
        this.availableTimeForCreateBooking = availableTimeForCreateBooking;
    }

    public void setMinimumDurationOfBooking(int minimumDurationOfBooking) {
        this.minimumDurationOfBooking = minimumDurationOfBooking;
    }

    public void setServiceTimeAfterBookingEnd(int serviceTimeAfterBookingEnd) {
        this.serviceTimeAfterBookingEnd = serviceTimeAfterBookingEnd;
    }

    public void setBookingIsAllowed(boolean bookingIsAllowed) {
        this.bookingIsAllowed = bookingIsAllowed;
    }

    public void setIdTimeZone(String idTimeZone) {
        this.idTimeZone = idTimeZone;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }





    static class  WorkSchedule<W> {
       private int day;
       private int startTime;
       private int endTime;

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public int getStartTime() {
            return startTime;
        }

        public void setStartTime(int startTime) {
            this.startTime = startTime;
        }

        public int getEndTime() {
            return endTime;
        }

        public void setEndTime(int endTime) {
            this.endTime = endTime;
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }

}
