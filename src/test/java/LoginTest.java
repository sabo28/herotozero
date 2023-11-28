import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LoginTest {
    private LoginBean loginBean;

    @Before
    public void setup() {
        loginBean = new LoginBean();
    }

    @Test
    public void testLoginAsAdmin() throws ClassNotFoundException {
        // Testdaten für den Login
        String username = "admin";
        String password = "password";

        // Setzen der Parameter in der LoginBean
        loginBean.setUsername(username);
        loginBean.setPassword(password);

        // Aufruf der Login-Methode
        String result = loginBean.login();

        // Assertions zum Überprüfen des erwarteten Ergebnisses
        assertEquals("admin", result);
        System.out.println("Test 1: Login-Test erfolgreich bestanden! Login als " + loginBean.getIsLoggedInAs());
    }
    @Test
    public void testLoginAsScientist() throws ClassNotFoundException {
        // Testdaten für den Login
        String username = "scientist";
        String password = "Scientist123";

        // Setzen der Parameter in der LoginBean
        loginBean.setUsername(username);
        loginBean.setPassword(password);

        // Aufruf der Login-Methode
        String result = loginBean.login();

        // Assertions zum Überprüfen des erwarteten Ergebnisses
        assertEquals("scientist", result);
        System.out.println("Test 2: Login-Test erfolgreich bestanden! Login als " + loginBean.getIsLoggedInAs());
    }

    @Test
    public void testLoginNotSuccessful() throws ClassNotFoundException {
        String username = "scientist";
        String password = "Scientist";

        loginBean.setUsername(username);
        loginBean.setPassword(password);

        String result = loginBean.login();

        assertNotEquals("admin", result);
        assertNotEquals("scientist", result);

        System.out.println("Test 3: Login-Test nicht erfolgreich bestanden! " + result);
    }

    @Test
    public void testLogout() {
        assertEquals(loginBean.logout(), "index");
        System.out.println("Logout redirect to " + loginBean.logout() + ".xhtml");
    }
}