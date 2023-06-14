package commands;

import client.Client;

public class LogoutCommand extends Command {
    {
        name = "logout";
        description = "log out";
    }

    @Override
    public boolean execute(String commandArg) {
        Client.setUser(null);
        System.out.println("You have been logged out.");
        return true;
    }
}
