package BookingREST.СategoryWarehouses;

public class CategoryWarehousesData {
    public CategoryWarehouses addNewCategory(int business_id, String name) {
        CategoryWarehouses addCategory = new CategoryWarehouses();
        addCategory.setBusiness_id(business_id);
        addCategory.setNode_id(null);
        addCategory.setName(name);
        return addCategory;
    }
    public CategoryWarehouses updateCategory(String node_id, String name2) {
        CategoryWarehouses updateCategory = new CategoryWarehouses();
        updateCategory.setNode_id(node_id);
        updateCategory.setName(name2);
        return updateCategory;
    }
}
