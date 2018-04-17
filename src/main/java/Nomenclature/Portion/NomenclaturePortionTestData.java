package Nomenclature;

import java.util.ArrayList;
import java.util.List;

public class NomenclaturePortionTestData {
    public NomenclaturePortion addPortion(){
        NomenclaturePortion portion= new NomenclaturePortion();
        portion.setMaximum(15);
        portion.setMinimum(3);
        portion.setMinimumPrice(10);
        portion.setStep(3);
        portion.setStepPrice(5);
        return  portion;
    }
}
