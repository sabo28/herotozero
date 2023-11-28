import org.junit.Before;
import org.junit.Test;

public class AdminPanelTest {

    private AdminBean adminBean;
    @Before
    public void setup() throws ClassNotFoundException {
            adminBean = new AdminBean();
    }

    @Test
    public void getAllPendingRequests() throws ClassNotFoundException {
        System.out.println(adminBean.getRequests().size());
    }
}
