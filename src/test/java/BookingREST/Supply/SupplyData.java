package BookingREST.Supply;

public class SupplyData {
    public Supply addNewSupply(int business_id, int supplier_id, int warehouse_id, int product_id, int count, String currency, int cost, String comment) {
        Supply addSupply = new Supply();
        addSupply.setBusiness_id(business_id);
        addSupply.setSupplier_id(supplier_id);
        addSupply.setWarehouse_id(warehouse_id);
        addSupply.setProduct_id(product_id);
        addSupply.setCount(count);
        addSupply.setCurrency(currency);
        addSupply.setCost(cost);
        addSupply.setComment(comment);
        return addSupply;
    }
}
