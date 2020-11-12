package bankingapp.Views.ViewClasses.GeneralViews;

import java.util.Map;

import bankingapp.API;
import bankingapp.ApplicationLookUpTable.Definitions;
import bankingapp.Protocol.Classes.RequestProtocol;
import bankingapp.Protocol.Classes.ResponseProtocol;
import bankingapp.Views.AbstractViewClasses.AccountViewBase;
import bankingapp.Views.ViewInterfaces.SendDynamicViewData;

public class LoginView extends AccountViewBase implements SendDynamicViewData {

    public LoginView(Map<String, Integer> integerData, Map<String, Double> doubleData, Map<String, String> userdata) {
        super(integerData, doubleData, userdata);
        // TODO Auto-generated constructor stub

    }

    @Override
    public void RenderView() throws Exception {
        // TODO Auto-generated method stub
        this.ParseString("Enter the username you'd like to log in with and press enter: ",
                "Warning. Bad input disocered. Please enter a non empty string.", Definitions.USERNAME);
        this.ParseString("Enter your password and press enter: ",
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
        request = new RequestProtocol(API.LOGIN);
        request.SetStringHeader(Definitions.USERNAME, this.stringState.get(Definitions.USERNAME));
        request.SetStringHeader(Definitions.PASSWORD, this.stringState.get(Definitions.PASSWORD));

        resp = request.SendMessage();

        return resp;
    }

    @Override
    public boolean ProcessDynamicViewData(ResponseProtocol resp) {
        // TODO Auto-generated method stub

        boolean respSuccess = resp.GetResponseCode();
        String accountType = resp.GetStringHeader(Definitions.ACCOUNT_TYPE);

        if (respSuccess) {
            System.out.println("User logged in. Attempting redirect to the appropriate menu tree ....");
            switch (accountType) {
                case Definitions.CUSTOMER_ACCOUNT:
                    this.SetNext(API.CUSTOMER_MENU);
                    break;
                case Definitions.EMPLOYEE_ACCOUNT:
                    this.SetNext(API.EMPLOYEE_MENU);
                    break;
                default:
                    System.out.println("App error detected. Please contact developer.Redirecting to root view.");
                    this.SetNext(API.ROOTVIEW);
                    break;
            }

        } else {
            System.out.println("Your login failed please try again.");
            this.SetNext(API.ROOTVIEW);
        }
        return respSuccess;
    }

}
