package Photos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Photos {
    private String data;
    private String dateTime;
    private String description;
    @JsonProperty("isSuccess")
    private boolean isSuccess;
    @JsonProperty("isDataFull")
    private boolean isDataFull;

    private String photoPath;

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getData() {
        return data;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public boolean isDataFull() {
        return isDataFull;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public void setDataFull(boolean dataFull) {
        isDataFull = dataFull;
    }
}

