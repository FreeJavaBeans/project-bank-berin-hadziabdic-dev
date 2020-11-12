package bankingapp.Services.Classes;

import bankingapp.ApplicationLookUpTable.Definitions;
import bankingapp.Model.Classes.AccountApplication;
import bankingapp.Model.Classes.AccountApplicationDAO;
import bankingapp.Protocol.Classes.RequestProtocol;
import bankingapp.Protocol.Classes.ResponseProtocol;
import bankingapp.Services.Interfaces.HandlerService;

public class ApplyForAccountHandler implements HandlerService {

    String cert = "1-z0-811";

    @Override
    public ResponseProtocol Handler(RequestProtocol requestPayload) throws Exception {
        // TODO Auto-generated method stub
        ResponseProtocol resp = null;
        AccountApplicationDAO appDao = AccountApplicationDAO.GetSingleton();
        AccountApplication app = new AccountApplication();
        boolean success = false;

        // Integer fromAccount = (Integer)
        // requestPayload.GetNumericHeader(Definitions.FROM_ACCOUNT);
        // Integer toAccount = requestPayload.GetNumericHeader(Definitions.TO_ACCOUNT);
        Double balance = requestPayload.GetNumericHeader(Definitions.ACCOUNT_BALANCE);
        String username = requestPayload.GetStringHeader(Definitions.USERNAME);

        app.setStartingAmount(balance);
        app.setUsername(username);

        success = appDao.Create(app);

        if (success)
            resp = new ResponseProtocol(true,
                    "Application succesfully created. A representative will process it soon.");
        else {
            resp = new ResponseProtocol(false, "Your application failed. Please try again at a later time.");
        }

        return resp;
    }

}
