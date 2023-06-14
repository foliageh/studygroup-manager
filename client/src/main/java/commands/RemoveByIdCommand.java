package commands;

import client.Client;
import network.*;

public class RemoveByIdCommand extends Command {
    {
        name = "remove_by_id";
        verboseName = "remove_by_id {id}";
        description = "remove an element from the collection by its id";
    }

    @Override
    public boolean execute(String commandArg) {
        try {
            Long.parseLong(commandArg);
        } catch (NumberFormatException e) {
            System.out.println("Please provide a valid id");
            return false;
        }
        CollectionRequest request = new CollectionRequest(name(), commandArg);
        CollectionResponse response = Client.getResponse(request);
        System.out.println(response.message);
        return response.success;
    }
}
