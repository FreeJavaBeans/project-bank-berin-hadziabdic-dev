package bankingapp.Model.Classes;

import bankingapp.UtilityClasses.AppUtility;

public class BankAccount {

    private Integer accountId;
    private Double balance;

    private String username;

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    };

    public BankAccount() {
        this.accountId = null;
        this.balance = null;
    }

    public Integer getAccountId() {
        return this.accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Double getBalance() {
        return this.balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public void Withdraw(Double amt) {
        if (ValidWithdrawal(amt))
            this.balance = balance - amt;
    }

    public void Deposit(Double amt) {
        if (this.ValidDeposit(amt))
            this.balance += amt;
    }

    public boolean ValidWithdrawal(Double withdraw) {

        return AppUtility.CheckValidNumber(withdraw) && this.balance > withdraw;
    }

    public boolean ValidDeposit(Double deposit) {
        return AppUtility.CheckValidNumber(deposit);
    }

}
