package bankingapp.Protocol.Classes;

import java.util.Set;
import java.util.TreeSet;

import bankingapp.Protocol.AbstractClasses.AbstractMessage;

//This class extends the abstract msg class and adds respinse support for 
//our imitiation http protocol. This class simply adds
//a status code and the ability to get the message body
//which was decided to be Set<String> to be able to cover
//all of the possible scenarios thsi protocol should
//satisfy.
public class ResponseProtocol extends AbstractMessage {

    Boolean responseCode;
    Set<String> responseBody;

    public ResponseProtocol(Boolean response, Set<String> msg) {
        this.responseCode = response;
        this.responseBody = msg;

    }

    public ResponseProtocol(boolean response, String message) {
        this.responseBody = new TreeSet<String>();
        this.responseBody.add(message);
        this.responseCode = response;

    }

    public ResponseProtocol(boolean response, Set<String> messages) {
        this.responseBody = new TreeSet<String>(messages);
        this.responseCode = response;

    }

    public Set<String> GetBody() {
        return this.responseBody;
    }

    public Boolean GetResponseCode() {
        return this.responseCode;
    }

}
