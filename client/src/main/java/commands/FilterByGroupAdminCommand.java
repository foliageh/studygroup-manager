package commands;
import client.Client;
import models.Person;
import network.CollectionRequest;
import network.CollectionResponse;
import serialization.serializers.PersonSerializer;
import ux.ModelForm;

public class FilterByGroupAdminCommand extends Command {
    {
        name = "filter_by_group_admin";
        description = "print elements whose groupAdmin field value is equal to the specified one";
    }

    @Override
    public boolean execute(String commandArg) {
        Person obj = new ModelForm<>(new PersonSerializer()).fill();
        CollectionRequest request = new CollectionRequest(name(), obj);
        CollectionResponse response = Client.getResponse(request);
        System.out.println(response.message);
        return response.success;
    }
}
