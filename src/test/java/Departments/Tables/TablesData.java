package Departments.Tables;

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
    public Tables setBeaconId(int tableId,String beaconId){
        Tables tables = new Tables();
        tables.setBeaconId(beaconId);
        tables.setId(tableId);
        return tables;
    }
}
