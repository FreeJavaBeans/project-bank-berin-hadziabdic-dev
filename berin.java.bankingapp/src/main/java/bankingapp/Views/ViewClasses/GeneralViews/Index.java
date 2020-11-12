package bankingapp.Views.ViewClasses.GeneralViews;

import java.util.Map;

import bankingapp.API;
import bankingapp.Views.AbstractViewClasses.ViewBase;

public class Index extends ViewBase {

    Map<String, String> children;
    String nextRoute;

    public Index() {
        super();
    }

    @Override
    public void RenderView() throws Exception {
        // TODO Auto-generated method stub
        this.InitView();

    }

    @Override
    public String GetNext() {
        // TODO Auto-generated method stub

        return this.nextRoute;
    }

    @Override
    protected void InitView() {
        // TODO Auto-generated method stub
        System.out.println("Greetings! Welcome to our banking app..");
        System.out.println("Please make a choice from the menu below...");

        System.out.println("Type 1 to register.");
        System.out.println("Type 2 to login.");
        System.out.println("Type 3 to exit");

        String nextchoice = null;

        // while user has not chosen nextRoute, keep asking for it.
        while (nextchoice == null) {
            nextchoice = this.reader.nextLine();
            this.SetNext(nextchoice);

        }

    }

    @Override
    public void SetNext(String stringBuffer) {

        Integer choice = -1; // -1 is used to indicate an error condition.
        Integer buffer = null;
        try {
            buffer = Integer.parseInt(stringBuffer);

            if (buffer >= 1 && buffer <= 3)
                choice = buffer;
            else
                System.out.println("Bad choice detected please enter the number 1, 2 , or 3.");
        } catch (Exception e) {
            System.out.println("Bad choice detected please enter the number 1, 2 , or 3.");
        }

        if (choice != -1) {
            switch (choice) {
                case 1:
                    this.nextRoute = API.REGISTER;
                    break;
                case 2:
                    this.nextRoute = API.LOGIN;
                    break;
                case 3:
                    System.out.println("Exiting application. Bye...");
                    System.exit(1);
                    break;
                default:
                    this.nextRoute = null;
                    break;

            }
        }

    }

}
