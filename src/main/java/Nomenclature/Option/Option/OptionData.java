package Nomenclature.Option.Option;

import java.util.ArrayList;
import java.util.List;

public class OptionData {
    public Option createOption(int parentId, String nomenclatureId){
        Option option = new Option();
        option.setAddressId(1);
        option.setMinQuantity(1);
        option.setMaxQuantity(5);
        option.setName("option Name");
        option.isFree(true);
        List <Option.NomenclatureOptions> nomenclatureOptions = new ArrayList<>();
        Option.NomenclatureOptions nomenclatureOption = new Option.NomenclatureOptions();
        nomenclatureOption.setNomenclatureId(nomenclatureId);
        nomenclatureOption.setMinQuantity(1);
        nomenclatureOption.setMaxQuantity(5);
        nomenclatureOption.setDefaultQuantity(1);
        nomenclatureOption.isPrintIfDefaultQuantity(true);
        nomenclatureOption.isConstant(true);
        nomenclatureOptions.add(nomenclatureOption);
        option.setNomenclatureOptions(nomenclatureOptions);
        return option;
    }
    public Option updateOption(int parentId, String nomenclatureId){
        Option option = new Option();
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
        nomenclatureOption.isPrintIfDefaultQuantity(false);
        nomenclatureOption.isConstant(false);
        return option;
    }
}
