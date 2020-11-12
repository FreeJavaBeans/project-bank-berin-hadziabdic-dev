package bankingapp.Views.Singleton;

import java.util.Scanner;

public final class ScannerSingleton {

    private static Scanner singletonScanner;

    private ScannerSingleton() {
        ScannerSingleton.singletonScanner = new Scanner(System.in);
    }

    public static Scanner GetScanner() {
        if (ScannerSingleton.singletonScanner == null)
            ScannerSingleton.singletonScanner = new Scanner(System.in);

        return ScannerSingleton.singletonScanner;
    }

}
