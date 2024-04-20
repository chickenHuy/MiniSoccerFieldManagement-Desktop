package minisoccerfieldmanagement.service;

import java.util.List;
import minisoccerfieldmanagement.dao.IUserDAO;
import minisoccerfieldmanagement.dao.UserDAOImpl;
import minisoccerfieldmanagement.model.User;

public class UserServiceImpl implements IUserService{

    IUserDAO userDAO = new UserDAOImpl();
    
    @Override
    public Boolean add(User model) {
        return userDAO.add(model);
    }

    @Override
    public Boolean update(User model) {
        return userDAO.update(model);
    }

    @Override
    public Boolean softDelete(int id) {
        return userDAO.softDelete(id);
    }

    @Override
    public User findById(int id) {
        return userDAO.findById(id);
    }

    @Override
    public User verifyLoginData(String userName, String password) {
        return userDAO.verifyLoginData(userName, password);
    }

    @Override
    public Boolean changeRole(int id, String role) {
        return userDAO.changeRole(id, role);
    }

    @Override
    public Boolean changePassword(int id, String oldPass, String newPass) {
        return userDAO.changePassword(id, oldPass, newPass);
    }
    
    @Override
    public Boolean checkPhoneNumberExistExceptCurrent(int id, String phoneNumber) {
        return userDAO.checkPhoneNumberExistExceptCurrent(id, phoneNumber);
    }
    
    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }
    
    @Override
    public Boolean checkPhoneNumberExist(String phoneNumber) {
        return userDAO.checkPhoneNumberExist(phoneNumber);
    }
    
    @Override
    public Boolean checkUsernameExist(String username) {
        return userDAO.checkUsernameExist(username);
    }
    
    @Override
    public Boolean checkUsernameExistExceptCurrent(String username, int userId) {
        return userDAO.checkUsernameExistExceptCurrent(username, userId);
    }

    @Override
    public List<User> getTopKpi(int top) {
        return  userDAO.getTopKpi(top);
    }
    
}
