package BookingREST.Addresses;

public class AddressData {
    public Address CreateAddress(int business_id){
        Address address = new Address();
        address.setBusiness_id(business_id);
        address.setCity("Київ");
        address.setStreet("вулиця Миколи Грінченка");
        address.setNumber("28");
        address.setLat("50.410133");
        address.setLng("30.521291700000006");
        address.setPhone("+380971234567");
        address.setMark("Біля чогосьтам");
        address.setPlace_hash("EkrQstGD0LvQuNGG0Y8g0JzQuNC60L7Qu9C4INCT0YDRltC90YfQtdC90LrQsCwgMjgsINCa0LjRl9CyLCDQo9C60YDQsNGX0L3QsA");
        return address;

    }



    public Address UpdateAddress(int business_id){
        Address address = CreateAddress(business_id);
        //address.setId(id);
        address.setCity("Київ");
        address.setStreet("вулиця Вадима Гетьмана");
        address.setNumber("7");
        address.setLat("50.4516007");
        address.setLng("30.444661699999983");
        address.setPhone("+380977777777");
        address.setMark("Біля чогосьіншого");
        address.setPlace_hash("EkfQstGD0LvQuNGG0Y8g0JLQsNC00LjQvNCwINCT0LXRgtGM0LzQsNC90LAsIDcsINCa0LjRl9CyLCDQo9C60YDQsNGX0L3QsA");
        return address;



    }


}
