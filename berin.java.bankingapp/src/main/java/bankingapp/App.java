package bankingapp;

import bankingapp.Views.ViewClasses.CustomerViews.AcceptTransferView;
import bankingapp.Views.ViewClasses.CustomerViews.ApplyView;
import bankingapp.Views.ViewClasses.CustomerViews.BalanceView;
import bankingapp.Views.ViewClasses.CustomerViews.CustomerMenu;
import bankingapp.Views.ViewClasses.CustomerViews.DepositView;
import bankingapp.Views.ViewClasses.CustomerViews.PostMoneyTransferView;
import bankingapp.Views.ViewClasses.CustomerViews.WithdrawView;
import bankingapp.Views.ViewClasses.EmployeeViews.ApproveAccountView;
import bankingapp.Views.ViewClasses.EmployeeViews.EmployeeMenuView;
import bankingapp.Views.ViewClasses.EmployeeViews.ViewAllTransactionsView;
import bankingapp.Views.ViewClasses.EmployeeViews.ViewCustomersAccountView;
import bankingapp.Views.ViewClasses.GeneralViews.Index;
import bankingapp.Views.ViewClasses.GeneralViews.LoginView;
import bankingapp.Views.ViewClasses.GeneralViews.RegisterView;

import java.util.Map;

import bankingapp.Views.ViewClasses.ViewContainer;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {

        try {
            ViewContainer appViews = new ViewContainer();
            Map<String, String> stringState = appViews.getStringState();
            Map<String, Integer> intState = appViews.getIntState();
            Map<String, Double> dblState = appViews.getDoubleState();

            Index index = new Index();
            RegisterView register = new RegisterView(intState, dblState, stringState);
            LoginView login = new LoginView(intState, dblState, stringState);
            CustomerMenu mainCustomerMenu = new CustomerMenu(intState, dblState, stringState);
            ApplyView applyForAccount = new ApplyView(intState, dblState, stringState);
            BalanceView getBalance = new BalanceView(intState, dblState, stringState);
            DepositView depositView = new DepositView(intState, dblState, stringState);
            WithdrawView withdrawView = new WithdrawView(intState, dblState, stringState);
            PostMoneyTransferView postTransferView = new PostMoneyTransferView(intState, dblState, stringState);
            AcceptTransferView acceptTransferView = new AcceptTransferView(intState, dblState, stringState);

            EmployeeMenuView employeeMenu = new EmployeeMenuView(intState, dblState, stringState);
            ApproveAccountView approveView = new ApproveAccountView(intState, dblState, stringState);
            ViewCustomersAccountView viewCusotmersAccountView = new ViewCustomersAccountView(intState, dblState,
                    stringState);
            ViewAllTransactionsView viewAllTransacts = new ViewAllTransactionsView(intState, dblState, stringState);

            appViews.RegisterView(API.ROOTVIEW, index);
            appViews.RegisterView(API.REGISTER, register);
            appViews.RegisterView(API.LOGIN, login);
            appViews.RegisterView(API.APPLY_FOR_ACCOUNT, applyForAccount);
            appViews.RegisterView(API.CUSTOMER_MENU, mainCustomerMenu);
            appViews.RegisterView(API.VIEW_CUSTOMERS_ACCOUNT_LISTING, getBalance);
            appViews.RegisterView(API.DEPOSIT, depositView);
            appViews.RegisterView(API.WITHDRAW, withdrawView);
            appViews.RegisterView(API.POST_TRANSFER, postTransferView);
            appViews.RegisterView(API.ACCEPT_TRANSFER, acceptTransferView);

            appViews.RegisterView(API.EMPLOYEE_MENU, employeeMenu);
            appViews.RegisterView(API.APPROVE_ACCOUNT_APP, approveView);
            appViews.RegisterView(API.VIEW_ACCOUNTS_AND_BALANCES, viewCusotmersAccountView);
            appViews.RegisterView(API.EMPLOYEE_VIEW_TRANSACTIONS, viewAllTransacts);

            try {
                appViews.Init();
            } catch (Exception e) {
                System.out.println("Critical error exiting...");
            }
        } catch (Exception e) {

        }
    }

}