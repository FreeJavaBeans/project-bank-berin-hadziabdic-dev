package bankingapp.Services.Classes;

import bankingapp.ApplicationLookUpTable.Definitions;
import bankingapp.Model.Classes.User;
import bankingapp.Model.Classes.UserDAO;
import bankingapp.Protocol.Classes.RequestProtocol;
import bankingapp.Protocol.Classes.ResponseProtocol;
import bankingapp.Services.Interfaces.HandlerService;
import bankingapp.UtilityClasses.AppUtility;

public class LoginAuthenticateUserHandler implements HandlerService {

    @Override
    public ResponseProtocol Handler(RequestProtocol requestPayload) throws Exception {
        // TODO Auto-generated method stub

        ResponseProtocol resp = null;
        String username = requestPayload.GetStringHeader(Definitions.USERNAME);
        String password = requestPayload.GetStringHeader(Definitions.PASSWORD);

        UserDAO userDao = UserDAO.GetSingleton();
        User logInUser = userDao.Read(username);

        if (logInUser != null && AppUtility.StringCompare(logInUser.getUsername(), username)
                && AppUtility.StringCompare(logInUser.getPassword(), password)) {
            resp = new ResponseProtocol(true, "User " + username + " logged in succesfully!");
            resp.SetStringHeader(Definitions.ACCOUNT_TYPE, logInUser.getAccountType());
        } else
            resp = new ResponseProtocol(false,
                    "The requested user was not found on the server. Please try again with valid credentials.");

        return resp;
    }

}
