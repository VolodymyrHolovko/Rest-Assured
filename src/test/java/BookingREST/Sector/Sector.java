package BookingREST.Sector;

public class Sector {
    String sector;
    String name_en;
    String name_ru;
    String name_uk;
    int status;
    int id;
    String deleted_at;

    public String getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(String deleted_at) {
        this.deleted_at = deleted_at;
    }

    public String getSector() {
        return sector;
    }

    public String getName_en() {
        return name_en;
    }

    public String getName_ru() {
        return name_ru;
    }

    public String getName_uk() {
        return name_uk;
    }


    public void setSector(String sector) {
        this.sector = sector;
    }

    public void setName_en(String name_en) {
        this.name_en = name_en;
    }

    public void setName_ru(String name_ru) {
        this.name_ru = name_ru;
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
