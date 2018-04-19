package Booking.BookingSettings;

import java.util.ArrayList;
import java.util.List;

public class BookingSettingsData {
    public BookingSettings addBookingSettings() {
        BookingSettings addSettings = new BookingSettings();
        addSettings.setAutomaticBookingConfirmation(false);
        addSettings.setAutomaticBookingRejection(true);
        addSettings.setMaxAmountOfDaysAdvanceForBooking(60);
        addSettings.setAvailableTimeForEditBooking(1800000);
        addSettings.setMaxAmountPeopleForBooking(10);
        addSettings.setAvailableTimeForCreateBooking(3600000);
        addSettings.setMinimumDurationOfBooking(3600000);
        addSettings.setServiceTimeAfterBookingEnd(300000);
        addSettings.setBookingIsAllowed(true);
        addSettings.setIdTimeZone("Europe/Kiev");
        addSettings.setAddressId(2);

        List<BookingSettings.WorkSchedule> workScheduleList = new ArrayList<BookingSettings.WorkSchedule>();
        BookingSettings.WorkSchedule workSchedule = new BookingSettings.WorkSchedule();
        workSchedule.setDay(1);
        workSchedule.setStartTime(28800000);
        workSchedule.setEndTime(86460000);
        workScheduleList.add(workSchedule);
        addSettings.setWorkScheduleList(workSchedule);

        List<BookingSettings.WorkSchedule> workScheduleList2 = new ArrayList<BookingSettings.WorkSchedule>();
        BookingSettings.WorkSchedule workSchedule2 = new BookingSettings.WorkSchedule();
        workSchedule2.setDay(2);
        workSchedule2.setStartTime(28800088);
        workSchedule2.setEndTime(86460088);
        workScheduleList2.add(workSchedule2);
        addSettings.setWorkScheduleList2(workSchedule2);

        List<BookingSettings.WorkSchedule> workScheduleList3 = new ArrayList<BookingSettings.WorkSchedule>();
        BookingSettings.WorkSchedule workSchedule3 = new BookingSettings.WorkSchedule();
        workSchedule3.setDay(3);
        workSchedule3.setStartTime(28800085);
        workSchedule3.setEndTime(86460085);
        workScheduleList3.add(workSchedule3);
        addSettings.setWorkScheduleList3(workSchedule3);

        List<BookingSettings.WorkSchedule> workScheduleList4 = new ArrayList<BookingSettings.WorkSchedule>();
        BookingSettings.WorkSchedule workSchedule4 = new BookingSettings.WorkSchedule();
        workSchedule4.setDay(4);
        workSchedule4.setStartTime(28800084);
        workSchedule4.setEndTime(86460084);
        workScheduleList4.add(workSchedule4);
        addSettings.setWorkScheduleList4(workSchedule4);

        List<BookingSettings.WorkSchedule> workScheduleList5 = new ArrayList<BookingSettings.WorkSchedule>();
        BookingSettings.WorkSchedule workSchedule5 = new BookingSettings.WorkSchedule();
        workSchedule5.setDay(5);
        workSchedule5.setStartTime(28800087);
        workSchedule5.setEndTime(86460087);
        workScheduleList5.add(workSchedule5);
        addSettings.setWorkScheduleList5(workSchedule5);

        List<BookingSettings.WorkSchedule> workScheduleList6 = new ArrayList<BookingSettings.WorkSchedule>();
        BookingSettings.WorkSchedule workSchedule6 = new BookingSettings.WorkSchedule();
        workSchedule6.setDay(6);
        workSchedule6.setStartTime(28800084);
        workSchedule6.setEndTime(86460084);
        workScheduleList6.add(workSchedule6);
        addSettings.setWorkScheduleList6(workSchedule6);

        List<BookingSettings.WorkSchedule> workScheduleList7 = new ArrayList<BookingSettings.WorkSchedule>();
        BookingSettings.WorkSchedule workSchedule7 = new BookingSettings.WorkSchedule();
        workSchedule7.setDay(7);
        workSchedule7.setStartTime(28800064);
        workSchedule7.setEndTime(86460064);
        workScheduleList7.add(workSchedule7);
        addSettings.setWorkScheduleList7(workSchedule7);

        return addSettings;
    }
}
