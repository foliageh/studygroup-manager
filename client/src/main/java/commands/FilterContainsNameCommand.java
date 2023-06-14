package commands;

import client.Client;
import network.*;

public class FilterContainsNameCommand extends Command {
    {
        name = "filter_contains_name";
        verboseName = "filter_contains_name {name}";
        description = "print elements whose name field value contains the specified substring";
    }

    @Override
    public boolean execute(String commandArg) {
        if (commandArg.isBlank()) {
            System.out.println("Please provide a name");
            return false;
        }
        CollectionRequest request = new CollectionRequest(name(), commandArg);
        CollectionResponse response = Client.getResponse(request);
        System.out.println(response.message);
        return response.success;
    }
}
