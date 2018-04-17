package Nomenclature.Sizes;

public class Size {
    private int id;
 private int  nomenclatureId;
 private int size;
 private int price;
 private int sizeTypeId;
 private int writeOffIndex;
 private String presentationName;

    public int getNomenclatureId() {
        return nomenclatureId;
    }

    public int getSize() {
        return size;
    }

    public int getPrice() {
        return price;
    }

    public int getSizeTypeId() {
        return sizeTypeId;
    }

    public int getWriteOffIndex() {
        return writeOffIndex;
    }

    public String getPresentationName() {
        return presentationName;
    }


    public void setNomenclatureId(int nomenclatureId) {
        this.nomenclatureId = nomenclatureId;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setSizeTypeId(int sizeTypeId) {
        this.sizeTypeId = sizeTypeId;
    }

    public void setWriteOffIndex(int writeOffIndex) {
        this.writeOffIndex = writeOffIndex;
    }

    public void setPresentationName(String presentationName) {
        this.presentationName = presentationName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
