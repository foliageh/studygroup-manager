package views;

import exceptions.ServerException;
import models.StudyGroup;
import network.CollectionRequest;
import network.CollectionResponse;
import services.StudyGroupService;

public class UpdateView implements View {
    private final StudyGroupService service = StudyGroupService.getInstance();

    @Override
    public CollectionResponse generateResponse(CollectionRequest request) {
        try {
            service.update(Long.parseLong(request.getCommandArg()), (StudyGroup) request.getObj(), request.getUser());
            return new CollectionResponse(true, "Success");
        } catch (ServerException e) {
            return new CollectionResponse(false, e.getMessage());
        }
    }
}
