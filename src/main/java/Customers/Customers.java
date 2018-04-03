package Customers;

public class Customers {
    private int eserviaId;
    private String firstName;
    private String lastName;
    private String middleName;
    private String photoPath;
    private String birthday;
    private String sex;
    private String phoneNumber;
    private String email;
    private int businessId;

    public int getId() {
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

    public int getBusinessId() {
        return businessId;
    }

    public void setId(int eserviaId) {
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

    public void setBusinessId(int businessId) {
        this.businessId = businessId;
    }
}
