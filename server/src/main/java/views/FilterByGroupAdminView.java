package views;

import models.Person;
import network.CollectionRequest;
import network.CollectionResponse;
import services.StudyGroupService;

public class FilterByGroupAdminView implements View {
    private final StudyGroupService service = StudyGroupService.getInstance();

    @Override
    public CollectionResponse generateResponse(CollectionRequest request) {
        String result = service.filterByGroupAdmin((Person) request.getObj());
        return new CollectionResponse(true, result);
    }
}
