package bankingapp;

public final class API {

    public static final String ROOTVIEW = "/";
    public static final String LOGIN = "/login";

    // customer routes
    public static final String RENDER_SAME_VIEW_AGAIN = "/rerender";
    public static final String CUSTOMER_MENU = "/customermenu";
    public static final String VIEW_BALANCES = "/customerbalances";
    public static final String VIEW_ACCOUNTS_AND_BALANCES = "/customeraccountsandbalances";
    public static final String WITHDRAW = "/withdraw"; // handler done
    public static final String DEPOSIT = "/deposit"; // handler done
    public static final String APPLY_FOR_ACCOUNT = "/applyaccount";
    public static final String ACCEPT_TRANSFER = "/accepttransfer";
    public static final String POST_TRANSFER = "/posttransfer";
    public static final String REGISTER = "/register"; // handler done
    public static final String VIEW_ACCOUNT_BALANCE = "/customerbalance";

    public static final String MENU = "/menu";
    public static final String EXIT = "EXIT";

    // employee
    public static final String APPROVE_ACCOUNT_APP = "/approveaccountapplication";
    public static final String TRANSACTION_LOG = "/transactionlog";
    public static final String VIEW_CUSTOMERS_ACCOUNT_LISTING = "/viewcustomeraccounts";

    public static final String VIEW_TRANSFERS = "/viewtransfers";
    public static final String EMPLOYEE_MENU = "/employeemenu";
    public static final String EMPLOYEE_VIEW_TRANSACTIONS = "/viewtransactions";
    public static final String GET_PENDING_ACCOUNTS = "/getpendingaccounts";
}
