package BookingREST.Category;

public class Category {
    private String category;
    private int sector_id;
    private int strategy_id;
    private String name_en;
    private String name_ru;
    private String name_uk;
    private int status;
    private int id;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getSector_id() {
        return sector_id;
    }

    public void setSector_id(int sector_id) {
        this.sector_id = sector_id;
    }

    public int getStrategy_id() {
        return strategy_id;
    }

    public void setStrategy_id(int strategy_id) {
        this.strategy_id = strategy_id;
    }

    public String getName_en() {
        return name_en;
    }

    public void setName_en(String name_en) {
        this.name_en = name_en;
    }

    public String getName_ru() {
        return name_ru;
    }

    public void setName_ru(String name_ru) {
        this.name_ru = name_ru;
    }

    public String getName_uk() {
        return name_uk;
    }

    public void setName_uk(String name_uk) {
        this.name_uk = name_uk;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

