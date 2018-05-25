package BookingREST.ServiceGroups.Services;

public class ServiceData {
    public Service CreateService(int businessID, int serviceGroupID){
        Service service = new Service();
        service.setBusiness_id(businessID);
        service.setService_group_id(serviceGroupID);
        service.setName("ServiceName");
        service.setDuration(20);
        service.setPrice(700);
        service.setCurrency("UAH");
        service.setStatus(1);
        return service;


    }



    public Service UpdateService(){
        Service service = new Service();
        service.setName("updatedServiceName");
        service.setDuration(30);
        service.setPrice(800);
        service.setCurrency("USD");
        return service;
    }

}
