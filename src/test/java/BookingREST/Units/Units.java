package BookingREST.Units;

public class Units {
    private String name;
    private String abbr;
    private int id;
    private String updated_at;
    private String deleted_at;

    public String getDeleted_at() {
        return deleted_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbr() {
        return abbr;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
