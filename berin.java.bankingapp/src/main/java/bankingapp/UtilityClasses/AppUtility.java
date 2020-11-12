package bankingapp.UtilityClasses;

import java.sql.ResultSet;
import java.util.Set;

//Utility class for perfoirming various useful comparisons in app.
public abstract class AppUtility {

    // This function tells us if we're dealing with empty strings.
    public static boolean CheckNotNullOrEmptyString(String s) {

        return s != null && s.length() > 0;

    }

    // this function tells us if we're dealing with a parsable integer string

    public static boolean CheckValidInteger(String integerString) {

        boolean validInteger = true;
        Integer buffer;

        // try [arsing integer]
        try {
            Integer.parseInt(integerString);
        } catch (Exception e) {
            validInteger = false; // if exception throw, we have bad integer input like a letter mixed in with
                                  // digits, for wexample.
        }

        // if no excption thrown, parse int and make sure its greater than zero.
        if (validInteger) {
            buffer = Integer.parseInt(integerString);
            validInteger = buffer > 0;
        }

        return validInteger;
    }

    // this function tells us if we're dealing with a parsable integer string

    public static boolean CheckValidNumber(Integer integer) {

        return integer != null && integer > 0;
    }

    public static boolean CheckValidNumber(Double duble) {

        return duble != null && duble > 0;
    }

    public static boolean CheckValidDouble(String Dbl) {
        boolean validDouble = false;
        Double parsedDouble = null;
        try {
            parsedDouble = Double.parseDouble(Dbl);

            if (parsedDouble != null && parsedDouble > 0)
                validDouble = true;

        } catch (Exception e) {

        }

        return validDouble;

    }

    // returns true if both strings one and two are not null and they content
    // equals.
    public static boolean StringCompare(String one, String two) {

        return CheckNotNullOrEmptyString(one) && CheckNotNullOrEmptyString(two) && one.equals(two);
    }

    public static void PrintStringSet(String header, Set<String> setToPrint) {

        if (header != null)
            System.out.println(header);

        for (String data : setToPrint) {
            System.out.println(data);
        }

    }

    public static boolean ColumnExists(ResultSet results, String columnName) {
        boolean exists = false;

        if (results != null && columnName != null) {
            try {
                results.findColumn(columnName); // will throw if columnName does not exist.
                exists = true;
            } catch (Exception e) {

            }
        }

        return exists;
    }

}
