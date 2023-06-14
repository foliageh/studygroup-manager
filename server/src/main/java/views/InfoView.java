package views;

import network.CollectionRequest;
import network.CollectionResponse;
import services.StudyGroupService;

public class InfoView implements View {
    private final StudyGroupService service = StudyGroupService.getInstance();

    @Override
    public CollectionResponse generateResponse(CollectionRequest request) {
        return new CollectionResponse(true, service.info());
    }
}
