package BookingREST.WorkingDays;

import java.util.List;

public class WorkingDaysResponseArray {
    List<WorkingDays> data;
    WorkingDays links;
    WorkingDays meta;

    public List<WorkingDays> getData() {
        return data;
    }

    public void setData(List<WorkingDays> data) {
        this.data = data;
    }

    public WorkingDays getLinks() {
        return links;
    }

    public void setLinks(WorkingDays links) {
        this.links = links;
    }

    public WorkingDays getMeta() {
        return meta;
    }

    public void setMeta(WorkingDays meta) {
        this.meta = meta;
    }
}
