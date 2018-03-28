package Departments.Tables;

import java.time.LocalTime;

public class TablesData {
    public Tables createTable(int department,String code) {
        Tables tables = new Tables();
        tables.setCode(code);
        tables.setBookingAvailable(true);
        tables.setDepartmentId(department);
        tables.setCapacity(3);
        return tables;
    }
    public Tables updateTable(int department,String code,int tableId){
        Tables tables = createTable(department,code);
        tables.setCode(code);
        tables.setBookingAvailable(false);
        tables.setDepartmentId(department);
        tables.setCapacity(2);
        tables.setId(tableId);
        return tables;
    }
}
