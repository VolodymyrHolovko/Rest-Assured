package BookingREST.СategoryWarehouses;

public class CategoryWarehousesData {
    public CategoryWarehouses addNewCategory(int business_id, String name) {
        CategoryWarehouses addCategory = new CategoryWarehouses();
        addCategory.setBusiness_id(business_id);
        addCategory.setNode_id(Integer.parseInt(null));
        addCategory.setName(name);
        return addCategory;
    }
    }
