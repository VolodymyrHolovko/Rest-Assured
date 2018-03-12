package Departments;

import org.json.JSONObject;

public class DepartmentData {
    public  JSONObject allDepartments() {
        JSONObject dep = new JSONObject();
        dep.put("id", 7);
        dep.put("typeId", 1);
        dep.put("isMain",true);
        dep.put("isActive", true);
        dep.put("name", "2string");
        dep.put("establishmentId", 1);
        dep.put("printerId", JSONObject.NULL);
        dep.put("storageId", JSONObject.NULL);
        dep.put("groupId", 1);
        dep.put("currentMenuId", JSONObject.NULL);
        return dep;


    }
}
