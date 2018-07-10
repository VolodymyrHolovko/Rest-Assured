package BookingREST.Products.SaleInteraction;

public class SaleInteraction {
    private int business_id;
    private int product_id;
    private String currency;
    private int cost;
    private int id;
    private String updated_at;
    private String deleted_at;

    public String getDeleted_at() {
        return deleted_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public int getBusiness_id() {
        return business_id;
    }

    public void setBusiness_id(int business_id) {
        this.business_id = business_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
