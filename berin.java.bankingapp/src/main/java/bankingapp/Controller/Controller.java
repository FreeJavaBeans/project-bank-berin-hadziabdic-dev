package bankingapp.Controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import bankingapp.API;
import bankingapp.Protocol.Classes.RequestProtocol;
import bankingapp.Protocol.Classes.ResponseProtocol;
import bankingapp.Services.Classes.AccountAndBalanceHandler;
import bankingapp.Services.Classes.ApplyForAccountHandler;
import bankingapp.Services.Classes.ApproveRejectAccountHandler;
import bankingapp.Services.Classes.ApproveTransferHandler;
import bankingapp.Services.Classes.BalanceViewHandler;
import bankingapp.Services.Classes.CreateUserHandler;
import bankingapp.Services.Classes.DepositHandler;
import bankingapp.Services.Classes.GetAllCustomerAccountsHandler;
import bankingapp.Services.Classes.GetPendingAccountApplicationsHandler;
import bankingapp.Services.Classes.LoginAuthenticateUserHandler;
import bankingapp.Services.Classes.PostMoneyTransferHandler;
import bankingapp.Services.Classes.ViewCustomersTransfersHandler;
import bankingapp.Services.Classes.WithdrawHandler;
import bankingapp.Services.Interfaces.HandlerService;

public class Controller {

    private static Controller controller;
    Map<String, HandlerService> handlers;

    private Controller() {
        this.handlers = new HashMap<String, HandlerService>();
        this.handlers.put(API.REGISTER, new CreateUserHandler());
        this.handlers.put(API.LOGIN, new LoginAuthenticateUserHandler());
        this.handlers.put(API.APPLY_FOR_ACCOUNT, new ApplyForAccountHandler());
        this.handlers.put(API.VIEW_CUSTOMERS_ACCOUNT_LISTING, new GetAllCustomerAccountsHandler());
        this.handlers.put(API.VIEW_ACCOUNT_BALANCE, new BalanceViewHandler());
        this.handlers.put(API.VIEW_ACCOUNTS_AND_BALANCES, new AccountAndBalanceHandler());
        this.handlers.put(API.DEPOSIT, new DepositHandler());
        this.handlers.put(API.WITHDRAW, new WithdrawHandler());
        this.handlers.put(API.POST_TRANSFER, new PostMoneyTransferHandler());
        this.handlers.put(API.VIEW_TRANSFERS, new ViewCustomersTransfersHandler());
        this.handlers.put(API.ACCEPT_TRANSFER, new ApproveTransferHandler());
        this.handlers.put(API.GET_PENDING_ACCOUNTS, new GetPendingAccountApplicationsHandler());
        this.handlers.put(API.APPROVE_ACCOUNT_APP, new ApproveRejectAccountHandler());

    }

    public static Controller GetInstance() {
        if (Controller.controller == null)
            controller = new Controller();

        return controller;

    }

    public boolean RegisterHandler(String apiEndpoint, HandlerService businessLogic) {

        boolean success = true;

        if (!Objects.isNull(businessLogic))
            this.handlers.put(apiEndpoint, businessLogic);
        else
            success = false;

        return success;

    }

    public ResponseProtocol InvokeRoute(RequestProtocol request) throws Exception {

        ResponseProtocol resp = null;
        HandlerService service = this.handlers.get(request.GetUrl());

        if (service == null || request == null)
            resp = new ResponseProtocol(false,
                    "404.The requested route could not be found, or your request message was null. Please try again");
        else
            resp = service.Handler(request);

        return resp;
    }

}
