package bankingapp.Views.ViewInterfaces;

import bankingapp.Protocol.Classes.ResponseProtocol;

//this interface allows the implementing view 
// to send data to the back end after initializing 
//since some views need to send data to the back end after initilization.
//is abstract to enable protected modifier. ouitside need not send data.
public interface SendDynamicViewData {

    public ResponseProtocol SendDynamicData();

    public boolean ProcessDynamicViewData(ResponseProtocol resp);

}
