package BookingREST.Favorites;

import java.util.List;

public class FavoritesResponse {
    List<Favorites> data;

    public List<Favorites> getData() {
        return data;
    }

    public void setData(List<Favorites> data) {
        this.data = data;
    }
}
