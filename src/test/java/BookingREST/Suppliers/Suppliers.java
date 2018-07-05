package BookingREST.Suppliers;

import java.util.List;

public class Suppliers  {
    private int business_id;
    private int supplier_type_id;
    private String title;
    private String phone;
    private String fax;
    private String email;
    private int inn;
    private String address;
    private String comment;
    private int id;
    private String created_at;
    private String updated_at;
    private String deleted_at;

    public String getDeleted_at() {
        return deleted_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public int getInn() {
        return inn;
    }

    public void setInn(int inn) {
        this.inn = inn;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBusiness_id() {
        return business_id;
    }

    public void setBusiness_id(int business_id) {
        this.business_id = business_id;
    }

    public int getSupplier_type_id() {
        return supplier_type_id;
    }

    public void setSupplier_type_id(int supplier_type_id) {
        this.supplier_type_id = supplier_type_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
