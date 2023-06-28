import jakarta.annotation.PostConstruct;
import jakarta.el.MethodExpression;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.List;

@Named
@RequestScoped
public class PendingRequestBean {

    private List<PendingRequest> requests;

    @Inject
    private DataController dataController;
    @PostConstruct
    public void init() throws ClassNotFoundException {
        requests = dataController.getAllPendingRequests();
    }

    public List<PendingRequest> getRequests() {
        return requests;
    }

    public MethodExpression deleteRequest() {
        return null;
    }

    public MethodExpression confirmRequest() {
        return null;
    }
}
