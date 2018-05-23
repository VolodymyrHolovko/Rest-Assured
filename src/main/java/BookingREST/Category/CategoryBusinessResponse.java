package BookingREST.Category;

import java.util.List;

public class CategoryBusinessResponse {
    List<Category> data;

    public List<Category> getData() {
        return data;
    }

    public void setData(List<Category> data) {
        this.data = data;
    }
}
