package Departments;

import org.json.JSONObject;

import java.time.LocalDate;

public class DepartmentData  {
    public Department CreatePreparingDepartments(String name) {
        Department department = new Department();
        department.setTypeId(1);
        department.setMain(false);
        department.setActive(true);
        department.setName(name);
        department.setAddressId(2);
        return department;
    }
    public Department UpdateDepartment(String name,int id){
        Department department = CreatePreparingDepartments(name);
        department.setId(id);
        department.setTypeId(1);
        department.setActive(true);
        department.setName(name);
        return department;
    }
}
