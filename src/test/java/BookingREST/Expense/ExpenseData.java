package BookingREST.Expense;

public class ExpenseData {
    public Expense addNewExpense(int business_id, int warehouse_id, int stock_id, int product_id, int count, String comment) {
        Expense addExpense = new Expense();
        addExpense.setBusiness_id(business_id);
        addExpense.setWarehouse_id(warehouse_id);
        addExpense.setStock_id(stock_id);
        addExpense.setProduct_id(product_id);
        addExpense.setCount(count);
        addExpense.setComment(comment);
        return addExpense;
    }
    public Expense updateExpense(String comment2) {
        Expense updateExpense = new Expense();
        updateExpense.setComment(comment2);
        return  updateExpense;
    }
}
