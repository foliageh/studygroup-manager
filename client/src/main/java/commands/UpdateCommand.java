package commands;

import client.Client;
import network.*;
import models.StudyGroup;
import serialization.serializers.StudyGroupSerializer;
import ux.ModelForm;

public class UpdateCommand extends Command {
    {
        name = "update";
        verboseName = "update {id}";
        description = "update the value of a collection element whose id is equal to the given one";
    }

    @Override
    public boolean execute(String commandArg) {
        try {
            Long.parseLong(commandArg);
        } catch (NumberFormatException e) {
            System.out.println("Please provide a valid id");
            return false;
        }

        StudyGroup sg = new StudyGroup();
        sg.setId(Long.parseLong(commandArg));
        CollectionRequest req = new CollectionRequest(name(), commandArg, sg);
        CollectionResponse resp = Client.getResponse(req);
        if (!resp.success) {
            System.out.println(resp.message);
            return false;
        }

        StudyGroup obj = new ModelForm<>(new StudyGroupSerializer()).fill();
        CollectionRequest request = new CollectionRequest(name(), commandArg, obj);
        CollectionResponse response = Client.getResponse(request);
        System.out.println(response.message);
        return response.success;
    }
}
