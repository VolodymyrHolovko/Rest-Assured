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
    private List <WorkSchedule> workSchedule;
    private WorkSchedule workScheduleList;
    private WorkSchedule workScheduleList2;
    private WorkSchedule workScheduleList3;
    private WorkSchedule workScheduleList4;
    private WorkSchedule workScheduleList5;
    private WorkSchedule workScheduleList6;
    private WorkSchedule workScheduleList7;

    public List<WorkSchedule> getWorkSchedule() {
        return workSchedule;
    }

    public void setWorkSchedule(List<WorkSchedule> workSchedule) {
        this.workSchedule = workSchedule;
    }

    public WorkSchedule getWorkScheduleList3() {
        return workScheduleList3;
    }

    public void setWorkScheduleList3(WorkSchedule workScheduleList3) {
        this.workScheduleList3 = workScheduleList3;
    }

    public WorkSchedule getWorkScheduleList4() {
        return workScheduleList4;
    }

    public void setWorkScheduleList4(WorkSchedule workScheduleList4) {
        this.workScheduleList4 = workScheduleList4;
    }

    public WorkSchedule getWorkScheduleList5() {
        return workScheduleList5;
    }

    public void setWorkScheduleList5(WorkSchedule workScheduleList5) {
        this.workScheduleList5 = workScheduleList5;
    }

    public WorkSchedule getWorkScheduleList6() {
        return workScheduleList6;
    }

    public void setWorkScheduleList6(WorkSchedule workScheduleList6) {
        this.workScheduleList6 = workScheduleList6;
    }

    public WorkSchedule getWorkScheduleList7() {
        return workScheduleList7;
    }

    public void setWorkScheduleList7(WorkSchedule workScheduleList7) {
        this.workScheduleList7 = workScheduleList7;
    }

    public WorkSchedule getWorkScheduleList2() {
        return workScheduleList2;
    }

    public void setWorkScheduleList2(WorkSchedule workScheduleList2) {
        this.workScheduleList2 = workScheduleList2;
    }

    public WorkSchedule getWorkScheduleList() {
        return workScheduleList;
    }

    public void setWorkScheduleList(WorkSchedule workScheduleList) {
        this.workScheduleList = workScheduleList;
    }

    public int getId() {
        return id;
    }

    public boolean isAutomaticBookingConfirmation() {
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
