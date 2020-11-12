package bankingapp.Services.Interfaces;

import bankingapp.Protocol.Classes.RequestProtocol;
import bankingapp.Protocol.Classes.ResponseProtocol;

public interface HandlerService {

    public ResponseProtocol Handler(RequestProtocol requestPayload) throws Exception;

}
