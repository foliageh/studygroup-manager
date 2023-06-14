package views;

import network.CollectionRequest;
import network.CollectionResponse;
import services.StudyGroupService;

public class ShowView implements View {
    private final StudyGroupService service = StudyGroupService.getInstance();

    @Override
    public CollectionResponse generateResponse(CollectionRequest request) {
        String result = service.show();
        return new CollectionResponse(true, result);
    }
}