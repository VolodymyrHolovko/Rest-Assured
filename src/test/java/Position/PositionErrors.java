package Position;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PositionErrors {
    Position error;
    public Position getError() {
        return error;
    }

    public void setError(Position error) {
        this.error = error;
    }

    private boolean data;
    private  String id;
    @JsonProperty("isSuccess")
    private boolean isSuccess;
    public boolean isData() {
        return data;
    }


    public void setData(boolean data) {
        this.data = data;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }
}
