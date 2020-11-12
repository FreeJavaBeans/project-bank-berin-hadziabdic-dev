package bankingapp.Views.ViewClasses.CustomerViews;

import java.util.Map;

import bankingapp.API;
import bankingapp.ApplicationLookUpTable.Definitions;
import bankingapp.Views.AbstractViewClasses.AccountViewBase;

public class CustomerMenu extends AccountViewBase {

    public CustomerMenu(Map<String, Integer> integerData, Map<String, Double> doubleData,
            Map<String, String> userdata) {
        super(integerData, doubleData, userdata);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void RenderView() throws Exception {
        // TODO Auto-generated method stub
        InitView();
        ParseInteger("Enter the integer option you'd like to navigate to and press enter.",
                "Bad  input detected. Please eneter a valid choice to proceed", Definitions.VIEW_OPTION);
    }

    @Override
    public String GetNext() {
        String retValue = null;

        switch (this.integerData.get(Definitions.VIEW_OPTION)) {

            case 1:
                retValue = API.DEPOSIT;
                break;
            case 2:
                retValue = API.WITHDRAW;
                break;
            case 3:
                retValue = API.POST_TRANSFER;
                break;
            case 4:
                retValue = API.ACCEPT_TRANSFER;
                break;
            case 5:
                retValue = API.VIEW_CUSTOMERS_ACCOUNT_LISTING;
                break;
            case 6:
                retValue = API.APPLY_FOR_ACCOUNT;
                break;
            case 10:
                System.out.println("You selected to exit the application. Bye..");
                System.exit(0);
                break;

            default:
                System.out.println("Your choice was not recognized. Please try again.");
                break;
        }
        return retValue;
    }

    @Override
    protected void InitView() {
        // TODO Auto-generated method stub
        System.out.println("Customer Menu");
        System.out.println("1 Deposit cache.");
        System.out.println("2 Withdraw cache.");
        System.out.println("3 Post a money transfer.");
        System.out.println("4 Accept a money transfer.");
        System.out.println("5 View account balances.");
        System.out.println("6 Apply for an account.");
        System.out.println("10 Exit the application.");

    }

}
