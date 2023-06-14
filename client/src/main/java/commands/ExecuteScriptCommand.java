package commands;

import ux.ConsoleInput;

public class ExecuteScriptCommand extends Command {
    {
        name = "execute_script";
        verboseName = "execute_script {script_filename}";
        description = "read and execute the script from the specified file";
    }

    @Override
    public boolean execute(String commandArg) {
        ConsoleInput.setFile(commandArg);
        return true;
    }
}
