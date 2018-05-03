package Booking.BookingCustomer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class BookingCustomerData {
    static final String D_F_T = "yyyy-MM-dd'T'HH:mm:ss.000";

    public BookingCustomer addBookingCustomer(int TableIds, int DepId) {
        BookingCustomer bookingCustomer = new BookingCustomer();
        DateFormat dateFormat = new SimpleDateFormat(D_F_T, Locale.getDefault());
        Date date = new Date();
        long addTwoHours = date.getTime() + TimeUnit.MINUTES.toMillis(125);
        long addThreeHours = date.getTime() + TimeUnit.MINUTES.toMillis(251);
        String dateTime = dateFormat.format(new Date(addTwoHours));
        String endTime = dateFormat.format(new Date(addThreeHours));
        bookingCustomer.setDepartmentId(DepId);
        List<Integer> tableIds = new ArrayList<>();
        tableIds.add(TableIds);
        bookingCustomer.setTableIds(tableIds);
        bookingCustomer.setPeopleCount(1);
        bookingCustomer.setRequestDescription("це мій букінг кастомера");
        bookingCustomer.setBookingDateTime(dateTime);
        bookingCustomer.setBookingEndTime(endTime);
        bookingCustomer.setAddressId(2);

        return bookingCustomer;
    }
}
