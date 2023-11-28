import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class LoginBean implements Serializable {
    private String username;
    private String password;
    private String errorMessage;
    private String isLoggedInAs;

    public LoginBean() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getIsLoggedInAs() {
        return isLoggedInAs;
    }

    public void setIsLoggedInAs(String isLoggedInAs) {
        this.isLoggedInAs = isLoggedInAs;
    }

    public String login() throws ClassNotFoundException {
        DataController dataController = new DataController();
        String status = dataController.loginUser(username, password);
        if (status.equals("admin")){
            isLoggedInAs = status;
            return "admin";
        } else if (status.equals("scientist")) {
            isLoggedInAs = status;
            return "scientist";
        }else {
            username = null;
            password = null;
            isLoggedInAs = null;
            errorMessage = status;
        }
        return status;
    }

    public String logout() {

        username = null;
        password = null;
        errorMessage = null;
        isLoggedInAs = null;

        return "index";
    }

    public void resetErrorMessage() {
        errorMessage = null;
    }
}
