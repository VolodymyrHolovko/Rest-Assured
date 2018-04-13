package Departments;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DepartmentResponse {
    public Department data;

    public Department getData() {
        return data;
    }

    public void setData(Department data) {
        this.data = data;
    }
}
