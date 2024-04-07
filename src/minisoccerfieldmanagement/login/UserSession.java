package minisoccerfieldmanagement.login;

import minisoccerfieldmanagement.model.User;

public class UserSession {

    private static UserSession instance;
    private User user;
    private boolean isLoggedIn;

    private UserSession() {
        isLoggedIn = false;
    }

    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    public void loginUser(User user) {
        this.user = user;
        isLoggedIn = true;
    }

    public void logoutUser() {
        user = null;
        isLoggedIn = false;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public User getUser() {
        return this.user;
    }

}
