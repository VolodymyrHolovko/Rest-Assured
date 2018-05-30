package BookingREST.ServiceGroups;

public class ServiceGroupData {
    public ServiceGroup CreateServiceGroup(int business_id){
       ServiceGroup serviceGroup = new ServiceGroup();
       serviceGroup.setBusiness_id(business_id);
       serviceGroup.setName("Servicegroupname");
       return serviceGroup;


    }

    public ServiceGroup UpdateServiceGroup(){
        ServiceGroup serviceGroup = new ServiceGroup();
        serviceGroup.setName("UpdatedServicegroupname");
        return serviceGroup;


    }



}
