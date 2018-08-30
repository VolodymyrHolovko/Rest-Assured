package DeliveryBACK.Accounts.Courier.Logist;

public class LogistData {
    public  Logist createLogist(){
        Logist logist = new Logist();
        logist.setEmail("");
        logist.setEstablishmentId(1);
        logist.setPhoneNumber("");
        logist.setFirstName("");
        logist.setLastName("");
        logist.setPhoto("");
        logist.setPassword("");
        return logist;
    }
}
