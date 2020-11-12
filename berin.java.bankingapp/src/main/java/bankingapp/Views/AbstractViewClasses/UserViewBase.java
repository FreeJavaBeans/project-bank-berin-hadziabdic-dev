package bankingapp.Views.AbstractViewClasses;

import java.util.Map;
import bankingapp.UtilityClasses.AppUtility;
import bankingapp.Views.ViewInterfaces.ViewStateSetter;

//this class supplies the shared implementation for 
//user specific logic common across all user related views.
public abstract class UserViewBase extends ViewBase implements ViewStateSetter {

    protected Map<String, String> stringState;

    public UserViewBase(Map<String, String> stringstate) {

        this.stringState = stringstate;
    }

    @Override
    public void SetStringState(String fieldName, String fieldValue) throws Exception {

        // Throw exceptions in the case of invalid field name or field value which
        // indicate programmer error
        // and thus an exceptional situation.

        if (!AppUtility.CheckNotNullOrEmptyString(fieldName))
            throw new Exception("Empty field name passed to SetString state in class " + this.getClass().toString());
        if (!AppUtility.CheckNotNullOrEmptyString(fieldValue))
            throw new Exception("Empty field value passed to SetString state in class " + this.getClass().toString());

        this.stringState.put(fieldName, fieldValue);

    }

    protected void ParseString(String promptEnterMsg, String errMsg, String fieldName) throws Exception {

        boolean continueLoop = true;
        String msgPrompt = "Enter your String input and press enter";
        String errPrompt = "You entered an empty string. Please enter a non empty string to proceed.";
        String buffer = null;

        if (promptEnterMsg != null)
            msgPrompt = promptEnterMsg;
        if (errMsg != null)
            errPrompt = errMsg;

        while (continueLoop) {
            System.out.println(msgPrompt);
            buffer = this.reader.nextLine();

            if (AppUtility.CheckNotNullOrEmptyString(buffer)) {
                continueLoop = false;
            } else {
                System.out.println(errPrompt);
            }
        }

        this.SetStringState(fieldName, buffer);

    }

}
