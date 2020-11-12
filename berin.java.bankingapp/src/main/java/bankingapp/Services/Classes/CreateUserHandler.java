package bankingapp.Services.Classes;

import bankingapp.ApplicationLookUpTable.Definitions;
import bankingapp.Model.Classes.User;
import bankingapp.Model.Classes.UserDAO;
import bankingapp.Protocol.Classes.RequestProtocol;
import bankingapp.Protocol.Classes.ResponseProtocol;
import bankingapp.Services.Interfaces.HandlerService;

public class CreateUserHandler implements HandlerService {

    @Override
    public ResponseProtocol Handler(RequestProtocol requestPayload) {
        // TODO Auto-generated method stub

        User userToCreate = new User();
        ResponseProtocol response = null;

        userToCreate.setUsername(requestPayload.GetStringHeader(Definitions.USERNAME));
        userToCreate.setPassword(requestPayload.GetStringHeader(Definitions.PASSWORD));

        UserDAO createUser = UserDAO.GetSingleton();

        try {
            boolean success = createUser.Create(userToCreate);
            response = new ResponseProtocol(success, "User created");
        } catch (Exception e) {
            response = new ResponseProtocol(false,
                    "User creation failed. Did you enter the right username and password combination?");
        }

        return response;
    }

}
