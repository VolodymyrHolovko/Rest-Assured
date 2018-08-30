package DeliveryBACK.Accounts.Courier.Admin;

public class AdminData {
    public Admin createAdmin(){
        Admin admin = new Admin();
        admin.setEmail("");
        admin.setEstablishmentId(1);
        admin.setPhoneNumber("");
        admin.setFirstName("");
        admin.setLastName("");
        admin.setPhoto("");
        admin.setPassword("");
        return  admin;
    }
}
