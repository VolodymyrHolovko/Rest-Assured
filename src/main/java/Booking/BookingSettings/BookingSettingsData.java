package Booking.BookingSettings;

import java.util.ArrayList;
import java.util.List;

public class BookingSettingsData {
    public BookingSettings addBookingSettings() {
        BookingSettings addSettings = new BookingSettings();
        addSettings.setAutomaticBookingConfirmation(false);
        addSettings.setAutomaticBookingRejection(true);
        addSettings.setMaxAmountOfDaysAdvanceForBooking(30);
        addSettings.setAvailableTimeForEditBooking(10);
        addSettings.setMaxAmountPeopleForBooking(10);
        addSettings.setAvailableTimeForCreateBooking(29);
        addSettings.setMinimumDurationOfBooking(5);
        addSettings.setServiceTimeAfterBookingEnd(10);
        addSettings.setBookingIsAllowed(true);
        addSettings.setIdTimeZone("Europe/Kiev");
        addSettings.setAddressId(2);

        List<BookingSettings.WorkSchedule> workScheduleList = new ArrayList<BookingSettings.WorkSchedule>();
        BookingSettings.WorkSchedule workSchedule = new BookingSettings.WorkSchedule();
        workSchedule.setDay(1);
        workSchedule.setStartTime(28800000);
        workSchedule.setEndTime(86460000);
        addSettings.setWorkScheduleList(workSchedule);
        return addSettings;
    }
}
