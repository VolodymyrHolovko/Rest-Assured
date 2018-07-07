package BookingREST.Plans;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

public class PlanSettings {


    @JsonProperty("address:limit")
    @SerializedName("address:limit")
    private int addresslimit;
    @JsonProperty("address:purchase_limit")
    @SerializedName("address:purchase_limit")
    private int addresspurchase_limit;
    @JsonProperty("staff:limit")
    @SerializedName("staff:limit")
    private int stafflimit;
    @JsonProperty("staff:purchase_limit")
    @SerializedName("staff:purchase_limit")
    private int staffpurchase_limit;
    @JsonProperty("staff:authentication")
    @SerializedName("staff:authentication")
    private boolean staffauthentication;
    @SerializedName("marketing")
    @JsonProperty("marketing")
    private boolean marketing;
    @JsonProperty("booking:user_history_limit")
    @SerializedName("booking:user_history_limit")
    private int bookinguser_history_limit;
    @JsonProperty("booking:create_max_duration")
    @SerializedName("booking:create_max_duration")
    private int bookingcreate_max_duration;
    @JsonProperty("booking:analytics")
    @SerializedName("booking:analytics")
    private boolean bookinganalytics;
    private boolean inventory;
    private boolean finance;

    public boolean isStaffauthentication() {
        return staffauthentication;
    }

    public boolean isMarketing() {
        return marketing;
    }

    public boolean isBookinganalytics() {
        return bookinganalytics;
    }

    public boolean isInventory() {
        return inventory;
    }

    public void setInventory(boolean inventory) {
        this.inventory = inventory;
    }

    public boolean isFinance() {
        return finance;
    }

    public void setFinance(boolean finance) {
        this.finance = finance;
    }

    public int getAddresslimit() {
        return addresslimit;
    }

    public void setAddresslimit(int addresslimit) {
        this.addresslimit = addresslimit;
    }

    public int getAddresspurchase_limit() {
        return addresspurchase_limit;
    }

    public void setAddresspurchase_limit(int addresspurchase_limit) {
        this.addresspurchase_limit = addresspurchase_limit;
    }

    public int getStafflimit() {
        return stafflimit;
    }

    public void setStafflimit(int stafflimit) {
        this.stafflimit = stafflimit;
    }

    public int getStaffpurchase_limit() {
        return staffpurchase_limit;
    }

    public void setStaffpurchase_limit(int staffpurchase_limit) {
        this.staffpurchase_limit = staffpurchase_limit;
    }



    public int getBookinguser_history_limit() {
        return bookinguser_history_limit;
    }

    public void setBookinguser_history_limit(int bookinguser_history_limit) {
        this.bookinguser_history_limit = bookinguser_history_limit;
    }

    public int getBookingcreate_max_duration() {
        return bookingcreate_max_duration;
    }

    public void setBookingcreate_max_duration(int bookingcreate_max_duration) {
        this.bookingcreate_max_duration = bookingcreate_max_duration;
    }

    public boolean getStaffauthentication() {
        return staffauthentication;
    }

    public void setStaffauthentication(boolean staffauthentication) {
        this.staffauthentication = staffauthentication;
    }

    public boolean getMarketing() {
        return marketing;
    }

    public void setMarketing(boolean marketing) {
        this.marketing = marketing;
    }

    public boolean getBookinganalytics() {
        return bookinganalytics;
    }

    public void setBookinganalytics(boolean bookinganalytics) {
        this.bookinganalytics = bookinganalytics;
    }



}
