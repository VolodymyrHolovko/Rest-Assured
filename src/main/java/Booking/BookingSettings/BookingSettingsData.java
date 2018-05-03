package Booking.BookingSettings;

import java.util.ArrayList;
import java.util.List;

public class BookingSettingsData {
    public BookingSettings addBookingSettings() {
        BookingSettings addSettings = new BookingSettings();
        addSettings.setAutomaticBookingConfirmation(false);
        addSettings.setAutomaticBookingRejection(false);
        addSettings.setMaxAmountOfDaysAdvanceForBooking(50);
        addSettings.setAvailableTimeForEditBooking(1800010);
        addSettings.setMaxAmountPeopleForBooking(12);
        addSettings.setAvailableTimeForCreateBooking(3600015);
        addSettings.setMinimumDurationOfBooking(3600016);
        addSettings.setServiceTimeAfterBookingEnd(300017);
        addSettings.setBookingIsAllowed(true);
        addSettings.setIdTimeZone("Europe/Kiev");
        addSettings.setAddressId(2);

        List<BookingSettings.WorkSchedule> workScheduleList = new ArrayList<BookingSettings.WorkSchedule>();

        BookingSettings.WorkSchedule workSchedule = new BookingSettings.WorkSchedule();
        workSchedule.setDay(1);
        workSchedule.setStartTime(28800000);
        workSchedule.setEndTime(86460000);
        workScheduleList.add(workSchedule);

        BookingSettings.WorkSchedule workSchedule2 = new BookingSettings.WorkSchedule();
        workSchedule2.setDay(2);
        workSchedule2.setStartTime(28800088);
        workSchedule2.setEndTime(86460088);
        workScheduleList.add(workSchedule2);

        BookingSettings.WorkSchedule workSchedule3 = new BookingSettings.WorkSchedule();
        workSchedule3.setDay(3);
        workSchedule3.setStartTime(28800085);
        workSchedule3.setEndTime(86460085);
        workScheduleList.add(workSchedule3);


        BookingSettings.WorkSchedule workSchedule4 = new BookingSettings.WorkSchedule();
        workSchedule4.setDay(4);
        workSchedule4.setStartTime(28800084);
        workSchedule4.setEndTime(86460084);
        workScheduleList.add(workSchedule4);


        BookingSettings.WorkSchedule workSchedule5 = new BookingSettings.WorkSchedule();
        workSchedule5.setDay(5);
        workSchedule5.setStartTime(28800087);
        workSchedule5.setEndTime(86460087);
        workScheduleList.add(workSchedule5);

        BookingSettings.WorkSchedule workSchedule6 = new BookingSettings.WorkSchedule();
        workSchedule6.setDay(6);
        workSchedule6.setStartTime(28800084);
        workSchedule6.setEndTime(86460084);
        workScheduleList.add(workSchedule6);

        BookingSettings.WorkSchedule workSchedule7 = new BookingSettings.WorkSchedule();
        workSchedule7.setDay(7);
        workSchedule7.setStartTime(28800064);
        workSchedule7.setEndTime(86460064);
        workScheduleList.add(workSchedule7);

        addSettings.setWorkSchedule(workScheduleList);
        return addSettings;
    }
    public BookingSettings updateBookingSettings(int id) {
        BookingSettings updateSettings = new BookingSettings();
        updateSettings.setId(id);
        updateSettings.setAutomaticBookingConfirmation(false);
        updateSettings.setAutomaticBookingRejection(false);
        updateSettings.setMaxAmountOfDaysAdvanceForBooking(50);
        updateSettings.setAvailableTimeForEditBooking(1800000);
        updateSettings.setMaxAmountPeopleForBooking(12);
        updateSettings.setAvailableTimeForCreateBooking(3600015);
        updateSettings.setMinimumDurationOfBooking(3600000);
        updateSettings.setServiceTimeAfterBookingEnd(300000);
        updateSettings.setBookingIsAllowed(true);
        updateSettings.setIdTimeZone("Europe/Kiev");
        updateSettings.setAddressId(2);

        List<BookingSettings.WorkSchedule> workScheduleList = new ArrayList<BookingSettings.WorkSchedule>();

        BookingSettings.WorkSchedule workSchedule = new BookingSettings.WorkSchedule();
        workSchedule.setDay(1);
        workSchedule.setStartTime(28800000);
        workSchedule.setEndTime(86460000);
        workScheduleList.add(workSchedule);

        BookingSettings.WorkSchedule workSchedule2 = new BookingSettings.WorkSchedule();
        workSchedule2.setDay(2);
        workSchedule2.setStartTime(28800088);
        workSchedule2.setEndTime(86460088);
        workScheduleList.add(workSchedule2);

        BookingSettings.WorkSchedule workSchedule3 = new BookingSettings.WorkSchedule();
        workSchedule3.setDay(3);
        workSchedule3.setStartTime(28800085);
        workSchedule3.setEndTime(86460085);
        workScheduleList.add(workSchedule3);

        BookingSettings.WorkSchedule workSchedule4 = new BookingSettings.WorkSchedule();
        workSchedule4.setDay(4);
        workSchedule4.setStartTime(28800084);
        workSchedule4.setEndTime(86460084);
        workScheduleList.add(workSchedule4);

        BookingSettings.WorkSchedule workSchedule5 = new BookingSettings.WorkSchedule();
        workSchedule5.setDay(5);
        workSchedule5.setStartTime(28800087);
        workSchedule5.setEndTime(86460087);
        workScheduleList.add(workSchedule5);

        BookingSettings.WorkSchedule workSchedule6 = new BookingSettings.WorkSchedule();
        workSchedule6.setDay(6);
        workSchedule6.setStartTime(28800084);
        workSchedule6.setEndTime(86460084);
        workScheduleList.add(workSchedule6);

        BookingSettings.WorkSchedule workSchedule7 = new BookingSettings.WorkSchedule();
        workSchedule7.setDay(7);
        workSchedule7.setStartTime(28800064);
        workSchedule7.setEndTime(86460064);
        workScheduleList.add(workSchedule7);

        updateSettings.setWorkSchedule(workScheduleList);
        return updateSettings;
    }
}
