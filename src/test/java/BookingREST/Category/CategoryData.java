package BookingREST.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryData {
    public Category addCategories(String category, int sector_id, int strategy_id, String enName) {
        Category addCategory = new Category();
        addCategory.setCategory(category);
        addCategory.setSector_id(sector_id);
        addCategory.setStrategy_id(strategy_id);
        addCategory.setName_en(enName);
        addCategory.setName_ru("раша");
        addCategory.setName_uk("категорія");
        addCategory.setStatus(0);
        return addCategory;
    }
    public Category updateCategories(String enNameUpdate) {
        Category updateCategory = new Category();
        updateCategory.setName_en(enNameUpdate);
        updateCategory.setName_ru("раша-па*аша");
        updateCategory.setName_uk("категорія оновлена");
        return  updateCategory;
    }
    public Category addCategoryToBusinessId(int id) {
        Category addToBusiness = new Category();
        List<Integer> categories = new ArrayList<>();
        categories.add(id);
        addToBusiness.setCategories(categories);
        return  addToBusiness;
    }
}
