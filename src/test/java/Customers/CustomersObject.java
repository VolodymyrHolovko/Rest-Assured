package Customers;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class CustomersObject {
        private String eserviaId;
        private String firstName;
        private String lastName;
        private String middleName;
        private String photoPath;
        private String birthday;
        private String sex;
        private String phoneNumber;
        private String email;
        private List<Integer> businesses;
        @JsonProperty
        private String id;

        private String errorDescription;


        public String getEserviaId() {
            return eserviaId;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getMiddleName() {
            return middleName;
        }

        public String getPhotoPath() {
            return photoPath;
        }

        public String getBirthday() {
            return birthday;
        }

        public String getSex() {
            return sex;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public String getEmail() {
            return email;
        }


        public String getId() {
            return id;
        }

        public String getErrorDescription() {
            return errorDescription;
        }

        public void setEserviaId(String eserviaId) {
            this.eserviaId = eserviaId;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public void setMiddleName(String middleName) {
            this.middleName = middleName;
        }

        public void setPhotoPath(String photoPath) {
            this.photoPath = photoPath;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public void setEmail(String email) {
            this.email = email;
        }


    public List<Integer> getBusinesses() {
        return businesses;
    }

    public void setBusinesses(List<Integer> businesses) {
        this.businesses = businesses;
    }



    public void setId(String id) {
            this.id = id;
        }

        public void setErrorDescription(String errorDescription) {
            this.errorDescription = errorDescription;
        }
    }

    class businesses{
       int id;
       String eserviaId;
       String phoneNumber;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getEserviaId() {
            return eserviaId;
        }

        public void setEserviaId(String eserviaId) {
            this.eserviaId = eserviaId;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }
    }


