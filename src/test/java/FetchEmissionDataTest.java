import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class FetchEmissionDataTest {
    private Co2EmissionBean co2EmissionBean;

    @Before
    public void setup() {
        co2EmissionBean = new Co2EmissionBean();
    }

    @Test
    public void getEmissionDataForGermany() throws ClassNotFoundException {
        String country = "Germany";

        co2EmissionBean.setCountry(country);

        co2EmissionBean.fetchEmissionData();

        String result = co2EmissionBean.getEmissionData();

        assertNotNull(result);

        System.out.println("Test 1: The emission data for Germany is " + result);
    }
    @Test
    public void getNoDataAsReturn() throws ClassNotFoundException {
        String country = "German";

        co2EmissionBean.setCountry(country);

        co2EmissionBean.fetchEmissionData();

        String result = co2EmissionBean.getEmissionData();

        assertEquals(result, "");

        System.out.println("Test 2: Invalid country!");
    }
}
