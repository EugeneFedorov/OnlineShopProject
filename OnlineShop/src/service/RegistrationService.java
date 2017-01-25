package service;

import dao.AdressDao;
import dao.CustomerDao;
import dao.HomeDao;
import dto.RegistrationDto;
import entity.Adress;
import entity.Customer;
import entity.Home;

/**
 * Created by laonen on 25.01.2017.
 */
public class RegistrationService {
    private static final RegistrationService instance = new RegistrationService();

    private RegistrationService() {
    }

    public static RegistrationService getInstance() {
        return instance;
    }

    public boolean isRegistration(RegistrationDto dto){
        Home home = new Home(dto.getStreet(),dto.getNumberHouse(),dto.getNumberFlat());
        home.setId(new HomeDao().create(home));
        Adress adress = new Adress(dto.getCountry(), dto.getTown(), dto.getPost_index(), home);
        adress.setId(new AdressDao().create(adress));
        Customer customer = new Customer();
        return new CustomerDao().create(customer)> 0L;
    }
}
