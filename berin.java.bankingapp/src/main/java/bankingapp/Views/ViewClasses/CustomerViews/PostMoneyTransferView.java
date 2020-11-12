package bankingapp.Views.ViewClasses.CustomerViews;

import java.util.Map;

import bankingapp.API;
import bankingapp.ApplicationLookUpTable.Definitions;
import bankingapp.Protocol.Classes.RequestProtocol;
import bankingapp.Protocol.Classes.ResponseProtocol;
import bankingapp.UtilityClasses.AppUtility;
import bankingapp.Views.AbstractViewClasses.AccountViewBase;
import bankingapp.Views.ViewInterfaces.SendDynamicViewData;

public class PostMoneyTransferView extends AccountViewBase implements SendDynamicViewData {

    public PostMoneyTransferView(Map<String, Integer> integerData, Map<String, Double> doubleData,
            Map<String, String> userdata) {
        super(integerData, doubleData, userdata);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void RenderView() throws Exception {
        // TODO Auto-generated method stub
        ResponseProtocol resp = null;

        System.out.println("Hello. " + this.stringState.get(Definitions.USERNAME));
        this.InitView();
        if (this.renderDynamicPrompt) {
            this.ParseInteger("Enter the account you'd like to transfer from and press enter: ",
                    "Invalid account entered", Definitions.FROM_ACCOUNT);
            this.ParseInteger("Enter the account you'd like to transfer to and press enter: ",
                    "Invalid account entered", Definitions.TO_ACCOUNT);
            this.ParseDouble("Enter the amount you'd like to transfer and press enter", "Invalid amount entered.",
                    Definitions.ACTION_AMOUNT);

            resp = this.SendDynamicData();
            this.ProcessDynamicViewData(resp);
        }

    }

    @Override
    public ResponseProtocol SendDynamicData() {
        // TODO Auto-generated method stub

        ResponseProtocol resp = null;
        RequestProtocol request = new RequestProtocol(API.POST_TRANSFER);

        request.SetNumericHeader(Definitions.FROM_ACCOUNT, this.integerData.get(Definitions.FROM_ACCOUNT));
        request.SetNumericHeader(Definitions.TO_ACCOUNT, this.integerData.get(Definitions.TO_ACCOUNT));
        request.SetNumericHeader(Definitions.ACTION_AMOUNT, this.doubleData.get(Definitions.ACTION_AMOUNT));

        resp = request.SendMessage();

        return resp;
    }

    @Override
    public boolean ProcessDynamicViewData(ResponseProtocol resp) {
        // TODO Auto-generated method stub

        if (resp.GetResponseCode()) {
            System.out.println("Your money transfer request was succesfully processed.");
            AppUtility.PrintStringSet("Server response:", resp.GetBody());
        } else {
            System.out.println(
                    "There was a problem posting your transfer. please ensure you've entered valid accounts and transfer amounts.");
        }
        return resp.GetResponseCode();
    }

}
