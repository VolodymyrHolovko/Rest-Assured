package Booking;

import Departments.Tables.TablesTest;

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
        bookingAdmin.setBookingDateTime("2018-04-14T18:55:33.000");
        bookingAdmin.setBookingEndTime("2018-04-14T19:40:33.000");
        bookingAdmin.setAddressId(1);
        return bookingAdmin;
    }
    public Booking updateBookingAdmin (int id) {
        Booking updateBookingAdm = new Booking();
        updateBookingAdm.setId(id);
        updateBookingAdm.setDepartmentId(101);
        ArrayList<Integer> tableIds = new ArrayList<>();
        tableIds.add(114);
        updateBookingAdm.setTableIds(tableIds);
        updateBookingAdm.setPeopleCount(2);
        updateBookingAdm.setRequestDescription("а сєводня в завтрашній дєнь");
        updateBookingAdm.setBookingDateTime("2018-03-30T13:55:33.000");
        updateBookingAdm.setBookingEndTime("2018-03-30T14:55:33.000");
        updateBookingAdm.setAddressId(1);
        updateBookingAdm.setPreviousBookingAvailable(false);
        return updateBookingAdm;
    }
}
