package bankingapp.Services.Classes;

import java.util.Set;

import bankingapp.ApplicationLookUpTable.Definitions;
import bankingapp.Model.Classes.BankAccountDAO;
import bankingapp.Protocol.Classes.RequestProtocol;
import bankingapp.Protocol.Classes.ResponseProtocol;
import bankingapp.Services.Interfaces.HandlerService;

public class GetAllCustomerAccountsHandler implements HandlerService {

    @Override
    public ResponseProtocol Handler(RequestProtocol requestPayload) throws Exception {
        // TODO Auto-generated method stub

        ResponseProtocol resp;
        String username = requestPayload.GetStringHeader(Definitions.USERNAME);
        BankAccountDAO accountDao = BankAccountDAO.GetSingleton();
        Set<String> response = accountDao.ReadAllById(username);

        resp = new ResponseProtocol(true, response);

        return resp;
    }

}
