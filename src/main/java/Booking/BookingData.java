package Booking;

import java.util.ArrayList;
import java.util.List;

public class BookingData {
    public Booking addBookingAdmin(int TableIds, int DepId) {
        Booking bookingAdmin = new Booking();
        bookingAdmin.setDepartmentId(DepId);
        List<Integer> tableIds = new ArrayList<>();
        tableIds.add(TableIds);
        bookingAdmin.setTableIds(tableIds);
        bookingAdmin.setPeopleCount(3);
        bookingAdmin.setRequestDescription("хочу живої музики без мертвих музикантів");
<<<<<<< HEAD
        bookingAdmin.setBookingDateTime("2018-06-24T10:55:33.000");
        bookingAdmin.setBookingEndTime("2018-06-24T12:40:33.000");
=======
        bookingAdmin.setBookingDateTime("2018-04-26T10:55:33.000");
        bookingAdmin.setBookingEndTime("2018-04-26T12:40:33.000");
>>>>>>> v_holovko
        bookingAdmin.setAddressId(2);
        return bookingAdmin;
    }
    public Booking updateBookingAdmin (int TableIds, int DepIds, int id ) {
        Booking updateBookingAdm = new Booking();
        updateBookingAdm.setId(id);
        updateBookingAdm.setDepartmentId(DepIds);
        ArrayList<Integer> tableIds = new ArrayList<>();
        tableIds.add(TableIds);
        updateBookingAdm.setTableIds(tableIds);
        updateBookingAdm.setPeopleCount(2);
        updateBookingAdm.setRequestDescription("а сєводня в завтрашній дєнь");
<<<<<<< HEAD
        updateBookingAdm.setBookingDateTime("2018-06-24T11:55:33.000");
        updateBookingAdm.setBookingEndTime("2018-06-24T14:40:33.000");
=======
        updateBookingAdm.setBookingDateTime("2018-04-26T11:55:33.000");
        updateBookingAdm.setBookingEndTime("2018-04-26T14:40:33.000");
>>>>>>> v_holovko
        updateBookingAdm.setAddressId(2);
        updateBookingAdm.setPreviousBookingAvailable(false);
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
