package Nomenclature.Option.Option;

import javax.validation.constraints.Size;
import java.lang.reflect.Array;
import java.util.List;

public class OptionPatchOption {
    List<Integer> optionsIds;

    public List<Integer> getOptionsIds() {
        return optionsIds;
    }

    public void setOptionsIds(List<Integer> optionsIds) {
        this.optionsIds = optionsIds;
    }
}
