package bankingapp.Views.ViewInterfaces;

//This interface is used to route to different views through out the app. 
//Individual views will have a reference to the singleton viewrouter and can
// advance to the appropriate view after they've completed their tasks.
public interface ViewRouter {

    public void RouteView(String viewUrl);

}
