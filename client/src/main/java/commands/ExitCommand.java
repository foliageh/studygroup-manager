package commands;

import java.util.NoSuchElementException;

public class ExitCommand extends Command {
    {
        name = "exit";
        description = "end the program";
    }

    @Override
    public boolean execute(String commandArg) {
        throw new NoSuchElementException();
    }
}
