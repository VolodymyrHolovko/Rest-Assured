package Menu;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MenuResponse {

    long data;

    @JsonProperty
    boolean isSuccess;

    public long getData() {
        return data;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setData(long data) {
        this.data = data;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }
}
