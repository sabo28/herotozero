import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ScientistPanelTest {
    private ScientistBean scientistBean;

    @Before
    public void setup() {
        scientistBean = new ScientistBean();
    }

    @Test
    public void getAllCountriesForDropdownMenu() throws ClassNotFoundException {
        System.out.println("There are " + scientistBean.getOptions().size() + " countries in the dropdown menu");

    }

    @Test
    public void sendNewEmissionDataToAdmin() throws ClassNotFoundException {
        String username = "jUnitScientist";
        String selectedCountry = "France";
        String inputValue = "983543.23";

        scientistBean.setName(username);
        scientistBean.setSelectedCountry(selectedCountry);
        scientistBean.setInputValue(inputValue);
        scientistBean.submitData();

        assertEquals(scientistBean.getPendingStatus(), "Request sent successfully");
        System.out.println(scientistBean.getPendingStatus());
    }

    @Test
    public void sendNewEmissionDataToAdminWithFalseInput() throws ClassNotFoundException {
        String username = "jUnitScientist";
        String selectedCountry = "France";
        String inputValue = "983543,23";

        scientistBean.setName(username);
        scientistBean.setSelectedCountry(selectedCountry);
        scientistBean.setInputValue(inputValue);
        scientistBean.submitData();

        assertEquals(scientistBean.getPendingStatus(), "Error: You can only enter numbers in this format: 1234 or 1234.1234");
        System.out.println(scientistBean.getPendingStatus());
    }
}
