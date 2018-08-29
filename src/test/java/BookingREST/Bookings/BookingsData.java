package BookingREST.Bookings;

public class BookingsData {
    public Booking createBooking(int busines, int address, int staff, int service, String customerId){
        Booking booking = new Booking();
        booking.setBusiness_id(busines);
        booking.setAddress_id(address);
        booking.setStaff_id(staff);
        booking.setService_id(service);
        booking.setCustomer_id(customerId);
        booking.setDate("2018-09-25 17:00:00");
        booking.setComment("");
        return  booking;
    }

    public Booking updateBooking(int staff, int service){
        Booking booking = new Booking();
        booking.setStaff_id(staff);
        booking.setService_id(service);
        booking.setDate("2018-09-25 14:00:00");
        booking.setComment("");
        return  booking;
    }
}
