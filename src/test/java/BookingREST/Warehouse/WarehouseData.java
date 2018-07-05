package BookingREST.Warehouse;

public class WarehouseData {
    public Warehouse addWarehouses(int business_id, int address_id, int responsible_id, String title) {
        Warehouse addWarehouse = new Warehouse();
        addWarehouse.setBusiness_id(business_id);
        addWarehouse.setAddress_id(address_id);
        addWarehouse.setResponsible_id(responsible_id);
        addWarehouse.setTitle(title);
        return addWarehouse;
    }
    public Warehouse updateWarehouses(int responsible_id2, String title2) {
        Warehouse updateWarehouse = new Warehouse();
        updateWarehouse.setResponsible_id(responsible_id2);
        updateWarehouse.setTitle(title2);
        return updateWarehouse;
    }
}
