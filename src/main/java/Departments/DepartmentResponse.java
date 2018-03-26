package Departments;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DepartmentResponse {
    Department data;

    public Department getData() {
        return data;
    }

    public void setData(Department data) {
        this.data = data;
    }
}
