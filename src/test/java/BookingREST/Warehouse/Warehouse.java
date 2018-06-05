package BookingREST.Warehouse;

public class Warehouse {
    private int business_id;
    private int address_id;
    private int responsible_id;
    private String title;

    public int getBusiness_id() {
        return business_id;
    }

    public void setBusiness_id(int business_id) {
        this.business_id = business_id;
    }

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }

    public int getResponsible_id() {
        return responsible_id;
    }

    public void setResponsible_id(int responsible_id) {
        this.responsible_id = responsible_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
