package bankingapp.Services.Classes;

import bankingapp.ApplicationLookUpTable.Definitions;
import bankingapp.Model.Classes.BankAccount;
import bankingapp.Model.Classes.BankAccountDAO;
import bankingapp.Protocol.Classes.RequestProtocol;
import bankingapp.Protocol.Classes.ResponseProtocol;
import bankingapp.Services.Interfaces.HandlerService;

public class BalanceViewHandler implements HandlerService {

    @Override
    public ResponseProtocol Handler(RequestProtocol requestPayload) throws Exception {
        // TODO Auto-generated method stub

        ResponseProtocol resp = null;
        Integer accountId = requestPayload.GetNumericIntegerHeader(Definitions.ACCOUNT_NUMBER);

        BankAccountDAO accountDao = BankAccountDAO.GetSingleton();
        BankAccount balanceAccount = accountDao.Read(accountId);

        if (balanceAccount != null) {
            resp = new ResponseProtocol(true, balanceAccount.getBalance().toString());

        } else {
            resp = new ResponseProtocol(false, "There was an issue reading account " + accountId.toString()
                    + ". Please ensure the account is properly .");
        }

        return resp;
    }

}
