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
        bookingAdmin.setDepartmentId(DepId);
        List<Integer> tableIds = new ArrayList<>();
        tableIds.add(TableIds);
        bookingAdmin.setTableIds(tableIds);
        bookingAdmin.setPeopleCount(2);
        bookingAdmin.setRequestDescription("хочу живої музики без мертвих музикантів");
        bookingAdmin.setBookingDateTime("2018-10-15T18:58:57.000");
        bookingAdmin.setBookingEndTime("2018-10-15T18:58:57.000");
        bookingAdmin.setAddressId(2);

        return bookingAdmin;
    }
    public Booking updateBookingAdmin (int TableIds, int DepId, int id ) {
        Booking updateBookingAdm = new Booking();
        updateBookingAdm.setDepartmentId(DepId);
        List<Integer> tableIds = new ArrayList<>();
        tableIds.add(TableIds);
        updateBookingAdm.setTableIds(tableIds);
        updateBookingAdm.setId(id);
        updateBookingAdm.setPeopleCount(2);
        updateBookingAdm.setRequestDescription("а сєводня в завтрашній дєнь");
        updateBookingAdm.setBookingDateTime("2018-10-16T18:58:57.000");
        updateBookingAdm.setBookingEndTime("2018-10-16T18:58:57.000");
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
