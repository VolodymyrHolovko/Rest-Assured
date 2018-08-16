package BookingREST.Products;

public class ProductsData {
    public Products addNewProducts(int business_id, String name, String sku, int category_id, int unit_id) {
        Products addProducts = new Products();
        addProducts.setBusiness_id(business_id);
        addProducts.setName(name);
        addProducts.setSku(sku);
        addProducts.setCategory_id(category_id);
        addProducts.setUnit_id(unit_id);
        addProducts.setExpense_used(true);
        addProducts.setSale_used(true);
        return addProducts;
    }
    public Products updateProducts(String name2, String sku2, int category_id2, int unit_id2) {
        Products updateProduct = new Products();
        updateProduct.setName(name2);
        updateProduct.setSku(sku2);
        updateProduct.setCategory_id(category_id2);
        updateProduct.setUnit_id(unit_id2);
        return updateProduct;
    }


}
