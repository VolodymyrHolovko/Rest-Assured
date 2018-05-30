package Marketing;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MarketingErrors {
    Marketing error;

    public Marketing getError() {
        return error;
    }

    public void setError(Marketing error) {
        this.error = error;
    }

    @JsonProperty("isSuccess")
    private boolean isSuccess;
    private String description;


    public String getDescription() {
        return description;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }
}



