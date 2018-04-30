package Booking;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class BookingData {
static final String D_F_T = "yyyy-MM-dd'T'HH:mm:ss.000";

    public Booking addBookingAdmin(int TableIds, int DepId) {
        Booking bookingAdmin = new Booking();
        DateFormat dateFormat = new SimpleDateFormat(D_F_T, Locale.getDefault());
        Date date = new Date();
        long addHours = date.getTime() + TimeUnit.MINUTES.toMillis(61);
        long addTwoHours = date.getTime() + TimeUnit.MINUTES.toMillis(122);
        String dateTime = dateFormat.format(new Date(addHours));
        String endTime = dateFormat.format(new Date(addTwoHours));
        bookingAdmin.setDepartmentId(DepId);
        List<Integer> tableIds = new ArrayList<>();
        tableIds.add(TableIds);
        bookingAdmin.setTableIds(tableIds);
        bookingAdmin.setPeopleCount(2);
        bookingAdmin.setRequestDescription("хочу живої музики без мертвих музикантів");
        bookingAdmin.setBookingDateTime(dateTime);
        bookingAdmin.setBookingEndTime(endTime);
        bookingAdmin.setAddressId(2);

        return bookingAdmin;
    }
    public Booking updateBookingAdmin (int TableIds, int DepId, int id ) {
        Booking updateBookingAdm = new Booking();
        DateFormat dateFormat = new SimpleDateFormat(D_F_T, Locale.getDefault());
        Date date = new Date();
        long addHours = date.getTime() + TimeUnit.MINUTES.toMillis(62);
        long addTwoHours = date.getTime() + TimeUnit.MINUTES.toMillis(123);
        String dateTime = dateFormat.format(new Date(addHours));
        String endTime = dateFormat.format(new Date(addTwoHours));
        updateBookingAdm.setDepartmentId(DepId);
        List<Integer> tableIds = new ArrayList<>();
        tableIds.add(TableIds);
        updateBookingAdm.setTableIds(tableIds);
        updateBookingAdm.setId(id);
        updateBookingAdm.setPeopleCount(2);
        updateBookingAdm.setRequestDescription("а сєводня в завтрашній дєнь");
        updateBookingAdm.setBookingDateTime(dateTime);
        updateBookingAdm.setBookingEndTime(endTime);
        updateBookingAdm.setAddressId(2);

        return updateBookingAdm;
    }
    public Booking bookingAdminReject(int id, int DepIds) {
        Booking bookingReject = new Booking();
        bookingReject.setId(id);
        bookingReject.setDepartmentId(DepIds);
        bookingReject.setResponseDescription("Ваше замовлення було відхилено адміністратором");

        return bookingReject;
    }

}
