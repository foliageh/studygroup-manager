package views;

import exceptions.ServerException;
import network.CollectionRequest;
import network.CollectionResponse;
import services.StudyGroupService;

public class RemoveByIdView implements View {
    private final StudyGroupService service = StudyGroupService.getInstance();

    @Override
    public CollectionResponse generateResponse(CollectionRequest request) {
        try {
            service.removeById(Long.parseLong(request.getCommandArg()), request.getUser());
            return new CollectionResponse(true, "Success");
        } catch (ServerException e) {
            return new CollectionResponse(false, e.getMessage());
        }
    }
}
