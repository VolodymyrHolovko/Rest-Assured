package Departments.Tables;

public class TableResponse {
    Tables data;
    Tables error;

    public Tables getData() {
        return data;
    }

    public void setData(Tables data) {
        this.data = data;
    }

    public Tables getError() {
        return error;
    }

    public void setError(Tables error) {
        this.error = error;
    }
}
