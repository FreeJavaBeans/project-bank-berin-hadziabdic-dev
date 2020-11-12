package bankingapp.Views.ViewClasses.EmployeeViews;

import java.util.Map;

import bankingapp.API;
import bankingapp.ApplicationLookUpTable.Definitions;
import bankingapp.Views.AbstractViewClasses.AccountViewBase;

public class EmployeeMenuView extends AccountViewBase {

    public EmployeeMenuView(Map<String, Integer> integerData, Map<String, Double> doubleData,
            Map<String, String> userdata) {
        super(integerData, doubleData, userdata);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void RenderView() throws Exception {
        // TODO Auto-generated method stub
        InitView();
        this.ParseInteger("Type in the action you'd like to take and press enter..",
                "Invalid option entered, Enter a number between 1 and 4.", Definitions.VIEW_OPTION);

    }

    @Override
    public void InitView() {
        System.out.println("Hello," + this.stringState.get(Definitions.USERNAME) + " .");
        System.out.println("Please choose from one of the options below...");
        System.out.println("1) Approve or Reject a pending account application.");
        System.out.println("2) Print all transactions.");
        System.out.println("3) View all customer accounts.");
        System.out.println("4) Exit the application.");
    }

    @Override
    public String GetNext() {
        String retValue = null;

        switch (this.integerData.get(Definitions.VIEW_OPTION)) {

            case 1:
                retValue = API.APPROVE_ACCOUNT_APP;
                break;
            case 2:
                retValue = API.EMPLOYEE_VIEW_TRANSACTIONS;
                break;
            case 3:
                retValue = API.VIEW_ACCOUNTS_AND_BALANCES;
                break;
            case 4:
                System.out.println("Exiting application. Bye...");
                System.exit(0);
                break;

            default:
                System.out.println("Your choice was not recognized. Please try again.");
                break;
        }
        return retValue;
    }

}
