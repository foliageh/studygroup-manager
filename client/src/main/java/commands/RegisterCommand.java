package commands;

import client.Client;
import network.*;
import models.User;
import serialization.serializers.UserSerializer;
import ux.ModelForm;

public class RegisterCommand extends Command {
    {
        name = "register";
        description = "register a new user and log in";
    }

    @Override
    public boolean execute(String commandArg) {
        User user = new ModelForm<>(new UserSerializer()).fill();
        Client.setUser(user);
        CollectionRequest request = new CollectionRequest("register");
        CollectionResponse response = Client.getResponse(request);
        if (!response.success) Client.setUser(null);
        System.out.println(response.message);
        return response.success;
    }
}
