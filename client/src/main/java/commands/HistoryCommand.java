package commands;

import utils.CommandHistory;

public class HistoryCommand extends Command {
    private final CommandHistory history;

    public HistoryCommand(CommandHistory history) {
        this.history = history;
        name = "history";
        description = "print the last "+history.size()+" commands executed";
    }

    @Override
    public boolean execute(String commandArg) {
        history.print();
        return true;
    }
}
