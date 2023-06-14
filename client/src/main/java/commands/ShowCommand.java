package commands;

import client.Client;
import network.*;

public class ShowCommand extends Command {
    {
        name = "show";
        description = "print all elements of the collection";
    }

    @Override
    public boolean execute(String commandArg) {
        CollectionRequest request = new CollectionRequest(name());
        CollectionResponse response = Client.getResponse(request);
        System.out.println(response.message);
        return response.success;
    }
}