package bankingapp.Views.AbstractViewClasses;

import java.util.Map;
import java.util.Set;

import bankingapp.API;
import bankingapp.ApplicationLookUpTable.Definitions;
import bankingapp.Protocol.Classes.RequestProtocol;
import bankingapp.Protocol.Classes.ResponseProtocol;
import bankingapp.UtilityClasses.AppUtility;

//this class extends viewbase and userviewbase, adding shared account managment functionality.
//We can use it model account balances and perform balance transfers.

public abstract class AccountViewBase extends UserViewBase {

    protected Map<String, Integer> integerData;
    protected Map<String, Double> doubleData;
    protected Boolean renderDynamicPrompt = false;

    public AccountViewBase(Map<String, Integer> integerData, Map<String, Double> doubleData,
            Map<String, String> userdata) {
        super(userdata);
        this.integerData = integerData;
        this.doubleData = doubleData;

    }

    /**
     * @param fieldName  integer field to set
     * @param fieldValue value to set to
     * @throws Exception exception thrown when field name or field value is invalid
     */
    @Override
    public void SetIntegerState(String fieldName, Integer fieldValue) throws Exception {

        // Throw exceptions in the case of invalid field name or field value which
        // indicate programmer error
        // and thus an exceptional situation.

        if (!AppUtility.CheckNotNullOrEmptyString(fieldName))
            throw new Exception("Empty field name passed to SetIntegerState in class " + this.getClass().toString());
        if (!AppUtility.CheckValidNumber(fieldValue))
            throw new Exception(
                    "Empty  or invalid field value passed to SetIntegerState in class " + this.getClass().toString());

        this.integerData.put(fieldName, fieldValue);

    }

    protected void ParseInteger(String promptEnterMsg, String errMsg, String fieldName) throws Exception {
        boolean continueLoop = true;
        String msgPrompt = "Enter your String input and press enter";
        String errPrompt = "You entered an empty string. Please enter a non empty string to proceed.";
        String stringBuffer = null;
        Integer integerBuffer = null;

        if (promptEnterMsg != null)
            msgPrompt = promptEnterMsg;
        if (errMsg != null)
            errPrompt = errMsg;

        while (continueLoop) {
            System.out.println(msgPrompt);
            stringBuffer = this.reader.nextLine();

            if (AppUtility.CheckValidInteger(stringBuffer)) {
                continueLoop = false;
                integerBuffer = Integer.parseInt(stringBuffer);
            } else {
                System.out.println(errPrompt);
            }
        }

        this.SetIntegerState(fieldName, integerBuffer);
    }

    protected void ParseDouble(String promptEnterMsg, String errMsg, String fieldName) throws Exception {
        boolean continueLoop = true;
        String msgPrompt = "Enter your String input and press enter";
        String errPrompt = "You entered an empty string. Please enter a non empty string to proceed.";
        String stringBuffer = null;
        Double doubleBuffer = null;

        if (promptEnterMsg != null)
            msgPrompt = promptEnterMsg;
        if (errMsg != null)
            errPrompt = errMsg;

        while (continueLoop) {
            System.out.println(msgPrompt);
            stringBuffer = this.reader.nextLine();

            if (AppUtility.CheckValidDouble(stringBuffer)) {
                continueLoop = false;
                doubleBuffer = Double.parseDouble(stringBuffer);
            } else {
                System.out.println(errPrompt);
            }
        }

        this.SetDoubleState(fieldName, doubleBuffer);
    }

    @Override
    public void SetDoubleState(String fieldName, Double fieldValue) throws Exception {

        // Throw exceptions in the case of invalid field name or field value which
        // indicate programmer error
        // and thus an exceptional situation.

        if (!AppUtility.CheckNotNullOrEmptyString(fieldName))
            throw new Exception("Empty field name passed to SetIntegerState in class " + this.getClass().toString());
        if (!AppUtility.CheckValidNumber(fieldValue))
            throw new Exception(
                    "Empty  or invalid field value passed to SetIntegerState in class " + this.getClass().toString());

        this.doubleData.put(fieldName, fieldValue);

    }

    @Override
    protected void InitView() {
        // TODO Auto-generated method stub
        RequestProtocol req = new RequestProtocol(API.VIEW_ACCOUNTS_AND_BALANCES);
        req.SetStringHeader(Definitions.USERNAME, this.stringState.get(Definitions.USERNAME));

        ResponseProtocol resp = req.SendMessage();
        Set<String> responseBody = resp.GetBody();
        AppUtility.PrintStringSet("Your Accounts and Balances", responseBody);

        if (!responseBody.contains("We found no registered accounts."))
            renderDynamicPrompt = true;

    }

}
