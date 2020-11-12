package bankingapp.Views.ViewClasses.CustomerViews;

import java.util.Map;
import bankingapp.API;
import bankingapp.ApplicationLookUpTable.Definitions;
import bankingapp.Protocol.Classes.RequestProtocol;
import bankingapp.Protocol.Classes.ResponseProtocol;
import bankingapp.UtilityClasses.AppUtility;
import bankingapp.Views.AbstractViewClasses.AccountViewBase;
import bankingapp.Views.ViewInterfaces.SendDynamicViewData;

//Deposit view is a user + account based view that supports rendering, initializing, and sending dynamic data.
public class DepositView extends AccountViewBase implements SendDynamicViewData {

    public DepositView(Map<String, Integer> integerData, Map<String, Double> doubleData, Map<String, String> userdata) {
        super(integerData, doubleData, userdata);
        // TODO Auto-generated constructor stub
    }

    @Override
    public ResponseProtocol SendDynamicData() {
        // TODO Auto-generated method stubfiled
        RequestProtocol request = new RequestProtocol(API.DEPOSIT);
        ResponseProtocol resp = null;
        request.SetNumericHeader(Definitions.ACCOUNT_NUMBER, this.integerData.get(Definitions.ACCOUNT_NUMBER));
        request.SetStringHeader(Definitions.USERNAME, this.stringState.get(Definitions.USERNAME));
        request.SetNumericHeader(Definitions.ACTION_AMOUNT, this.doubleData.get(Definitions.ACTION_AMOUNT));
        resp = request.SendMessage();

        return resp;
    }

    @Override
    public void RenderView() throws Exception {
        System.out.println("Hello, " + this.stringState.get(Definitions.USERNAME) + ". Welcome to the deposit view.");
        InitView();

        if (this.renderDynamicPrompt) {
            this.ParseInteger("Enter the account number you'd like to deposit to and press enter: ",
                    "Invalid account number entered. " + Definitions.INVALID_INTEGER, Definitions.ACCOUNT_NUMBER);

            this.ParseDouble("Enter how much money you'd like to deposit to and press enter: ",
                    "Invalid money amount entered. " + Definitions.INVALID_INTEGER, Definitions.ACTION_AMOUNT);

            ResponseProtocol dynamicResp = this.SendDynamicData();
            this.ProcessDynamicViewData(dynamicResp);
        } else
            System.out.println(
                    "You have no accounts. You need to submit and have an account application approved before you can deposit. Thanks.\n");

    }

    @Override
    public boolean ProcessDynamicViewData(ResponseProtocol resp) {
        // TODO Auto-generated method stub

        AppUtility.PrintStringSet("Response ", resp.GetBody());
        return true;
    }
}