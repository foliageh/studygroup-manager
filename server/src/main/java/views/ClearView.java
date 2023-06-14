package views;

import network.CollectionRequest;
import network.CollectionResponse;
import services.StudyGroupService;

public class ClearView implements View {
    private final StudyGroupService service = StudyGroupService.getInstance();

    @Override
    public CollectionResponse generateResponse(CollectionRequest request) {
        service.clear(request.getUser());
        return new CollectionResponse(true, "Success");
    }
}
