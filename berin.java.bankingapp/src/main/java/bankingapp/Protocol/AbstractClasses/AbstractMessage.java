package bankingapp.Protocol.AbstractClasses;

import java.util.HashMap;
import java.util.Map;

import bankingapp.Protocol.Interfaces.Message;
import bankingapp.UtilityClasses.AppUtility;

public abstract class AbstractMessage implements Message {

    // These are the datastructures that hold the headers for the messages sent in
    // our application.
    Map<String, String> stringHeaders; // string based headers. usernames password and etc
    Map<String, Double> numericHeaders; // int based headers. account #, account balance, and et
    Map<String, Integer> numericIntegerHeaders;

    public AbstractMessage() {
        this.stringHeaders = new HashMap<String, String>();
        this.numericHeaders = new HashMap<String, Double>();
        this.numericIntegerHeaders = new HashMap<String, Integer>();
    }

    // Returns Stribg based headers.
    @Override
    public String GetStringHeader(String headerName) {
        // TODO Auto-generated method stub
        String returnValue = null;

        if (AppUtility.CheckNotNullOrEmptyString(headerName))
            returnValue = stringHeaders.get(headerName);

        return returnValue;
    }

    // returns integer based headers.
    @Override
    public Double GetNumericHeader(String headerName) {
        // TODO Auto-generated method stub
        Double returnValue = null;

        if (AppUtility.CheckNotNullOrEmptyString(headerName))
            returnValue = numericHeaders.get(headerName);

        return returnValue;
    }

    @Override
    public Integer GetNumericIntegerHeader(String headerName) {
        // TODO Auto-generated method stub
        Integer returnValue = null;

        if (AppUtility.CheckNotNullOrEmptyString(headerName))
            returnValue = numericIntegerHeaders.get(headerName);

        return returnValue;
    }

    @Override
    public void SetStringHeader(String headerName, String headerValue) {
        // TODO Auto-generated method stub

        if (AppUtility.CheckNotNullOrEmptyString(headerName) && AppUtility.CheckNotNullOrEmptyString(headerValue))
            this.stringHeaders.put(headerName, headerValue);

    }

    @Override
    public void SetNumericHeader(String headerName, Double headerValue) {
        // TODO Auto-generated method stub
        if (AppUtility.CheckNotNullOrEmptyString(headerName) && AppUtility.CheckValidNumber(headerValue))
            this.numericHeaders.put(headerName, headerValue);

    }

    public void SetNumericHeader(String headerName, Integer headerValue) {
        if (AppUtility.CheckNotNullOrEmptyString(headerName) && AppUtility.CheckValidNumber(headerValue))
            this.numericIntegerHeaders.put(headerName, headerValue);

    }

    public Map<String, String> GetStringHeaders() {
        return this.stringHeaders;
    }

    public Map<String, Double> GetNumericHeaders() {
        return this.numericHeaders;
    }

}
