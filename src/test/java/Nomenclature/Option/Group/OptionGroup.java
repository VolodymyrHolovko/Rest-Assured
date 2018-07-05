package Nomenclature.Option.Group;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OptionGroup {
    private String name;
    private String colorHex;
    private int parentId;
    private int id;
    @JsonProperty
    private boolean isActive;

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getName() {
        return name;
    }

    public String getColorHex() {
        return colorHex;
    }

    public int getParentId() {
        return parentId;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColorHex(String colorHex) {
        this.colorHex = colorHex;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public void setId(int id) {
        this.id = id;
    }
}
