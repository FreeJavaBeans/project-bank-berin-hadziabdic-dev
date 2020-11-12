package bankingapp.Views.ViewInterfaces;

public interface View {
    public void RenderView() throws Exception;

    public String GetNext();

    public void SetNext(String nextUrl);

}