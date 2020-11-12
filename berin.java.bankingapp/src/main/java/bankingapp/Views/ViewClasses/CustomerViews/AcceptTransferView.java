package bankingapp.Views.ViewClasses.CustomerViews;

import java.util.Map;

import bankingapp.API;
import bankingapp.ApplicationLookUpTable.Definitions;
import bankingapp.Protocol.Classes.RequestProtocol;
import bankingapp.Protocol.Classes.ResponseProtocol;
import bankingapp.UtilityClasses.AppUtility;
import bankingapp.Views.AbstractViewClasses.AccountViewBase;
import bankingapp.Views.ViewInterfaces.SendDynamicViewData;

public class AcceptTransferView extends AccountViewBase implements SendDynamicViewData {

    public AcceptTransferView(Map<String, Integer> integerData, Map<String, Double> doubleData,
            Map<String, String> userdata) {
        super(integerData, doubleData, userdata);
        // TODO Auto-generated constructor stub
    }

    @Override
    public ResponseProtocol SendDynamicData() {
        // TODO Auto-generated method stub

        RequestProtocol request = new RequestProtocol(API.ACCEPT_TRANSFER);
        ResponseProtocol resp = null;

        request.SetStringHeader(Definitions.USERNAME, this.stringState.get(Definitions.USERNAME));
        request.SetStringHeader(Definitions.PASSWORD, this.stringState.get(Definitions.PASSWORD));
        request.SetNumericHeader(Definitions.TRANSFER_ID, this.integerData.get(Definitions.TRANSFER_ID));

        resp = request.SendMessage();

        return resp;

    }

    @Override
    public void RenderView() throws Exception {
        // TODO Auto-generated method stub
        ResponseProtocol resp = null;
        System.out.println(
                "Hello, " + this.stringState.get(Definitions.USERNAME) + ". Welcome to the Accept Transfer View!");
        System.out.println("We are loading your pending transfers. . .");
        this.InitView();
        if (this.renderDynamicPrompt) {
            this.ParseInteger("Enter the transfer #ID you'd like to approve, and press enter.",
                    "You entered an invalid transfer ID." + Definitions.INVALID_INTEGER, Definitions.TRANSFER_ID);
            resp = this.SendDynamicData();

            this.ProcessDynamicViewData(resp);
        }

    }

    @Override
    protected void InitView() {
        RequestProtocol request = new RequestProtocol(API.VIEW_TRANSFERS);
        request.SetStringHeader(Definitions.USERNAME, this.stringState.get(Definitions.USERNAME));
        request.SetStringHeader(Definitions.PASSWORD, this.stringState.get(Definitions.PASSWORD));
        ResponseProtocol resp = request.SendMessage();

        if (resp.GetResponseCode()) {
            if (!resp.GetBody().contains("We found no awaiting transfers.")) {
                this.renderDynamicPrompt = true;
            }

            AppUtility.PrintStringSet("Server response ", resp.GetBody());

        } else {
            System.out.println(
                    "Bad response response recieved from server. Please make sure your fields are correct and try again.");
        }
    }

    @Override
    public boolean ProcessDynamicViewData(ResponseProtocol resp) {

        AppUtility.PrintStringSet("Server Response", resp.GetBody());

        return true;
    }

}
