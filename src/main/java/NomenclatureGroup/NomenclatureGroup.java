package NomenclatureGroup;

public class NomenclatureGroup {
    NomenclatureGroup data;
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

    public NomenclatureGroup(NomenclatureGroup data, int parentId, int tagId, int defaultDimensionId, int defaultDebitingMethodId, int defaultSellingMethodId, String name, String description, String colorHex, String iconPath, boolean isSuccess) {
        this.data = data;
        this.parentId = parentId;
        this.tagId = tagId;
        this.defaultDimensionId = defaultDimensionId;
        this.defaultDebitingMethodId = defaultDebitingMethodId;
        this.defaultSellingMethodId = defaultSellingMethodId;
        this.name = name;
        this.description = description;
        this.colorHex = colorHex;
        this.iconPath = iconPath;
        this.isSuccess = isSuccess;
    }

    public NomenclatureGroup getData() {
        return data;
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

    public void setData(NomenclatureGroup data) {
        this.data = data;
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
    //dfsdfsdf
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

    @Override
    public String toString() {
        return "NomenclatureGroup{" + "data=" + data + ", parentId=" + parentId + ", tagId=" + tagId + ", defaultDimensionId=" + defaultDimensionId + ", defaultDebitingMethodId=" + defaultDebitingMethodId + ", defaultSellingMethodId=" + defaultSellingMethodId + ", name='" + name + '\'' + ", description='" + description + '\'' + ", colorHex='" + colorHex + '\'' + ", iconPath='" + iconPath + '\'' + ", isSuccess=" + isSuccess + '}';
    }
}
