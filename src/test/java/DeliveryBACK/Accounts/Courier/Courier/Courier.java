package DeliveryBACK.Accounts.Courier.Courier;

import java.util.List;

public class Courier {
    List<userData> userData;
    List<courierInfo> courierInfos;

    public List<Courier.userData> getUserData() {
        return userData;
    }

    public void setUserData(List<Courier.userData> userData) {
        this.userData = userData;
    }

    public List<courierInfo> getCourierInfos() {
        return courierInfos;
    }

    public void setCourierInfos(List<courierInfo> courierInfos) {
        this.courierInfos = courierInfos;
    }

    public static class userData{
        private String phoneNumber;
        private String firstName;
        private String lastName;
        private String photo;
        private String password;

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    public static class courierInfo{
        private String vehicleModel;
        private int fuelCoefficient;

        public String getVehicleModel() {
            return vehicleModel;
        }

        public void setVehicleModel(String vehicleModel) {
            this.vehicleModel = vehicleModel;
        }

        public int getFuelCoefficient() {
            return fuelCoefficient;
        }

        public void setFuelCoefficient(int fuelCoefficient) {
            this.fuelCoefficient = fuelCoefficient;
        }
    }
}
