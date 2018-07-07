package BookingREST.Products;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Products {
    private int business_id;
    private String name;
    private String sku;
    private int category_id;
    @JsonProperty
    private boolean sale_used;
    private int unit_id;
    private String sale_currency;
    private int sale_cost;
    private boolean expense_used;
    private int expense_unit_id;
    private String expense_currency;
    private int expense_cost;
    private int id;
    private String updated_at;
    private String deleted_at;

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

    public boolean isSale_used() {
        return sale_used;
    }

    public void setSale_used(boolean sale_used) {
        this.sale_used = sale_used;
    }

    public int getUnit_id() {
        return unit_id;
    }

    public void setUnit_id(int unit_id) {
        this.unit_id = unit_id;
    }

    public String getSale_currency() {
        return sale_currency;
    }

    public void setSale_currency(String sale_currency) {
        this.sale_currency = sale_currency;
    }

    public int getSale_cost() {
        return sale_cost;
    }

    public void setSale_cost(int sale_cost) {
        this.sale_cost = sale_cost;
    }

    public boolean isExpense_used() {
        return expense_used;
    }

    public void setExpense_used(boolean expense_used) {
        this.expense_used = expense_used;
    }

    public int getExpense_unit_id() {
        return expense_unit_id;
    }

    public void setExpense_unit_id(int expense_unit_id) {
        this.expense_unit_id = expense_unit_id;
    }

    public String getExpense_currency() {
        return expense_currency;
    }

    public void setExpense_currency(String expense_currency) {
        this.expense_currency = expense_currency;
    }

    public int getExpense_cost() {
        return expense_cost;
    }

    public void setExpense_cost(int expense_cost) {
        this.expense_cost = expense_cost;
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
