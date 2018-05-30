package Nomenclature.Category;

public class Category {

   private String departmentId;
   private int addressId;
   private int parentId;
   private int categoryTypeId;
   private String name;
   private String description;
   private String colorHex;
   private String iconPath;
   private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public int getAddressId() {
        return addressId;
    }

    public int getParentId() {
        return parentId;
    }

    public int getCategoryTypeId() {
        return categoryTypeId;
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

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public void setCategoryTypeId(int categoryTypeId) {
        this.categoryTypeId = categoryTypeId;
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
}
