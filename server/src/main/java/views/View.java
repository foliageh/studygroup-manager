package views;

import network.CollectionRequest;
import network.CollectionResponse;

public interface View {
    CollectionResponse generateResponse(CollectionRequest request);
}
