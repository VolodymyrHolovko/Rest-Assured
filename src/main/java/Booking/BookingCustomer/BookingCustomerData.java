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

    public BookingCustomer addBookingCustomer(int TableId, int DepId) {
        BookingCustomer bookingCustomer = new BookingCustomer();
        DateFormat dateFormat = new SimpleDateFormat(D_F_T, Locale.getDefault());
        Date date = new Date();
        long addTwoHours = date.getTime() + TimeUnit.MINUTES.toMillis(125);
        long addThreeHours = date.getTime() + TimeUnit.MINUTES.toMillis(251);
        String dateTime = dateFormat.format(new Date(addTwoHours));
        String endTime = dateFormat.format(new Date(addThreeHours));
        bookingCustomer.setDepartmentId(DepId);
        List<Integer> tableIds = new ArrayList<>();
        tableIds.add(TableId);
        bookingCustomer.setTableIds(tableIds);
        bookingCustomer.setPeopleCount(1);
        bookingCustomer.setRequestDescription("це мій букінг кастомера");
        bookingCustomer.setBookingDateTime(dateTime);
        bookingCustomer.setBookingEndTime(endTime);
        bookingCustomer.setAddressId(2);

        return bookingCustomer;
    }
    public BookingCustomer updateBookingCustomer(int TableId, int DepId, int id) {
        BookingCustomer updateBooking = new BookingCustomer();
        DateFormat dateFormat = new SimpleDateFormat(D_F_T, Locale.getDefault());
        Date date = new Date();
        long addTwoHours = date.getTime() + TimeUnit.MINUTES.toMillis(130);
        long addThreeHours = date.getTime() + TimeUnit.MINUTES.toMillis(260);
        String dateTime = dateFormat.format(new Date(addTwoHours));
        String endTime = dateFormat.format(new Date(addThreeHours));
        updateBooking.setDepartmentId(DepId);
        List<Integer> tableIds = new ArrayList<>();
        tableIds.add(TableId);
        updateBooking.setTableIds(tableIds);
        updateBooking.setId(id);
        updateBooking.setPeopleCount(2);
        updateBooking.setRequestDescription("це мій букінг кастомера, який я редагую");
        updateBooking.setBookingDateTime(dateTime);
        updateBooking.setBookingEndTime(endTime);
        updateBooking.setAddressId(2);
        updateBooking.setPreviousBookingAvailable(false);

        return updateBooking;
    }
    public BookingCustomer bookingCustomerReject(int id) {
        BookingCustomer bookingCustomerReject = new BookingCustomer();
        bookingCustomerReject.setId(id);
        bookingCustomerReject.setAddressId(2);
        bookingCustomerReject.setResponseDescription("я передумав");

        return bookingCustomerReject;
    }
}
