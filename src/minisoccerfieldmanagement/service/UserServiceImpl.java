package minisoccerfieldmanagement.service;

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
    
}
