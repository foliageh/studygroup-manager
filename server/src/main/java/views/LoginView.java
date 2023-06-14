package views;

import exceptions.ServerException;
import network.CollectionRequest;
import network.CollectionResponse;
import services.UserService;

public class LoginView implements View {
    private final UserService service = UserService.getInstance();

    @Override
    public CollectionResponse generateResponse(CollectionRequest request) {
        try {
            service.login(request.getUser());
            return new CollectionResponse(true, "You have successfully logged in.");
        } catch (ServerException e) {
            return new CollectionResponse(false, e.getMessage());
        }
    }
}
