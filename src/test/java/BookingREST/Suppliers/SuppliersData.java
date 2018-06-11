package BookingREST.Suppliers;

public class SuppliersData {
    public Suppliers addSuppliers(int business_id, int supplier_type_id, String title, String phone, String fax, String email, int inn, String address, String comment) {
        Suppliers addSupplier = new Suppliers();
        addSupplier.setBusiness_id(business_id);
        addSupplier.setSupplier_type_id(supplier_type_id);
        addSupplier.setTitle(title);
        addSupplier.setPhone(phone);
        addSupplier.setFax(fax);
        addSupplier.setInn(inn);
        addSupplier.setEmail(email);
        addSupplier.setAddress(address);
        addSupplier.setComment(comment);
        return addSupplier;
    }
    public Suppliers updateSuppliers(int supplier_type_id2, String title2, String phone2, String fax2, String email2, int inn2, String address2, String comment2) {
        Suppliers updateSupplier = new Suppliers();
        updateSupplier.setSupplier_type_id(supplier_type_id2);
        updateSupplier.setTitle(title2);
        updateSupplier.setPhone(phone2);
        updateSupplier.setFax(fax2);
        updateSupplier.setEmail(email2);
        updateSupplier.setInn(inn2);
        updateSupplier.setAddress(address2);
        updateSupplier.setComment(comment2);
        return  updateSupplier;
    }
}
