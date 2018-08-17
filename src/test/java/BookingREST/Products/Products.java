package BookingREST.Products;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Products {
    private int business_id;
    private String name;
    private String sku;
    private int category_id;
    private boolean expense_used;
    private boolean sale_used;

    @JsonProperty
    private int unit_id;
    private int id;
    private String updated_at;
    private String deleted_at;

    public boolean isExpense_used() {
        return expense_used;
    }

    public void setExpense_used(boolean expense_used) {
        this.expense_used = expense_used;
    }

    public boolean isSale_used() {
        return sale_used;
    }

    public void setSale_used(boolean sale_used) {
        this.sale_used = sale_used;
    }

    public int getBusiness_id() {
        return business_id;
    }

    public void setBusiness_id(int business_id) {
        this.business_id = business_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getUnit_id() {
        return unit_id;
    }

    public void setUnit_id(int unit_id) {
        this.unit_id = unit_id;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(String deleted_at) {
        this.deleted_at = deleted_at;
    }
}
