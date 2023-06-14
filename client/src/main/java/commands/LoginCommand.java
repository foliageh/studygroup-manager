package commands;

import client.Client;
import network.*;
import models.User;
import serialization.serializers.UserSerializer;
import ux.ModelForm;

public class LoginCommand extends Command {
    {
        name = "login";
        description = "log in, so you can execute other commands";
    }

    @Override
    public boolean execute(String commandArg) {
        User user = new ModelForm<>(new UserSerializer()).fill();
        Client.setUser(user);
        CollectionRequest request = new CollectionRequest("login");
        CollectionResponse response = Client.getResponse(request);
        if (!response.success) Client.setUser(null);
        System.out.println(response.message);
        return response.success;
    }
}
