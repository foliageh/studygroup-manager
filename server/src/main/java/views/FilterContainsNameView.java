package views;

import network.CollectionRequest;
import network.CollectionResponse;
import services.StudyGroupService;

public class FilterContainsNameView implements View {
    private final StudyGroupService service = StudyGroupService.getInstance();

    @Override
    public CollectionResponse generateResponse(CollectionRequest request) {
        String result = service.filterContainsName(request.getCommandArg());
        return new CollectionResponse(true, result);
    }
}
