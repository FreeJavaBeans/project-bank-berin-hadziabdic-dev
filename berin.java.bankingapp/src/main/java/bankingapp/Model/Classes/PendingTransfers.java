package bankingapp.Model.Classes;

import bankingapp.UtilityClasses.AppUtility;
import bankingapp.Validation.SelfValidator;

public class PendingTransfers implements SelfValidator {
    private Integer fromAccount;
    private Integer toAccount;
    private Double amount;
    private Integer transferId;

    public Integer getTransferId() {
        return this.transferId;
    }

    public void setTransferId(Integer transferId) {
        this.transferId = transferId;
    }

    public PendingTransfers() {
        this.fromAccount = null;
        this.toAccount = null;
        this.amount = null;
        this.transferId = null;
    }

    public Integer getFromAccount() {
        return this.fromAccount;
    }

    public void setFromAccount(Integer fromAccount) {
        this.fromAccount = fromAccount;
    }

    public Integer getToAccount() {
        return this.toAccount;
    }

    public void setToAccount(Integer toAccount) {
        this.toAccount = toAccount;
    }

    public Double getAmount() {
        return this.amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public boolean SelfValidate() throws Exception {
        // TODO Auto-generated method stub

        boolean valid = false;
        if (AppUtility.CheckValidNumber(this.fromAccount) && AppUtility.CheckValidNumber(this.toAccount)
                && AppUtility.CheckValidNumber(this.amount))
            valid = true;
        else
            valid = false;

        return valid;
    }

}
