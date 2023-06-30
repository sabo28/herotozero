import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.Objects;

@Named
@SessionScoped
public class LoginBean implements Serializable {
    private String username;
    private String password;
    private String errorMessage;

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

    public String getErrorMessage() {
        return errorMessage;
    }

    public String login() throws ClassNotFoundException {
        String status = dataController.loginUser(username, password);
        if(Objects.equals(status, "Wrong username or password")){
            username = null;
            password = null;
            errorMessage = status;
        }else if (status.equals("admin")){
            System.out.println(FacesContext.getCurrentInstance().getExternalContext().getSessionMap());
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userRole", "admin");
            System.out.println(FacesContext.getCurrentInstance().getExternalContext().getSessionMap());
        } else if (status.equals("scientist")) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userRole", "scientist");
        }
        return status;
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

        username = null;
        password = null;

        return "index";
    }
}
