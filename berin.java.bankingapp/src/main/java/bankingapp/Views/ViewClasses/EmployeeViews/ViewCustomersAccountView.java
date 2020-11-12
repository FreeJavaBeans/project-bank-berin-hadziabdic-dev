package bankingapp.Views.ViewClasses.EmployeeViews;

import java.util.Map;
import bankingapp.API;
import bankingapp.ApplicationLookUpTable.Definitions;
import bankingapp.Protocol.Classes.RequestProtocol;
import bankingapp.Protocol.Classes.ResponseProtocol;
import bankingapp.UtilityClasses.AppUtility;
import bankingapp.Views.AbstractViewClasses.AccountViewBase;
import bankingapp.Views.ViewInterfaces.SendDynamicViewData;

public class ViewCustomersAccountView extends AccountViewBase implements SendDynamicViewData {

    public ViewCustomersAccountView(Map<String, Integer> integerData, Map<String, Double> doubleData,
            Map<String, String> userdata) {
        super(integerData, doubleData, userdata);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void RenderView() throws Exception {
        // TODO Auto-generated method stub
        ResponseProtocol dynamicResponse = null;
        InitView();
        this.ParseString("Enter the customer who's accounts you'd like to view and press enter.",
                "Invalid username entered. Please enter a non empty username.", Definitions.ACTION_STRING);
        dynamicResponse = this.SendDynamicData();
        this.ProcessDynamicViewData(dynamicResponse);

    }

    @Override
    protected void InitView() {
        // TODO Auto-generated method stub
        System.out.println("Hello, " + this.stringState.get(Definitions.USERNAME) + "...");
        System.out.println("Welcome to the employee only view customers account balances view.");

    }

    @Override
    public String GetNext() {
        return API.EMPLOYEE_MENU;
    }

    @Override
    public ResponseProtocol SendDynamicData() {
        // TODO Auto-generated method stub
        ResponseProtocol resp = null;
        RequestProtocol request = new RequestProtocol(API.VIEW_ACCOUNTS_AND_BALANCES);
        request.SetStringHeader(Definitions.USERNAME, this.stringState.get(Definitions.ACTION_STRING));

        resp = request.SendMessage();

        return resp;
    }

    @Override
    public boolean ProcessDynamicViewData(ResponseProtocol resp) {
        // TODO Auto-generated method stub
        if (resp.GetResponseCode()) {
            if (resp.GetBody() != null && resp.GetBody().size() > 0)
                AppUtility.PrintStringSet("Customer " + this.stringState.get(Definitions.ACTION_STRING),
                        resp.GetBody());
            else
                System.out.println("No accounts discovered for customer" + this.stringState.get(Definitions.USERNAME));
        } else
            System.out.println(
                    "It looks like the customer doesn't have any accounts, or you may have entered a non existing username.\n");

        return resp.GetResponseCode();
    }

}
