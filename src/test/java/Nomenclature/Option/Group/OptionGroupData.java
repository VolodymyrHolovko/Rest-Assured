package Nomenclature.Option.Group;

public class OptionGroupData {
    public OptionGroup createOptionGroup(){
        OptionGroup optionGroup = new OptionGroup();
        optionGroup.setName("Test Group");
        optionGroup.setColorHex("#223344");
        optionGroup.setParentId(1);
        return  optionGroup;
    }

    public OptionGroup updateOptionGroup(){
        OptionGroup optionGroup = new OptionGroup();
        optionGroup.setName("Test Group1");
        optionGroup.setColorHex("#223345");
        optionGroup.setParentId(1);
        return  optionGroup;
    }
}
