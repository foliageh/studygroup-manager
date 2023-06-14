package views;

import models.StudyGroup;
import network.CollectionRequest;
import network.CollectionResponse;
import services.StudyGroupService;

public class AddView implements View {
    private final StudyGroupService service = StudyGroupService.getInstance();

    @Override
    public CollectionResponse generateResponse(CollectionRequest request) {
        service.create((StudyGroup) request.getObj(), request.getUser());
        return new CollectionResponse(true, "Success");
    }
}
