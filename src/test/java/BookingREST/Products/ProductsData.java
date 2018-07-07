package BookingREST.Products;

public class ProductsData {
    public Products addNewProducts(int business_id, String name, String sku, int category_id, int sale_unit_id, String sale_currency, int sale_cost, int expense_unit_id, String expense_currency, int expense_cost) {
        Products addProducts = new Products();
        addProducts.setBusiness_id(business_id);
        addProducts.setName(name);
        addProducts.setSku(sku);
        addProducts.setCategory_id(category_id);
        addProducts.setSale_used(true);
        addProducts.setUnit_id(sale_unit_id);
        addProducts.setSale_currency(sale_currency);
        addProducts.setSale_cost(sale_cost);
        addProducts.setExpense_used(true);
        addProducts.setExpense_unit_id(expense_unit_id);
        addProducts.setExpense_currency(expense_currency);
        addProducts.setExpense_cost(expense_cost);
        return addProducts;
    }
    public Products updateProducts(String name2, String sku2, int category_id2, int sale_unit_id2, String sale_currency2, int sale_cost2, int expense_unit_id2, String expense_currency2, int expense_cost2) {
        Products updateProduct = new Products();
        updateProduct.setName(name2);
        updateProduct.setSku(sku2);
        updateProduct.setCategory_id(category_id2);
        updateProduct.setSale_used(false);
        updateProduct.setUnit_id(sale_unit_id2);
        updateProduct.setSale_currency(sale_currency2);
        updateProduct.setSale_cost(sale_cost2);
        updateProduct.setExpense_used(false);
        updateProduct.setExpense_unit_id(expense_unit_id2);
        updateProduct.setExpense_currency(expense_currency2);
        updateProduct.setExpense_cost(expense_cost2);
        return updateProduct;
    }


}
