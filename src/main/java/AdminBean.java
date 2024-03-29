import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class AdminBean {

    private final DataController dataController = new DataController();
    public AdminBean() {
    }

    public List<PendingRequest> getRequests() throws ClassNotFoundException {
        return dataController.getAllPendingRequests();
    }
    

    public void confirmRequest(PendingRequest pendingRequest) {
        dataController.confirmRequest(pendingRequest);
    }

    public void deleteRequest(PendingRequest pendingRequest) {
        dataController.deleteRequest(pendingRequest);
    }
}
