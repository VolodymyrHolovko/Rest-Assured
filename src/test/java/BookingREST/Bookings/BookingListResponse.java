package BookingREST.Bookings;

import java.util.List;

public class BookingListResponse {
    List<Booking> bookingList;

    public List<Booking> getBookingList() {
        return bookingList;
    }

    public void setBookingList(List<Booking> bookingList) {
        this.bookingList = bookingList;
    }
}
