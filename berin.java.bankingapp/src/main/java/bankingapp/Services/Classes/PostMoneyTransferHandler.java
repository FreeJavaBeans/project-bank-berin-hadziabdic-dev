package bankingapp.Services.Classes;

import bankingapp.ApplicationLookUpTable.Definitions;
import bankingapp.Model.Classes.BankAccount;
import bankingapp.Model.Classes.BankAccountDAO;
import bankingapp.Model.Classes.PendingTransfers;
import bankingapp.Model.Classes.PendingTransfersDAO;
import bankingapp.Protocol.Classes.RequestProtocol;
import bankingapp.Protocol.Classes.ResponseProtocol;
import bankingapp.Services.Interfaces.HandlerService;

public class PostMoneyTransferHandler implements HandlerService {

    @Override
    public ResponseProtocol Handler(RequestProtocol requestPayload) throws Exception {
        // TODO Auto-generated method stub
        ResponseProtocol resp = null;
        Integer fromAccount = requestPayload.GetNumericIntegerHeader(Definitions.FROM_ACCOUNT);
        Integer toAccount = requestPayload.GetNumericIntegerHeader(Definitions.TO_ACCOUNT);
        Double amount = requestPayload.GetNumericHeader(Definitions.ACTION_AMOUNT);

        BankAccountDAO bankAcctDao = BankAccountDAO.GetSingleton();
        BankAccount fromAcct = bankAcctDao.Read(fromAccount); // need ids
        BankAccount toAcct = bankAcctDao.Read(toAccount);

        PendingTransfersDAO pendingTransfersDAO = PendingTransfersDAO.GetSingleton();
        PendingTransfers pendingTransfer = new PendingTransfers();

        if (fromAcct.ValidWithdrawal(amount) && toAcct != null && toAcct.ValidDeposit(amount)) {
            fromAcct.Withdraw(amount);

            pendingTransfer.setAmount(amount);
            pendingTransfer.setToAccount(toAccount);
            pendingTransfer.setFromAccount(fromAccount);

            bankAcctDao.Update(fromAcct);
            pendingTransfersDAO.Create(pendingTransfer);

            resp = new ResponseProtocol(true,
                    "Your transfer was successful, but is pending and needs approval. The funds have been deducted and your new balance is: "
                            + fromAcct.getBalance());
        } else {
            resp = new ResponseProtocol(false,
                    "Your transfer was not approved. Please make sure your transferring positive amounts of money and try again later. Thank you.");
        }

        return resp;
    }

}
