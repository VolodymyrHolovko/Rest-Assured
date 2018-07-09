package BookingREST.Products.ExpenseInteraction;

public class ExpenseInteractionData {
    public ExpenseInteraction addExpenseInteraction(int business_id, int product_id) {
        ExpenseInteraction addExpense = new ExpenseInteraction();
        addExpense.setBusiness_id(business_id);
        addExpense.setProduct_id(product_id);
        return addExpense;
    }
}
