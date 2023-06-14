package commands;

import client.Client;
import network.*;
import models.StudyGroup;
import serialization.serializers.StudyGroupSerializer;
import ux.ModelForm;

public class RemoveLowerCommand extends Command {
    {
        name = "remove_lower";
        description = "remove all elements lower than the specified one";
    }

    @Override
    public boolean execute(String commandArg) {
        StudyGroup obj = new ModelForm<>(new StudyGroupSerializer()).fill();
        CollectionRequest request = new CollectionRequest(name(), obj);
        CollectionResponse response = Client.getResponse(request);
        System.out.println(response.message);
        return response.success;
    }
}
