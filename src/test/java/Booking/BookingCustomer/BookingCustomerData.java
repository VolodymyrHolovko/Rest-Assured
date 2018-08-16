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
        bookingCustomer.setBookingDateTime("2018-09-25T18:58:57.000");
        bookingCustomer.setBookingEndTime("2018-09-25T19:59:57.000");
        bookingCustomer.setDepartmentId(DepId);
        List<Integer> tableIds = new ArrayList<>();
        tableIds.add(TableId);
        bookingCustomer.setTableIds(tableIds);
        bookingCustomer.setPeopleCount(1);
        bookingCustomer.setRequestDescription("це мій букінг кастомера");
        bookingCustomer.setAddressId(2);

        return bookingCustomer;
    }
    public BookingCustomer updateBookingCustomer(int TableId, int DepId, int id) {
        BookingCustomer updateBooking = new BookingCustomer();
        updateBooking.setDepartmentId(DepId);
        List<Integer> tableIds = new ArrayList<>();
        tableIds.add(TableId);
        updateBooking.setTableIds(tableIds);
        updateBooking.setId(id);
        updateBooking.setPeopleCount(2);
        updateBooking.setRequestDescription("це мій букінг кастомера, який я редагую");
        updateBooking.setBookingDateTime("2018-09-25T18:58:57.000");
        updateBooking.setBookingEndTime("2018-09-25T19:59:57.000");
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
