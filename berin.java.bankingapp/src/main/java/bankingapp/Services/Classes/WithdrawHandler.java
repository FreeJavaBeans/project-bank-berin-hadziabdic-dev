package bankingapp.Services.Classes;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bankingapp.ApplicationLookUpTable.Definitions;
import bankingapp.Model.Classes.BankAccount;
import bankingapp.Model.Classes.BankAccountDAO;
import bankingapp.Protocol.Classes.RequestProtocol;
import bankingapp.Protocol.Classes.ResponseProtocol;
import bankingapp.Services.Interfaces.HandlerService;

public class WithdrawHandler implements HandlerService {

    private static Logger logger = LogManager.getLogger("applogger");

    @Override
    public ResponseProtocol Handler(RequestProtocol requestPayload) throws Exception {
        // TODO Auto-generated method stub

        ResponseProtocol resp = null;
        BankAccountDAO bankDao = BankAccountDAO.GetSingleton();
        BankAccount bankAcct = null;
        Double withdrawalAmount = requestPayload.GetNumericHeader(Definitions.ACTION_AMOUNT);

        if (withdrawalAmount == null) {
            resp = new ResponseProtocol(false, "The withdrawal header is empty.");
        } else {
            bankAcct = bankDao.Read(requestPayload.GetNumericIntegerHeader(Definitions.ACCOUNT_NUMBER));

            if (bankAcct != null && bankAcct.ValidWithdrawal(withdrawalAmount)) {
                bankAcct.Withdraw(withdrawalAmount);
                resp = new ResponseProtocol(true,
                        "Withdrawal made. Your name balance is: " + bankAcct.getBalance() + ".");
                bankDao.Update(bankAcct);
                logger.info("Withdrawal made to account " + bankAcct.getAccountId() + " made " + " for a total of "
                        + withdrawalAmount.toString());

            } else {
                resp = new ResponseProtocol(false,
                        "The withdrawal could not be made. Please check your available balance, and also check that the requested withdrawal is a positive number.");
            }
        }

        return resp;
    }

}
