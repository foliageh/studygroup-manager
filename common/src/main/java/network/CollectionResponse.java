package network;

import java.io.Serializable;

public class CollectionResponse implements Serializable {
    public final boolean success;
    public final String message;

    public CollectionResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
