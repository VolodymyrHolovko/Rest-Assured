package Nomenclature.Sizes;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SizeBoolean {
    Boolean data;
    @JsonProperty
    private  String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getData() {
        return data;
    }

    public void setData(Boolean data) {
        this.data = data;
    }
}
