package BookingREST.Products;

import java.util.List;

public class ProductsResponseArray {
    List<Products> data;

    public List<Products> getData() {
        return data;
    }

    public void setData(List<Products> data) {
        this.data = data;
    }
}
