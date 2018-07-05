package Nomenclature.Option.Option;

import java.util.ArrayList;
import java.util.List;

public class OptionData {
    public Option createOption(int parentId, int nomenclatureId){
        Option option = new Option();
        option.setParentId(parentId);
        option.setAddressId(1);
        option.setMinQuantity(1);
        option.setMaxQuantity(5);
        option.setName("option Name");
        option.setFree(true);
        option.setActive(true);
        List <Option.NomenclatureOptions> nomenclatureOptions = new ArrayList<>();
        Option.NomenclatureOptions nomenclatureOption = new Option.NomenclatureOptions();
        nomenclatureOption.setNomenclatureId(nomenclatureId);
        nomenclatureOption.setMinQuantity(1);
        nomenclatureOption.setMaxQuantity(5);
        nomenclatureOption.setDefaultQuantity(1);
        nomenclatureOption.setPrintIfDefaultQuantity(true);
        nomenclatureOption.setConstant(true);
        nomenclatureOptions.add(nomenclatureOption);
        option.setNomenclatureOptions(nomenclatureOptions);
        return option;
    }
    public Option updateOption(int parentId, int nomenclatureId){
        Option option = new Option();
        option.setParentId(parentId);
        option.setAddressId(1);
        option.setMinQuantity(2);
        option.setMaxQuantity(6);
        option.setName("option Name2");
        option.isFree(false);
        List <Option.NomenclatureOptions> nomenclatureOptions = new ArrayList<>();
        Option.NomenclatureOptions nomenclatureOption = new Option.NomenclatureOptions();
        nomenclatureOption.setNomenclatureId(nomenclatureId);
        nomenclatureOption.setMinQuantity(2);
        nomenclatureOption.setMaxQuantity(6);
        nomenclatureOption.setDefaultQuantity(2);
        nomenclatureOption.setPrintIfDefaultQuantity(false);
        nomenclatureOption.setConstant(false);
        nomenclatureOptions.add(nomenclatureOption);
        option.setNomenclatureOptions(nomenclatureOptions);
        return option;
    }
}
