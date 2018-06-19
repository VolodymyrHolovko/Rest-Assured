package BookingREST.Products;

public class ProductsData {
    public Products addNewProducts(int business_id, String name, String sku, int category_id, int sale_unit_id, String sale_currency, int sale_cost, int expense_unit_id, String expense_currency, int expense_cost) {
        Products addProducts = new Products();
        addProducts.setBusiness_id(business_id);
        addProducts.setName(name);
        addProducts.setSku(sku);
        addProducts.setCategory_id(category_id);
        addProducts.setSale_used(true);
        addProducts.setSale_unit_id(sale_unit_id);
        addProducts.setSale_currency(sale_currency);
        addProducts.setSale_cost(sale_cost);
        addProducts.setExpense_used(true);
        addProducts.setExpense_unit_id(expense_unit_id);
        addProducts.setExpense_currency(expense_currency);
        addProducts.setExpense_cost(expense_cost);
        return addProducts;
    }


}
