package BookingREST.Sale;

public class SaleData {
    public Sale addNewSales(int business_id, String customer_id, int seller_id, int warehouse_id, int stock_id, int product_id, int count, String currency, int cost, String comment) {
        Sale addSale = new Sale();
        addSale.setBusiness_id(business_id);
        addSale.setCustomer_id(customer_id);
        addSale.setSeller_id(seller_id);
        addSale.setWarehouse_id(warehouse_id);
        addSale.setStock_id(stock_id);
        addSale.setProduct_id(product_id);
        addSale.setCount(count);
        addSale.setCurrency(currency);
        addSale.setCost(cost);
        addSale.setComment(comment);
        return addSale;
    }
    public Sale updateSales(String comment2) {
        Sale updateSale = new Sale();
        updateSale.setComment(comment2);
        return updateSale;
    }
}
