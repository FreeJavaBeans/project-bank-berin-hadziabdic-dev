package bankingapp.Views.ViewClasses.EmployeeViews;

import java.util.Map;
import java.util.Set;

import bankingapp.API;
import bankingapp.ApplicationLookUpTable.Definitions;
import bankingapp.Protocol.Classes.RequestProtocol;
import bankingapp.Protocol.Classes.ResponseProtocol;
import bankingapp.UtilityClasses.AppUtility;
import bankingapp.Views.AbstractViewClasses.AccountViewBase;
import bankingapp.Views.ViewInterfaces.SendDynamicViewData;

public class ApproveAccountView extends AccountViewBase implements SendDynamicViewData {

    public ApproveAccountView(Map<String, Integer> integerData, Map<String, Double> doubleData,
            Map<String, String> userdata) {
        super(integerData, doubleData, userdata);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void RenderView() throws Exception {
        // TODO Auto-generated method stub

        RequestProtocol req = null;
        ResponseProtocol resp = null;
        ResponseProtocol dynamicResp = null;

        System.out.println("Hello, " + this.stringState.get(Definitions.USERNAME)
                + " welcome to the employee only approve account view.");
        System.out.println("Loading pending account approvals . . .");

        this.InitView();

        req = new RequestProtocol(API.GET_PENDING_ACCOUNTS);
        resp = req.SendMessage();

        if (this.ConditionalRender(resp.GetBody())) {

            dynamicResp = SendDynamicData();
            this.ProcessDynamicViewData(dynamicResp);
        }

    }

    private boolean ConditionalRender(Set<String> pendingTransers) throws Exception {
        boolean sendDynamicRequest = true;
        if (pendingTransers == null || pendingTransers.size() == 0
                || pendingTransers.contains("No pending account applications have been discovered.")) {
            System.out.println("No pending account applications have been discovered. Try again later.");

            sendDynamicRequest = false;
        } else {
            AppUtility.PrintStringSet("Here's a list of pending account applications we discovered...",
                    pendingTransers);
            this.GetApproveOrRejectFromCustomer();
            this.ParseInteger("Enter a pending account application id to you'd like to target and press enter: ",
                    "Invalid ID entered. " + Definitions.INVALID_INTEGER, Definitions.APPROVE_TRANSFER);
        }

        return sendDynamicRequest;
    }

    @Override
    protected void InitView() {
        // TODO Auto-generated method stub

    }

    @Override
    public ResponseProtocol SendDynamicData() {
        // TODO Auto-generated method stub
        RequestProtocol dynamicRequest = new RequestProtocol(API.APPROVE_ACCOUNT_APP);
        dynamicRequest.SetNumericHeader(Definitions.ACCOUNT_APPLICATION_ID,
                this.integerData.get(Definitions.APPROVE_TRANSFER));
        dynamicRequest.SetStringHeader(Definitions.ACTION_STRING, this.stringState.get(Definitions.ACTION_STRING));
        ResponseProtocol resp = dynamicRequest.SendMessage();

        return resp;
    }

    @Override
    public boolean ProcessDynamicViewData(ResponseProtocol resp) {
        // TODO Auto-generated method stub
        if (resp.GetResponseCode()) {
            System.out.println("The application was processed accordingly. Thank you.");

        } else {
            AppUtility.PrintStringSet("Server Response", resp.GetBody());
        }
        return resp.GetResponseCode();
    }

    @Override
    public String GetNext() {
        return API.EMPLOYEE_MENU;
    };

    private void GetApproveOrRejectFromCustomer() throws Exception {
        boolean continueLoop = true;
        String buffer = null;

        while (continueLoop) {
            System.out.println("Approve/Reject Account Menu");
            System.out.println("Type in the appropriate choice from the menu below...");
            System.out.println("1)  Approve a listed account application.");
            System.out.println("2)  Reject a listed account application.");

            buffer = this.reader.nextLine();

            switch (buffer) {
                case "1":
                    this.SetStringState(Definitions.ACTION_STRING, Definitions.APPROVE_ACCOUNT);
                    continueLoop = false;
                    break;
                case "2":
                    this.SetStringState(Definitions.ACTION_STRING, Definitions.REJECT_ACCOUNT);
                    continueLoop = false;
                    break;
                default:
                    System.out.println(
                            "Your choice was not recognized. Please enter 1 to approve an account or 2 to reject an account...");
            }

        }
    }
}
