package bankingapp.Views.ViewClasses;

import java.util.HashMap;
import java.util.Map;
import bankingapp.API;
import bankingapp.Views.ViewInterfaces.View;

public class ViewContainer {

    Map<String, View> viewRoutes;
    Map<String, String> stringState;
    Map<String, Double> doubleState;
    Map<String, Integer> intState;

    public ViewContainer() {
        this.viewRoutes = new HashMap<String, View>();
        stringState = new HashMap<String, String>();
        doubleState = new HashMap<String, Double>();
        intState = new HashMap<String, Integer>();
    }

    public Map<String, String> getStringState() {
        return this.stringState;
    }

    public Map<String, Double> getDoubleState() {
        return this.doubleState;
    }

    public Map<String, Integer> getIntState() {
        return this.intState;
    }

    String chosenView = null;

    public void RegisterView(String url, View view) {
        viewRoutes.put(url, view);
    }

    public void Init() throws Exception {

        if (this.viewRoutes == null || this.viewRoutes.size() == 0 || this.viewRoutes.get("/") == null) {
            throw new Exception("Invalid view tree state detected. Fatal error. Shutting down.");
        } else {
            this.Render();
        }
    }

    protected void Render() {
        boolean exitApplication = false;
        chosenView = API.ROOTVIEW;

        while (!exitApplication) {
            try {
                View viewToRender = this.viewRoutes.get(chosenView);
                viewToRender.RenderView();
                chosenView = viewToRender.GetNext();
            } catch (Exception e) {
                System.out.println("404 view not found redirecting to welcome screen....");
                chosenView = API.ROOTVIEW;
                // reset view state in case of error.
                stringState = new HashMap<String, String>();
                doubleState = new HashMap<String, Double>();
                intState = new HashMap<String, Integer>();
            }

        }

    }

}
