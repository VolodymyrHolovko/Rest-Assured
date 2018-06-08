package BookingREST.Suppliers;

public class SuppliersData {
    public Suppliers addSuppliers(int business_id, int supplier_type_id, String title, String phone, String fax, String email, String address, String comment) {
        Suppliers addSupplier = new Suppliers();
        addSupplier.setBusiness_id(business_id);
        addSupplier.setSupplier_type_id(supplier_type_id);
        addSupplier.setTitle(title);
        addSupplier.setPhone(phone);
        addSupplier.setFax(fax);
        addSupplier.setEmail(email);
        addSupplier.setAddress(address);
        addSupplier.setComment(comment);
        return addSupplier;
    }
}
