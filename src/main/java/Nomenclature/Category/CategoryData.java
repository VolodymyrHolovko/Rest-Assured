package Nomenclature.Category;

public class CategoryData {
public Category createCategoty( ){
    Category category = new Category();
    category.setDepartmentId(null);
    category.setAddressId(2);
    category.setParentId(2);
    category.setCategoryTypeId(1);
    category.setName("MaxCreAted");
    category.setDescription("This is the perfect Category");
    category.setColorHex("#221133");
    category.setIconPath("Images/201710/file636449551671201110.png");
    return category;
}

    public Category updateCategoty( ){
        Category category = new Category();
        category.setDepartmentId(null);
        category.setAddressId(2);
        category.setParentId(1);
        category.setCategoryTypeId(1);
        category.setName("MaxCreAted2");
        category.setDescription("This is the perfect Category2");
        category.setColorHex("#221132");
        category.setIconPath("Images/201710/file636449552169080100.png");
        return category;
    }
}
