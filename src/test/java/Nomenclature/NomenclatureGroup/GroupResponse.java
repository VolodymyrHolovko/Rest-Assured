package Nomenclature.NomenclatureGroup;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GroupResponse {
    @JsonProperty
    private  boolean isActive;
    private int id;
    private int parentId;
    private int tagId;
    private int defaultDimensionId;
    private int defaultDebitingMethodId;
    private int defaultSellingMethodId;
    private String name;
    private String description;
    private String colorHex;
    private String iconPath;
    private boolean isSuccess;

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentId() {
        return parentId;
    }

    public int getTagId() {
        return tagId;
    }

    public int getDefaultDimensionId() {
        return defaultDimensionId;
    }

    public int getDefaultDebitingMethodId() {
        return defaultDebitingMethodId;
    }

    public int getDefaultSellingMethodId() {
        return defaultSellingMethodId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getColorHex() {
        return colorHex;
    }

    public String getIconPath() {
        return iconPath;
    }

    public boolean isSuccess() {
        return isSuccess;
    }


    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public void setDefaultDimensionId(int defaultDimensionId) {
        this.defaultDimensionId = defaultDimensionId;
    }

    public void setDefaultDebitingMethodId(int defaultDebitingMethodId) {
        this.defaultDebitingMethodId = defaultDebitingMethodId;
    }

    public void setDefaultSellingMethodId(int defaultSellingMethodId) {
        this.defaultSellingMethodId = defaultSellingMethodId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setColorHex(String colorHex) {
        this.colorHex = colorHex;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }
}
