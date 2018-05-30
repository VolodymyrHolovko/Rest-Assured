package BookingREST.Contact;

public class ContactData {
    public Contact ContactLetter(){
        Contact contact = new Contact();
        contact.setPhone("+380971234567");
        contact.setEmail("testmail@mail.com");
        contact.setName("testname");
        contact.setSubject("Subject");
        contact.setMessage("Message");
        return contact;

    }
}
