package bankingapp.Services.Classes;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bankingapp.ApplicationLookUpTable.Definitions;
import bankingapp.Model.Classes.BankAccount;
import bankingapp.Model.Classes.BankAccountDAO;
import bankingapp.Protocol.Classes.RequestProtocol;
import bankingapp.Protocol.Classes.ResponseProtocol;
import bankingapp.Services.Interfaces.HandlerService;

public class DepositHandler implements HandlerService {

    private static Logger logger = LogManager.getLogger("applogger");

    @Override
    public ResponseProtocol Handler(RequestProtocol requestPayload) throws Exception {
        // TODO Auto-generated method stub

        ResponseProtocol response = null;
        boolean success = false;
        try {
            BankAccountDAO bankDao = BankAccountDAO.GetSingleton();
            BankAccount bankAcct = bankDao.Read(requestPayload.GetNumericIntegerHeader(Definitions.ACCOUNT_NUMBER));
            Double depositAmount = requestPayload.GetNumericHeader(Definitions.ACTION_AMOUNT);

            if (depositAmount != null && bankAcct.ValidDeposit(depositAmount)) {
                bankAcct.Deposit(depositAmount);
                success = bankDao.Update(bankAcct);

                if (success) {
                    response = new ResponseProtocol(true,
                            "Deposit successful. New balance: " + bankAcct.getBalance().toString());
                    logger.info("Deposit to account " + bankAcct.getAccountId() + " made " + " for a total of "
                            + depositAmount.toString());
                } else {
                    response = new ResponseProtocol(false, "Deposit failed. Please try again later.");
                }
            } else
                throw new Exception("Missing action header. Cannot proceed with request.");

        } catch (Exception e) {
            response = new ResponseProtocol(false, "We couldn't process your deposit. Please try again later.");
        }

        return response;
    }

}
