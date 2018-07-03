package BookingREST.Stock;

import java.util.List;

public class StockResponseArray {
    public List<Stock> data;

    public List<Stock> getData() {
        return data;
    }

    public void setData(List<Stock> data) {
        this.data = data;
    }
}
