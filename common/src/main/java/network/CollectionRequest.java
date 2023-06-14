package network;

import models.Model;
import models.User;

import java.io.Serializable;

public class CollectionRequest implements Serializable {
    private final String command;
    private String commandArg;
    private Model obj;
    private User user;

    public CollectionRequest(String command) {
        this.command = command;
    }
    public CollectionRequest(String command, String commandArg) {
        this(command);
        this.commandArg = commandArg;
    }
    public CollectionRequest(String command, Model obj) {
        this(command);
        this.obj = obj;
    }
    public CollectionRequest(String command, String commandArg, Model obj) {
        this(command);
        this.commandArg = commandArg;
        this.obj = obj;
    }

    public String getCommand() {
        return command;
    }
    public String getCommandArg() {
        return commandArg;
    }
    public Model getObj() {
        return obj;
    }
    public User getUser() {
        return user;
    }

    public void setCommandArg(String commandArg) {
        this.commandArg = commandArg;
    }
    public void setObj(Model obj) {
        this.obj = obj;
    }
    public void setUser(User user) {
        this.user = user;
    }
}
