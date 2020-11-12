package bankingapp.Model.PreppedStatements;

public class Statements {

    // Create Operations
    public static final String CREATE_USER = "insert into users(username,password_,account_type_id) values(?,?,default)"; // tested
    // approve account appliation
    public static final String CREATE_BANK_ACCOUNT = "insert into bank_account(account_id,username,balance) values(default,?,?)";// tested
    // apply for bank account
    public static final String CREATE_BANK_ACCOUNT_APPLICATION = "insert into account_applications(app_id,username,starting_amount) values(default, ?,?)";// tested
    // request money transfer
    public static final String CREATE_MONEY_TRANSFER = "insert into pending_transfers (transfer_id,from_acct,to_acct,amount) values(default,?,?,?)";
    public static final String CREATE_TRANSACTION = "insert into transactions values(default,?,?,default)";

    // Read operations

    public static final String READ_ALL_ACCOUNTS_BY_USER = "select account_id,balance from bank_account where username = ?";
    public static final String READ_ALL_PENDING_TRANSFERS = "select transfer_id, from_acct, to_acct, amount from pending_transfers p inner join bank_account b on b.username=? and b.account_id=p.to_acct";

    public static final String READ_ALL_PENDING_ACCOUNTS = "select * from account_applications";
    public static final String READ_ACCOUNT_BALANCE = "select balance,username from bank_account where account_id=?";
    // Authentication related reads
    public static final String READ_USER_BY_USERNAME = "select username,password_,account_type from users natural join type_lookup where username = ? "; // tested
    public static final String READ_ACCOUNTS_BY_USERNAME = "select account_id from bank_account where username=?";
    public static final String READ_ACCOUNTS_AND_BALANCE_BY_USERNAME = "select account_id, balance from bank_account where username=?";
    public static final String READ_TRANSFER_BY_ID_USERNAME_AND_PASSWORD = "select transfer_id,from_acct,to_acct,amount from pending_transfers natural join users where transfer_id=? and username=? and password_=?";

    // Updaye operations
    public static final String UPDATE_ACCOUNTBALANCE_BY_ID = "update bank_account set balance=? where account_id=?"; // withdraw
    // deposit

    public static final String DELETE_PENDING_TRANSFER_BY_ID = "delete from pending_transfers where transfer_id=?";
    public static final String DELETE_PENDING_ACCOUNT_BY_ID = "delete from account_applications where app_id=?"; // tested
    public static final String READ_ACCOUNT_APPLICATION_BY_ID = "select * from account_applications where app_id=?";

    /*
     * insert into users values('a_customer', '1234',1); -- a user w/ account type
     * customer insert into users values('a_employee', '4321', 2); -- a user w/
     * account type employee
     * 
     * 
     * insert into bank_account(username,balance) values ('a_customer', 431.12);
     * insert into bank_account(username,balance) values ('a_customer', 41.12);
     * 
     * insert into account_applications values(default, 'a_customer', 432.10);
     * insert into account_applications values(default, 'a_customer', 343.42);
     * 
     * insert into pending_transfers values(1,2,200.12);
     */

}
