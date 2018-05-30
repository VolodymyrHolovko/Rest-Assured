package Nomenclature.Option.Option;

import java.util.ArrayList;
import java.util.List;

public class OptionPatchOptionData {
public OptionPatchOption setOption(int nomenclatureId){
    OptionPatchOption optionPatchOption = new OptionPatchOption();
    List<Integer> optionsIds = new ArrayList<>();
    optionsIds.add(nomenclatureId);
    optionPatchOption.setOptionsIds(optionsIds);
    return optionPatchOption;
}
}
