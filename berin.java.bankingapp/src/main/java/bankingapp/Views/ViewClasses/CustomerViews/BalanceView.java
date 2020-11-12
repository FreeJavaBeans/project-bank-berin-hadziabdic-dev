package bankingapp.Views.ViewClasses.CustomerViews;

import java.util.Map;
import java.util.Set;

import bankingapp.API;
import bankingapp.ApplicationLookUpTable.Definitions;
import bankingapp.Protocol.Classes.RequestProtocol;
import bankingapp.Protocol.Classes.ResponseProtocol;
import bankingapp.UtilityClasses.AppUtility;
import bankingapp.Views.AbstractViewClasses.AccountViewBase;
import bankingapp.Views.ViewInterfaces.SendDynamicViewData;

public class BalanceView extends AccountViewBase implements SendDynamicViewData {

    public BalanceView(Map<String, Integer> integerData, Map<String, Double> doubleData, Map<String, String> userdata) {
        super(integerData, doubleData, userdata);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void InitView() {
        // TODO Auto-generated method stub
    }

    @Override
    public void RenderView() throws Exception {
        this.InitView();

        RequestProtocol req = new RequestProtocol(API.VIEW_CUSTOMERS_ACCOUNT_LISTING);
        req.SetStringHeader(Definitions.USERNAME, this.stringState.get(Definitions.USERNAME));

        ResponseProtocol resp = req.SendMessage();
        Set<String> responseBody = resp.GetBody();

        if (responseBody.size() == 0) {
            System.out.println("The user has no accounts with the bank, currently. Please try again later.");
        } else {

            AppUtility.PrintStringSet("Account list owned by user", resp.GetBody());

            ParseInteger("Enter the account number you'd like to view the balance for and press enter: ",
                    "You entered a bad account number." + Definitions.INVALID_INTEGER, Definitions.ACCOUNT_NUMBER);

            ResponseProtocol respDynamic = this.SendDynamicData(); // fetch data
            ProcessDynamicViewData(respDynamic); // process dynamic response
        }

    }

    @Override
    public ResponseProtocol SendDynamicData() {
        // TODO Auto-generated method stub

        RequestProtocol dynamicReq = new RequestProtocol(API.VIEW_ACCOUNT_BALANCE);
        dynamicReq.SetStringHeader(Definitions.USERNAME, this.stringState.get(Definitions.USERNAME));
        dynamicReq.SetNumericHeader(Definitions.ACCOUNT_NUMBER, this.integerData.get(Definitions.ACCOUNT_NUMBER));
        ResponseProtocol resp = dynamicReq.SendMessage();

        return resp;
    }

    @Override
    public boolean ProcessDynamicViewData(ResponseProtocol resp) {
        // TODO Auto-generated method stub

        if (resp.GetResponseCode() && resp.GetBody().size() > 0) {
            AppUtility.PrintStringSet("Requested Account Balance", resp.GetBody());

        } else {
            System.out.println("No corresponding account information could be found. Please try again");
        }
        return resp.GetResponseCode();

    }

}
