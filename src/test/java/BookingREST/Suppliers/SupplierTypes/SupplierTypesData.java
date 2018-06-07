package BookingREST.Suppliers.SupplierTypes;

public class SupplierTypesData {
    public SupplierTypes addSupplierType(String title) {
        SupplierTypes addType = new SupplierTypes();
        addType.setTitle(title);
        return addType;
    }
    public SupplierTypes updateSupplierType(String title2) {
        SupplierTypes updateType = new SupplierTypes();
        updateType.setTitle(title2);
        return updateType;
    }
}
