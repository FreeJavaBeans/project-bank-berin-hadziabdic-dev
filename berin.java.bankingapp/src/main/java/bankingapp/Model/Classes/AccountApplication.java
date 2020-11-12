package bankingapp.Model.Classes;

import bankingapp.UtilityClasses.AppUtility;
import bankingapp.Validation.SelfValidator;

public class AccountApplication implements SelfValidator {

    private Integer applicationId;
    private String username;
    private Double startingAmount;

    public AccountApplication() {
        this.applicationId = null;
        this.username = null;
        this.startingAmount = null;
    }

    public Integer getApplicationId() {
        return this.applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Double getStartingAmount() {
        return this.startingAmount;
    }

    public void setStartingAmount(Double startingAmount) {
        this.startingAmount = startingAmount;
    }

    @Override
    public boolean SelfValidate() throws Exception {
        // TODO Auto-generated method stub
        return (AppUtility.CheckValidNumber(startingAmount) && AppUtility.CheckNotNullOrEmptyString(this.username));
    }

}
