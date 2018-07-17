package BookingREST.Movement;

public class Movement {
    private int business_id;
    private int sender_warehouse_id;
    private int receiver_warehouse_id;
    private int sender_responsible_id;
    private int receiver_responsible_id;
    private int sender_stock_id;
    private int product_id;
    private int count;
    private String comment;
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

    public int getSender_warehouse_id() {
        return sender_warehouse_id;
    }

    public void setSender_warehouse_id(int sender_warehouse_id) {
        this.sender_warehouse_id = sender_warehouse_id;
    }

    public int getReceiver_warehouse_id() {
        return receiver_warehouse_id;
    }

    public void setReceiver_warehouse_id(int receiver_warehouse_id) {
        this.receiver_warehouse_id = receiver_warehouse_id;
    }

    public int getSender_responsible_id() {
        return sender_responsible_id;
    }

    public void setSender_responsible_id(int sender_responsible_id) {
        this.sender_responsible_id = sender_responsible_id;
    }

    public int getReceiver_responsible_id() {
        return receiver_responsible_id;
    }

    public void setReceiver_responsible_id(int receiver_responsible_id) {
        this.receiver_responsible_id = receiver_responsible_id;
    }

    public int getSender_stock_id() {
        return sender_stock_id;
    }

    public void setSender_stock_id(int sender_stock_id) {
        this.sender_stock_id = sender_stock_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
