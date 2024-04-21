package minisoccerfieldmanagement.dao;

import java.util.List;
import minisoccerfieldmanagement.model.User;

public interface IUserDAO {

    Boolean add(User model);

    Boolean update(User model);

    Boolean softDelete(int id);

    User findById(int id);

    User verifyLoginData(String userName, String password);

    Boolean changeRole(int id, String role);

    Boolean changePassword(int id, String oldPass, String newPass);
    
    Boolean checkPhoneNumberExistExceptCurrent(int id, String phoneNumber);
    
    List<User> findAll();
    
    Boolean checkPhoneNumberExist(String phoneNumber);
    
    Boolean checkUsernameExist(String username);
    
    Boolean checkUsernameExistExceptCurrent(String username, int userId);
    
    List<User> getTopKpi(int top);

    List<User> findAllAndFilter(String search);
    
}
