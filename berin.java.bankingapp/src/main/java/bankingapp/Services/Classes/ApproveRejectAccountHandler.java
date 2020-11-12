package bankingapp.Services.Classes;

import bankingapp.ApplicationLookUpTable.Definitions;
import bankingapp.Model.Classes.AccountApplication;
import bankingapp.Model.Classes.AccountApplicationDAO;
import bankingapp.Model.Classes.BankAccount;
import bankingapp.Model.Classes.BankAccountDAO;
import bankingapp.Protocol.Classes.RequestProtocol;
import bankingapp.Protocol.Classes.ResponseProtocol;
import bankingapp.Services.Interfaces.HandlerService;

public class ApproveRejectAccountHandler implements HandlerService {

    public ResponseProtocol CreateAccount(RequestProtocol requestPayload, AccountApplication application)
            throws Exception {
        ResponseProtocol response;
        BankAccountDAO bankAccountDAO = BankAccountDAO.GetSingleton();
        BankAccount newAccount = null;

        newAccount = new BankAccount();
        newAccount.setUsername(application.getUsername());
        newAccount.setBalance(application.getStartingAmount());
        bankAccountDAO.Create(newAccount);

        response = new ResponseProtocol(true,
                "Application with ID " + application.getApplicationId().toString() + " approved.");

        return response;
    }

    public ResponseProtocol RejectApp(RequestProtocol requestPayload, AccountApplication application) throws Exception {

        ResponseProtocol response;

        response = new ResponseProtocol(true,
                "Application with ID " + application.getApplicationId().toString() + " rejected.");

        return response;
    }

    @Override
    public ResponseProtocol Handler(RequestProtocol requestPayload) throws Exception {

        ResponseProtocol resp = null;
        String accountAction = requestPayload.GetStringHeader(Definitions.ACTION_STRING);
        AccountApplicationDAO appsDAO = AccountApplicationDAO.GetSingleton();
        Integer applicationID = requestPayload.GetNumericIntegerHeader(Definitions.ACCOUNT_APPLICATION_ID).intValue();
        AccountApplication selectedApplication = appsDAO.Read(applicationID);

        selectedApplication = appsDAO.Read(applicationID);

        if (selectedApplication != null && selectedApplication.getApplicationId().equals(applicationID)
                && selectedApplication.getStartingAmount() > 0 && accountAction != null) {

            if (accountAction.equals(Definitions.APPROVE_ACCOUNT)) {
                resp = CreateAccount(requestPayload, selectedApplication);
            } else {
                resp = RejectApp(requestPayload, selectedApplication);
            }
            appsDAO.DeleteById(applicationID);
        } else {
            resp = new ResponseProtocol(false, "There was a problem with the application. Please try again later.");
        }
        // TODO Auto-generated method stub
        return resp;
    }

}
