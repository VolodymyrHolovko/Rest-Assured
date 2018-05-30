package Booking.BookingSettings;

public class BookingSettingsResponse {
    BookingSettings data;

    public BookingSettings getData() {
        return data;
    }

    public void setData(BookingSettings data) {
        this.data = data;
    }
    BookingSettings workSchedule;

    public BookingSettings getWorkSchedule() {
        return workSchedule;
    }

    public void setWorkSchedule(BookingSettings workSchedule) {
        this.workSchedule = workSchedule;
    }
}
