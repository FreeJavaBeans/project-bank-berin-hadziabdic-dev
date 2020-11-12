package bankingapp.Protocol.Interfaces;

//The message interface represents  the msg protocl 
//our front end uses to communicate with the back end
//Its similar to HTTP in that the protocl has headers,
//but this is a very java centric protocol fit to this app
// so I have String  header values and integer header values.
public interface Message {

    public String GetStringHeader(String headerName);

    public Double GetNumericHeader(String headerName);

    public Integer GetNumericIntegerHeader(String headerName);

    public void SetStringHeader(String headername, String headerValue);

    public void SetNumericHeader(String headername, Double headerValue);

    public void SetNumericHeader(String headername, Integer headerValue);

}
