package bankingapp.Services.Classes;

import java.util.Set;

import bankingapp.ApplicationLookUpTable.Definitions;
import bankingapp.Model.Classes.PendingTransfersDAO;
import bankingapp.Protocol.Classes.RequestProtocol;
import bankingapp.Protocol.Classes.ResponseProtocol;
import bankingapp.Services.Interfaces.HandlerService;

public class ViewCustomersTransfersHandler implements HandlerService {

    @Override
    public ResponseProtocol Handler(RequestProtocol requestPayload) throws Exception {

        ResponseProtocol resp = null;
        String username = requestPayload.GetStringHeader(Definitions.USERNAME);
        String password = requestPayload.GetStringHeader(Definitions.PASSWORD);
        PendingTransfersDAO pendingTransferDAO = PendingTransfersDAO.GetSingleton();

        Set<String> pendingTransfers = pendingTransferDAO.ReadAllPendingTransfersForUser(username, password);

        if (pendingTransfers.size() == 0)
            pendingTransfers.add("We found no awaiting transfers.");
        // TODO Auto-generated method stub
        resp = new ResponseProtocol(true, pendingTransfers);

        return resp;
    }

}
