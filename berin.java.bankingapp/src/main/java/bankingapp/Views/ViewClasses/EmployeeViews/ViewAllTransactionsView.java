package bankingapp.Views.ViewClasses.EmployeeViews;

import java.util.Map;

import bankingapp.ApplicationLookUpTable.Definitions;
import bankingapp.Views.AbstractViewClasses.AccountViewBase;

public class ViewAllTransactionsView extends AccountViewBase {

    public ViewAllTransactionsView(Map<String, Integer> integerData, Map<String, Double> doubleData,
            Map<String, String> userdata) {
        super(integerData, doubleData, userdata);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void InitView() {
        // TODO Auto-generated method stub

    }

    @Override
    public void RenderView() throws Exception {
        // TODO Auto-generated method stub
        System.out.println(
                "Hello, " + this.stringState.get(Definitions.USERNAME) + ". Welcome to the All Transactions view.");
        System.out.println("We are loading all transactions that have taken place at our bank . . .");
        this.InitView();

    }

}
