package BookingREST.ServiceGroups.Services;

import java.util.List;

public class AddressServicesResponse {
    public List<Service> getData() {
        return data;
    }

    public void setData(List<Service> services) {
        this.data = services;
    }

    public List<Service> data;
}
