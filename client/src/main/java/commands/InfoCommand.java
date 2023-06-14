package commands;

import client.Client;
import network.*;

public class InfoCommand extends Command {
    {
        name = "info";
        description = "print information about the collection";
    }

    @Override
    public boolean execute(String commandArg) {
        CollectionRequest request = new CollectionRequest(name());
        CollectionResponse response = Client.getResponse(request);
        System.out.println(response.message);
        return response.success;
    }
}
