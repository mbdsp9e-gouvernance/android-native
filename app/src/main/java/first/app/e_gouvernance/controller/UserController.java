package first.app.e_gouvernance.controller;

import android.content.Context;

import first.app.e_gouvernance.model.User;
import first.app.e_gouvernance.util.LoginCallBack;


// Final because we don't want another class to derive from it and create another instance
public final class UserController {
    private static UserController instance = null;

    private UserController() {
        super();
    }

    /**
     * Create instance
     *
     * @return instance
     */
    public static final UserController getInstance() {
        if (UserController.instance == null) {
            UserController.instance = new UserController();
        }
        return UserController.instance;
    }

    /**
     * Verify authentication
     *
     * @param email
     * @param password
     * @return true or false
     */
    public void verifyLogin(String email, String password, Context context, LoginCallBack callBack) {
        User user = new User();
        user.login(email, password, context, callBack);
    }

}
