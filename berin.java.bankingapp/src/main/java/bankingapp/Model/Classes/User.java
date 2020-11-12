package bankingapp.Model.Classes;

import bankingapp.UtilityClasses.AppUtility;
import bankingapp.Validation.SelfValidator;

public class User implements SelfValidator {

    private String username;
    private String password;
    private String accountType;

    public User() {
        this.username = null;
        this.password = null;
        this.accountType = null;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccountType() {
        return this.accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    @Override
    public boolean SelfValidate() throws Exception {
        // TODO Auto-generated method stub

        boolean validated = false;
        if (AppUtility.CheckNotNullOrEmptyString(this.username) && AppUtility.CheckNotNullOrEmptyString(this.password))
            validated = true;
        else
            throw new Exception("Selfvalidate in DTO " + this.getClass().toString() + "failed.");

        return validated;
    }

}
