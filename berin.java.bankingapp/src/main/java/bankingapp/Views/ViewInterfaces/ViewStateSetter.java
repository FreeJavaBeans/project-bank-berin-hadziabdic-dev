package bankingapp.Views.ViewInterfaces;

public interface ViewStateSetter {
    public void SetStringState(String fieldName, String fieldValue) throws Exception;

    public void SetIntegerState(String fieldName, Integer fieldValue) throws Exception;

    public void SetDoubleState(String fieldName, Double fieldValue) throws Exception;
}