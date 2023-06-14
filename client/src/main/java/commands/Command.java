package commands;

public abstract class Command {
    protected String name = "command";
    protected String verboseName;
    protected String description;

    public Command() {
    }

    public Command(String name) {
        if (name != null)
            this.name = name;
    }

    public final void setName(String name) {
        this.name = name;
    }

    public final String name() {
        return name;
    }

    public final String verboseName() {
        return verboseName != null ? verboseName : name;
    }

    public final String description() {
        return description != null ? description : name;
    }

    public abstract boolean execute(String commandArg);
}
