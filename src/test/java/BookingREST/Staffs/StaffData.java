package BookingREST.Staffs;

public class StaffData {
    public Staff createStaff(int businesId, int addressId, String phone, String email){
        Staff staff = new Staff();
        staff.setBusiness_id(businesId);
        staff.setAddress_id(addressId);
        staff.setFirst_name("Max");
        staff.setLast_name("Lutkovec");
        staff.setPhone(phone);
        staff.setEmail(email);
        staff.setPhoto("https://staging.eservia.com/image/media/201805/jAgUxCmshMJuFrFl.png");
        staff.setPassword("11111111");
        staff.setPosition("Паріхмахєр");
        staff.setDescription("Підстрижу по повній");
        staff.setStatus(1);
        return  staff;
    }
    public Staff updateStaff( String phone, String email){
        Staff staff = new Staff();
        staff.setFirst_name("Maxa");
        staff.setLast_name("Lutkoveca");
        staff.setPhone(phone);
        staff.setEmail(email);
        staff.setPhoto("https://staging.eservia.com/image/media/201805/jAgUxCmshMJuFrFl1.png");
        staff.setPosition("Паріхмахєр");
        staff.setDescription("Підстрижу по повній");
        return  staff;
    }
}
