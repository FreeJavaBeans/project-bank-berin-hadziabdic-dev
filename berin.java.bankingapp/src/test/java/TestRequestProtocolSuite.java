
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import bankingapp.Protocol.Classes.ResponseProtocol;

@TestMethodOrder(OrderAnnotation.class)
public class TestRequestProtocolSuite {

    private ResponseProtocol testMessage;

    @Test
    @Order(1)
    public void TestTrueConstructorAndResponseBody() {

        testMessage = new ResponseProtocol(true, "");
        assertEquals(true, testMessage.GetResponseCode());

    }

    @Test
    @Order(2)
    public void TestFalseConstructorAndResponseBody() {
        testMessage = new ResponseProtocol(true, "");
        assertEquals(true, testMessage.GetResponseCode());
    }

    @Test
    @Order(3)
    public void TestGetSetString() {
        testMessage = new ResponseProtocol(true, "");
        String test = "test";

        testMessage.SetStringHeader(test, test);
        assertEquals(test, testMessage.GetStringHeader(test));
    }

    @Test
    @Order(4)
    public void TestGetSetInteger() {
        testMessage = new ResponseProtocol(true, "");
        String test = "test";
        int testInteger = 100;

        testMessage.SetNumericHeader(test, testInteger);
        assertEquals(testInteger, testMessage.GetNumericIntegerHeader(test).intValue());
    }

}
