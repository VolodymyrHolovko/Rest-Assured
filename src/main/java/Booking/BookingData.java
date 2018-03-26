package Booking;

import java.util.ArrayList;

public class BookingData {
    public Booking addBookingAdmin() {
        Booking bookingAdmin = new Booking();
        bookingAdmin.setDepartmentId(42);
        ArrayList<Integer> tableIds = new ArrayList<>();
        tableIds.add(30);
        bookingAdmin.setTableIds(tableIds);
        bookingAdmin.setPeopleCount(8);
        bookingAdmin.setRequestDescription("хочу живої музики без мертвих музикантів");
        bookingAdmin.setBookingDateTime("2018-03-30T12:55:33.000");
        bookingAdmin.setBookingEndTime("2018-03-30T13:40:33.000");
        bookingAdmin.setAddressId(100);
        return bookingAdmin;
    }
    public Booking updateBookingAdmin (int id) {
        Booking updateBookingAdm = new Booking();
        updateBookingAdm.setDepartmentId(42);
        ArrayList<Integer> tableIds = new ArrayList<>();
        tableIds.add(29);
        updateBookingAdm.setTableIds(tableIds);
        updateBookingAdm.setPeopleCount(9);
        updateBookingAdm.setRequestDescription("а сєводня в завтрашній дєнь");
        updateBookingAdm.setBookingDateTime("2018-03-30T13:55:33.000");
        updateBookingAdm.setBookingEndTime("2018-03-30T14:55:33.000");
        updateBookingAdm.setAddressId(100);
        return updateBookingAdm;
    }
}
