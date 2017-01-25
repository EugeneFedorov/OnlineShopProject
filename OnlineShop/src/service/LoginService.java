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

    public boolean isExist(LoginDto dto) {
        return new CustomerDao().isByNamePwd(dto.getUsername(), dto.getPassword());
    }
}
