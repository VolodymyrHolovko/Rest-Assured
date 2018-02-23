import java.util.ArrayList;

public class Profile {
    Profile data;
    private String id;
    private String corporationId;
    private String personalNumber;
    private boolean isActive;
    private String photoPath;
    private String firstName;
    private String secondName;
    private String middleName;
    private String sexId;
    private Long birthday;
    private Long registrationDate;
    private Long medicalCardExpirationDate;
    private String address;
    private String email;
    private String phoneNumber;
    private Long cardNumber;
    private ArrayList positions;
    private int establishmentId;
    private int preparingTime;
    public Profile(String id, String corporationId, String personalNumber, boolean isActive, String photoPath, String firstName, String secondName, String middleName, String sexId, Long birthday, Long registrationDate, Long medicalCardExpirationDate, String address, String email, String phoneNumber, Long cardNumber, ArrayList positions) {
        this.id = id;
        this.corporationId = corporationId;
        this.personalNumber = personalNumber;
        this.isActive = isActive;
        this.photoPath = photoPath;
        this.firstName = firstName;
        this.secondName = secondName;
        this.middleName = middleName;
        this.sexId = sexId;
        this.birthday = birthday;
        this.registrationDate = registrationDate;
        this.medicalCardExpirationDate = medicalCardExpirationDate;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.cardNumber = cardNumber;
        this.positions = positions;
    }

    public void setId(String id) {
        this.id = id;
    }
    public void setPreparingTime(int preparingTime){this.preparingTime = preparingTime;}

    public void setCorporationId(String corporationId) {
        this.corporationId = corporationId;
    }

    public void setPersonalNumber(String personalNumber) {
        this.personalNumber = personalNumber;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setSexId(String sexId) {
        this.sexId = sexId;
    }

    public void setBirthday(Long birthday) {
        this.birthday = birthday;
    }

    public void setRegistrationDate(Long registrationDate) {
        this.registrationDate = registrationDate;
    }

    public void setMedicalCardExpirationDate(Long medicalCardExpirationDate) {
        this.medicalCardExpirationDate = medicalCardExpirationDate;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setPositions(ArrayList positions) {
        this.positions = positions;
    }

    public String getId() {
        return id;
    }

    public int getPreparingTime() {

        return preparingTime;
    }

    public String getPersonalNumber() {
        return personalNumber;
    }

    public boolean isActive() {
        return isActive;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getSexId() {
        return sexId;
    }

    public Long getBirthday() {
        return birthday;
    }

    public Long getRegistrationDate() {
        return registrationDate;
    }

    public Long getMedicalCardExpirationDate() {
        return medicalCardExpirationDate;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Long getCardNumber() {
        return cardNumber;
    }

    public ArrayList getPositions() {
        return positions;
    }

    @Override
    public String toString() {
        return "Profile{" + "id='" + id + '\'' + ", corporationId='" + corporationId + '\'' + ", personalNumber='" + personalNumber + '\'' + ", isActive=" + isActive + ", photoPath='" + photoPath + '\'' + ", firstName='" + firstName + '\'' + ", secondName='" + secondName + '\'' + ", middleName='" + middleName + '\'' + ", sexId='" + sexId + '\'' + ", birthday=" + birthday + ", registrationDate=" + registrationDate + ", medicalCardExpirationDate=" + medicalCardExpirationDate + ", address='" + address + '\'' + ", email='" + email + '\'' + ", phoneNumber='" + phoneNumber + '\'' + ", cardNumber=" + cardNumber + ", positions=" + positions + '}';
    }


}
