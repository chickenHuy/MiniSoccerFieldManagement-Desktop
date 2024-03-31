package minisoccerfieldmanagement.service;

import minisoccerfieldmanagement.model.User;

public interface IUserService {
    
    Boolean add(User model);

    Boolean update(User model);

    Boolean softDelete(int id);
    
    User findById(int id);
    
    User verifyLoginData(String userName, String password);
    
    Boolean changeRole(int id, String role);
    
    Boolean changePassword(int id, String oldPass, String newPass);
    
}
