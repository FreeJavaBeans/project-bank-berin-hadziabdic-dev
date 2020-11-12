package bankingapp.Views.ViewClasses.CustomerViews;

import java.util.Map;

import bankingapp.API;
import bankingapp.ApplicationLookUpTable.Definitions;
import bankingapp.Protocol.Classes.RequestProtocol;
import bankingapp.Protocol.Classes.ResponseProtocol;
import bankingapp.UtilityClasses.AppUtility;
import bankingapp.Views.AbstractViewClasses.AccountViewBase;
import bankingapp.Views.ViewInterfaces.SendDynamicViewData;

public class WithdrawView extends AccountViewBase implements SendDynamicViewData {

    public WithdrawView(Map<String, Integer> integerData, Map<String, Double> doubleData,
            Map<String, String> userdata) {
        super(integerData, doubleData, userdata);
        // TODO Auto-generated constructor stub
    }

    @Override
    public ResponseProtocol SendDynamicData() {
        // TODO Auto-generated method stub
        ResponseProtocol resp = null;
        RequestProtocol request = new RequestProtocol(API.WITHDRAW);
        request.SetNumericHeader(Definitions.ACTION_AMOUNT, this.doubleData.get(Definitions.ACTION_AMOUNT));
        request.SetNumericHeader(Definitions.ACCOUNT_NUMBER, this.integerData.get(Definitions.ACCOUNT_NUMBER));
        request.SetStringHeader(Definitions.USERNAME, this.stringState.get(Definitions.USERNAME));

        resp = request.SendMessage();

        return resp;
    }

    @Override
    public void RenderView() throws Exception {

        ResponseProtocol dynamicResponse = null;

        System.out.println("Hello, " + this.stringState.get(Definitions.USERNAME) + ". Welcome to the withdraw view.");
        this.InitView();

        if (this.renderDynamicPrompt) {

            this.ParseInteger("Enter the account number you'd like to withdraw from and press enter: ",
                    "Invalid account number entered. " + Definitions.INVALID_INTEGER, Definitions.ACCOUNT_NUMBER);

            this.ParseDouble("Enter how much money you'd like to withdraw and press enter: ",
                    "Invalid money amount entered. " + Definitions.INVALID_INTEGER, Definitions.ACTION_AMOUNT);

            dynamicResponse = this.SendDynamicData();
            this.ProcessDynamicViewData(dynamicResponse);
        } else {
            System.out.println(
                    "You have no accounts registered with us. Please register for an account, deposit some cache, and then make a withdrawal.");
        }

    }

    @Override
    public boolean ProcessDynamicViewData(ResponseProtocol resp) {

        if (resp != null) {
            if (resp.GetResponseCode()) {
                AppUtility.PrintStringSet("Server Response", resp.GetBody());
            } else {
                System.out.println(
                        "Unable to complete withdrawal. Bad response recieved from server please try again at a later time.");
            }
        }
        // TODO Auto-generated method stub
        return resp.GetResponseCode();
    }

}