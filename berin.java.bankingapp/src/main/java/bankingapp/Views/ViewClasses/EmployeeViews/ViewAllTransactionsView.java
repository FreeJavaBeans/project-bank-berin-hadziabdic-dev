package bankingapp.Views.ViewClasses.EmployeeViews;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

import bankingapp.API;
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
        try {
            File myObj = new File("trace.log");
            Scanner myReader = new Scanner(myObj);
            if (myReader.hasNextLine())
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    System.out.println(data);
                }
            else
                System.out
                        .println("No transactions have taken as of yet. Please come back at a later time. Thank you.");
            myReader.close();
        } catch (FileNotFoundException e) {

        }
    }

    @Override
    public void RenderView() throws Exception {
        // TODO Auto-generated method stub
        System.out.println(
                "Hello, " + this.stringState.get(Definitions.USERNAME) + ". Welcome to the All Transactions view.");
        System.out.println("We are loading all transactions that have taken place at our bank . . .");
        this.InitView();

    }

    @Override

    public String GetNext() {
        return API.EMPLOYEE_MENU;
    }

}
