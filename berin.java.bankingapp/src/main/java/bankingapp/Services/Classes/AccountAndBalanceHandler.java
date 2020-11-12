package bankingapp.Services.Classes;

import java.util.Set;

import bankingapp.ApplicationLookUpTable.Definitions;
import bankingapp.Model.Classes.BankAccountDAO;
import bankingapp.Protocol.Classes.RequestProtocol;
import bankingapp.Protocol.Classes.ResponseProtocol;
import bankingapp.Services.Interfaces.HandlerService;

public class AccountAndBalanceHandler implements HandlerService {

    @Override
    public ResponseProtocol Handler(RequestProtocol requestPayload) throws Exception {
        // TODO Auto-generated method stub

        ResponseProtocol resp = null;
        String userId = requestPayload.GetStringHeader(Definitions.USERNAME);

        BankAccountDAO accountDao = BankAccountDAO.GetSingleton();
        Set<String> respBody = accountDao.ReadAccountIdBalanceByUsername(userId);

        if (respBody.size() > 0) {
            resp = new ResponseProtocol(true, respBody);

        } else if (respBody.size() == 0) {
            resp = new ResponseProtocol(false, "We found no registered accounts.");

        } else {
            resp = new ResponseProtocol(false, "There was an issue reading account  for" + userId.toString()
                    + ". Please ensure you have entered the correct details and try again.");
        }

        return resp;

    }

}
