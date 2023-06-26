import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
public class LoginBean {
    private String username;
    private String password;
    private String loginStatus;

    public LoginBean() {
    }

    @Inject
    private DataController dataController;

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

    public String getLoginStatus() {
        return loginStatus;
    }

    public void login() throws ClassNotFoundException {
        this.loginStatus = dataController.loginUser(username, password);
    }
}
