package bankingapp.Protocol.Classes;

import bankingapp.Controller.Controller;
import bankingapp.Protocol.AbstractClasses.AbstractMessage;

//This class extends the abstract msg class and adds request support for 
//our imitiation http protocol. This class simply adds
//url support for routing.
public class RequestProtocol extends AbstractMessage {

    private String url;
    private Controller controller;

    public RequestProtocol(String url) {
        super();
        this.url = url;
        this.controller = Controller.GetInstance();
    }

    public ResponseProtocol SendMessage() {
        ResponseProtocol resp = null;

        try {
            resp = controller.InvokeRoute(this);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            resp = new ResponseProtocol(false,
                    "There was a problem with the server. Please try again at a later time.");
        }
        return resp;

    }

    public String GetUrl() {
        return this.url;
    }

}
