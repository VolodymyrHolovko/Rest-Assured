package BookingREST.Bookings;

public class BookingsData {
    public Booking createBooking(int busines, int address, int staff, int service, String customerId){
        Booking booking = new Booking();
        booking.setBusiness_id(busines);
        booking.setAddress_id(address);
        booking.setStaff_id(staff);
        booking.setService_id(service);
        booking.setCustomer_id(customerId);
        booking.setDate("");
        booking.setComment("");
        return  booking;
    }
}
