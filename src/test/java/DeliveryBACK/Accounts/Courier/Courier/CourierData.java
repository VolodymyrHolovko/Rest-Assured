package DeliveryBACK.Accounts.Courier.Courier;

import DeliveryBACK.Accounts.Courier.Courier.Courier;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;

public class CourierData {
    public Courier createCourier(){
        Faker faker = new Faker();
        String phone = faker.regexify("+380[0-9]{9}");
        Courier courier = new Courier();
        List<Courier.userData> userData1 = new ArrayList<>();
        Courier.userData userData = new Courier.userData();
        userData.setPhoneNumber(phone);
        userData.setFirstName("test");
        userData.setLastName("courier");
        userData.setPhoto(null);
        userData.setPassword("11111111");
        userData1.add(userData);
        courier.setUserData(userData1);

        List<Courier.courierInfo> courierInfos = new ArrayList<>();
        Courier.courierInfo courierInfo = new Courier.courierInfo();
        courierInfo.setVehicleModel("engine");
        courierInfo.setFuelCoefficient(1);
        courierInfos.add(courierInfo);
        courier.setCourierInfos(courierInfos);

        return courier;
    }
}
