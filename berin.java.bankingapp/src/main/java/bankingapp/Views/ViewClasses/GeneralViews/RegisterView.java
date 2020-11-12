package bankingapp.Views.ViewClasses.GeneralViews;

import java.util.Map;

import bankingapp.API;
import bankingapp.ApplicationLookUpTable.Definitions;
import bankingapp.Protocol.Classes.RequestProtocol;
import bankingapp.Protocol.Classes.ResponseProtocol;
import bankingapp.Views.AbstractViewClasses.AccountViewBase;
import bankingapp.Views.ViewInterfaces.SendDynamicViewData;

public class RegisterView extends AccountViewBase implements SendDynamicViewData {

    public RegisterView(Map<String, Integer> integerData, Map<String, Double> doubleData,
            Map<String, String> userdata) {
        super(integerData, doubleData, userdata);
        // TODO Auto-generated constructor stub

    }

    @Override
    public void RenderView() throws Exception {
        // TODO Auto-generated method stub
        this.ParseString("Enter the username you'd like to register with and press enter: ",
                "Warning. Bad input disocered. Please enter a non empty string.", Definitions.USERNAME);
        this.ParseString("Enter the password you'd like to register with  and press enter: ",
                "Warning. Bad input disovered. Please enter a non empty string.", Definitions.PASSWORD);
        ResponseProtocol resp = this.SendDynamicData();
        this.ProcessDynamicViewData(resp);

    }

    @Override
    protected void InitView() {
        System.out.println("Welcome to the login screen ...");

    }

    @Override
    public ResponseProtocol SendDynamicData() {
        // TODO Auto-generated method stub

        RequestProtocol request = null;
        ResponseProtocol resp = null;
        request = new RequestProtocol(API.REGISTER);
        request.SetStringHeader(Definitions.USERNAME, this.stringState.get(Definitions.USERNAME));
        request.SetStringHeader(Definitions.PASSWORD, this.stringState.get(Definitions.PASSWORD));

        resp = request.SendMessage();

        return resp;

    }

    @Override
    public boolean ProcessDynamicViewData(ResponseProtocol resp) {
        // TODO Auto-generated method stub

        boolean respSuccess = resp.GetResponseCode();
        if (respSuccess) {
            System.out.println("User created. Redirecting to menu ....");
            this.SetNext(API.CUSTOMER_MENU);
        } else {
            System.out.println("Your reigstration failed. Perhaps the username is taken. Please try again.");
            this.SetNext(API.ROOTVIEW);
        }

        return respSuccess;
    }

}
