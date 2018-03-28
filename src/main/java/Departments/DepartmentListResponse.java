package Departments;

import java.util.List;

public class DepartmentListResponse {
    List<Department> data;

    public List<Department> getData() {
        return data;
    }
    public void setData(List<Department> data) {
        this.data = data;
    }
}
