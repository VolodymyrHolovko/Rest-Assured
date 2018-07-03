package BookingREST.Expense;

import java.util.List;

public class ExpenseResponseArray {
    List<Expense> data;

    public List<Expense> getData() {
        return data;
    }

    public void setData(List<Expense> data) {
        this.data = data;
    }
}
