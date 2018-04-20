package Nomenclature.Sizes;

public class SizeData {
    public Size createSize(int id){
        Size size = new Size();
        size.setNomenclatureId(id);
        size.setSize(10);
        size.setPrice(21);
        size.setSizeTypeId(1);
        size.setWriteOffIndex(2);
        size.setPresentationName("XL");
        return size;
    }
    public Size updateSize(){
        Size size = new Size();
        size.setSize(12);
        size.setPrice(22);
        size.setWriteOffIndex(3);
        size.setPresentationName("L");
        return size;
    }
}
