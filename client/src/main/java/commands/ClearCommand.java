package commands;

import client.Client;
import network.*;

public class ClearCommand extends Command {
    {
        name = "clear";
        description = "remove all objects you've created from the collection";
    }

    @Override
    public boolean execute(String commandArg) {
        CollectionRequest request = new CollectionRequest(name());
        CollectionResponse response = Client.getResponse(request);
        System.out.println(response.message);
        return response.success;
    }
}
