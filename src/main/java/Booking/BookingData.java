package Booking;

import java.util.ArrayList;
import java.util.List;

public class BookingData {
    public Booking addBookingAdmin(int TableIds, int DEpIds) {
        Booking bookingAdmin = new Booking();
        bookingAdmin.setDepartmentId(DEpIds);
        List<Integer> tableIds = new ArrayList<>();
        tableIds.add(TableIds);
        bookingAdmin.setTableIds(tableIds);
        bookingAdmin.setPeopleCount(3);
        bookingAdmin.setRequestDescription("хочу живої музики без мертвих музикантів");
        bookingAdmin.setBookingDateTime("2018-04-20T10:55:33.000");
        bookingAdmin.setBookingEndTime("2018-04-20T12:40:33.000");
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
        updateBookingAdm.setBookingDateTime("2018-04-20T11:55:33.000");
        updateBookingAdm.setBookingEndTime("2018-04-20T14:40:33.000");
        updateBookingAdm.setAddressId(2);
        updateBookingAdm.setPreviousBookingAvailable(false);
        return updateBookingAdm;
    }
    public Booking addBookingSettings() {
        Booking addsettings = new Booking();
        return addsettings;
    }
}
