package service;

import dao.CustomerDao;
import dto.LoginDto;

/**
 * Created by laonen on 25.01.2017.
 */
public class LoginService {
    private static final LoginService instance = new LoginService();

    private LoginService() {
    }

    public static LoginService getInstance() {
        return instance;
    }

    public long idUser(LoginDto dto) {
        return new CustomerDao().idByNamePwd(dto.getUsername(), dto.getPassword());
    }

    public String getRole(LoginDto dto) {
        return new CustomerDao().getRole(dto.getUsername());
    }
}