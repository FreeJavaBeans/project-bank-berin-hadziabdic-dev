package bankingapp.Views.AbstractViewClasses;

import java.util.Scanner;
import bankingapp.API;
import bankingapp.Controller.Controller;
import bankingapp.Views.Singleton.ScannerSingleton;
import bankingapp.Views.ViewInterfaces.View;

/**
 * This class is the base class for all view classes, and it defines the shared
 * functionality across the vast majority of views in this application. Most
 * views will require a scanner for reading input and most views will require
 * children and parent nodes which this class defines in it its children and
 * parents variables.
 */
public abstract class ViewBase extends ViewInitBase implements View {

    protected Scanner reader;

    protected Controller controllerRef = null;

    protected String next = null;

    public ViewBase() {

        this.reader = ScannerSingleton.GetScanner();

        controllerRef = Controller.GetInstance();
        this.next = API.CUSTOMER_MENU;

    }

    @Override
    public String GetNext() {
        return this.next;
    }

    @Override
    public void SetNext(String next) {
        this.next = next;

    }

}
