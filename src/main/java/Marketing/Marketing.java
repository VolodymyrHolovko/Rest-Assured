package Marketing;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Marketing {
    public int businessId;
    private int id;
    private int establishmentId;
    private String title;
    private String description;
    private String pathToPhoto;
    private int marketingTypeId;
    private String address;
    private double longitude;
    private double latitude;
    private String beginTime;
    private String endTime;
    @JsonProperty("isActive")
    private boolean isActive;
    private List<Link> links;
    private List<WorkSchedule> workSchedule;


    public int getBusinessId() {
        return businessId;
    }

    public void setBusinessId(int businessId) {
        this.businessId = businessId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEstablishmentId() {
        return establishmentId;
    }

    public void setEstablishmentId(int establishmentId) {
        this.establishmentId = establishmentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPathToPhoto() {
        return pathToPhoto;
    }

    public void setPathToPhoto(String pathToPhoto) {
        this.pathToPhoto = pathToPhoto;
    }

    public int getMarketingTypeId() {
        return marketingTypeId;
    }

    public void setMarketingTypeId(int marketingTypeId) {
        this.marketingTypeId = marketingTypeId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public List<WorkSchedule> getWorkSchedule() {
        return workSchedule;
    }

    public void setWorkSchedule(List<WorkSchedule> workSchedule) {
        this.workSchedule = workSchedule;
    }
}

class Link{

    private int socialTypeId;
    private String url;

    public int getSocialTypeId() {
        return socialTypeId;
    }

    public String getUrl() {
        return url;
    }

    public void setSocialTypeId(int socialTypeId) {
        this.socialTypeId = socialTypeId;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Link{" + "socialTypeId=" + socialTypeId + ", url='" + url + '\'' + '}';
    }
}
class  WorkSchedule {
    private int day;
    private int startTime;
    private int endTime;

    public int getDay() {
        return day;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "WorkSchedule{" + "day=" + day + ", startTime=" + startTime + ", endTime=" + endTime + '}';
    }
}



