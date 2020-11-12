package bankingapp.Services.Classes;

import bankingapp.ApplicationLookUpTable.Definitions;
import bankingapp.Model.Classes.BankAccount;
import bankingapp.Model.Classes.BankAccountDAO;
import bankingapp.Model.Classes.PendingTransfers;
import bankingapp.Model.Classes.PendingTransfersDAO;
import bankingapp.Protocol.Classes.RequestProtocol;
import bankingapp.Protocol.Classes.ResponseProtocol;
import bankingapp.Services.Interfaces.HandlerService;

//READ_TRANSFER_BY_ID
public class ApproveTransferHandler implements HandlerService {

    @Override
    public ResponseProtocol Handler(RequestProtocol requestPayload) throws Exception {
        // TODO Auto-generated method stub

        ResponseProtocol resp = null;

        Integer transferId = requestPayload.GetNumericIntegerHeader(Definitions.TRANSFER_ID);

        String username = requestPayload.GetStringHeader(Definitions.USERNAME);
        String password = requestPayload.GetStringHeader(Definitions.PASSWORD);

        PendingTransfersDAO requestedTransfer = PendingTransfersDAO.GetSingleton();
        PendingTransfers pendingTransfer = requestedTransfer.ReadTransferIdOwnedByUser(transferId, username, password);

        if (pendingTransfer != null && pendingTransfer.getFromAccount() != null
                && pendingTransfer.getToAccount() != null && pendingTransfer.getAmount() != null) {

            resp = CompleteTransfer(pendingTransfer, username);
        } else {
            resp = new ResponseProtocol(false,
                    "The requested transfer could not be found. Please make sure the transfer id is correct, and that you are the recipient of said transfer.");
        }

        return resp;
    }

    public ResponseProtocol CompleteTransfer(PendingTransfers pendingtransfer, String username) throws Exception {

        ResponseProtocol response = null;
        BankAccountDAO bankAcctDao = BankAccountDAO.GetSingleton();
        BankAccount transferBankAccount = null;
        PendingTransfersDAO pendingTransferDao = PendingTransfersDAO.GetSingleton();

        // these two checks ensure that bankaccount belongs to username and username is
        // a valid match for toaccount
        // if either fail, then reject the transfer.
        boolean bankAccountCheck = false;
        boolean pendingTransferCheck = pendingtransfer != null && pendingtransfer.getToAccount() != null;

        // ensure transfer is valid, then read bank acccount
        if (pendingTransferCheck)
            transferBankAccount = bankAcctDao.Read(pendingtransfer.getToAccount());

        // ensure bank account matches transfer
        bankAccountCheck = transferBankAccount != null && transferBankAccount.getUsername() != null
                && transferBankAccount.getUsername().equals(username)
                && transferBankAccount.ValidDeposit(pendingtransfer.getAmount());

        if (pendingTransferCheck && bankAccountCheck) {
            transferBankAccount.Deposit(pendingtransfer.getAmount());

            bankAcctDao.Update(transferBankAccount);
            pendingTransferDao.Delete(pendingtransfer.getTransferId());
            response = new ResponseProtocol(true, "The transfer complete succefully.");
        } else {

            response = new ResponseProtocol(false,
                    "The transfer did not complete succesfully. Please ensure you are authorized to make the transfer and try again.");

        }

        return response;

    }

}
