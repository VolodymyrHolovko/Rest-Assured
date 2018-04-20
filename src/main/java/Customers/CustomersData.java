package Customers;


import java.util.ArrayList;
import java.util.List;

public class CustomersData {

    public int getRandom_number1() {
        int a = 1000;
        int b = 8888;
        int random_number1 = a + (int) (Math.random() * b);
        return  random_number1;
    }

    public Customers createCustomer(){
        int r1 = getRandom_number1();
        int r2 = getRandom_number1();
        String eserviaId="eb3bdcc9-"+r1+"-48d0-"+r2+"-9e42cf116bc6";
        Customers customers = new Customers();
        customers.setEserviaId(eserviaId);
        customers.setFirstName("Max");
        customers.setLastName("Lutkovec");
        customers.setMiddleName("Vasylovych");
        customers.setPhotoPath("media/201804/lxCWRpLL1Vm5Uz5y.jpg");
        customers.setSex("Male");
        customers.setPhoneNumber("+380679296215");
        customers.setEmail("lutkovec@gmail.com");
        customers.setBusinessId(1);
        return  customers;
    }

    public Customers updateCustomer(String eserviaId){
        Customers customers = new Customers();
        customers.setId(eserviaId);
        customers.setFirstName("Max1");
        customers.setLastName("Lutkovec1");
        customers.setMiddleName("Vasylovych1");
        customers.setPhotoPath("media/201804/lxCWRpLL1Vm5Uz52.jpg");
        customers.setSex("Female");
        customers.setPhoneNumber("+380679296216");
        customers.setEmail("lutkovec1@gmail.com");
        return customers;
    }

    public Customer1 blockCustomer(String eserviaId){
        Customer1 customers = new Customer1();
        ArrayList<Integer> busines = new ArrayList<>();
        busines.add(1);
        customers.setBusinesses(busines);
        customers.setId(eserviaId);
        return customers;
    }
}
