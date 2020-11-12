package bankingapp.Views.ViewClasses.CustomerViews;

import java.util.Map;

import bankingapp.API;
import bankingapp.ApplicationLookUpTable.Definitions;
import bankingapp.Protocol.Classes.RequestProtocol;
import bankingapp.Protocol.Classes.ResponseProtocol;
import bankingapp.Views.AbstractViewClasses.AccountViewBase;
import bankingapp.Views.ViewInterfaces.SendDynamicViewData;

public class ApplyView extends AccountViewBase implements SendDynamicViewData {

    public ApplyView(Map<String, Integer> integerData, Map<String, Double> doubleData, Map<String, String> userdata) {
        super(integerData, doubleData, userdata);
        // TODO Auto-generated constructor stub
        this.next = API.CUSTOMER_MENU;
    }

    @Override
    public ResponseProtocol SendDynamicData() {
        // TODO Auto-generated method stub
        RequestProtocol request = new RequestProtocol(API.APPLY_FOR_ACCOUNT);
        request.SetNumericHeader(Definitions.ACCOUNT_BALANCE, this.doubleData.get(Definitions.ACCOUNT_BALANCE));
        request.SetStringHeader(Definitions.USERNAME, this.stringState.get(Definitions.USERNAME));
        // ResponseProtocol resp = this.controllerRef.InvokeRoute(request);
        ResponseProtocol resp = request.SendMessage();

        return resp;
    }

    @Override
    public void RenderView() throws Exception {
        // TODO Auto-generated method stub

        System.out.println("Welcome, " + this.stringState.get(Definitions.USERNAME)
                + ". We're glad to see you want to apply for an account.");
        this.ParseDouble("Enter the starting balance of your account  and press enter: ",
                "You entered an invalid starting balance." + Definitions.INVALID_INTEGER, Definitions.ACCOUNT_BALANCE);
        ResponseProtocol resp = this.SendDynamicData();
        ProcessDynamicViewData(resp);

    }

    @Override
    protected void InitView() {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean ProcessDynamicViewData(ResponseProtocol resp) {
        // TODO Auto-generated method stub

        if (resp.GetResponseCode())
            System.out.println("Application filed. A member of our staff will process your application. Thank you!");
        else
            System.out.println("Your application failed. Please enter a positive balance and try again later.");
        return false;
    }

}
