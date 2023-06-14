package ux;

import commands.*;
import utils.CommandHistory;

import java.util.LinkedHashMap;
import java.util.NoSuchElementException;

public class CommandHandler {
    private final int COMMAND_HISTORY_SIZE = 5;
    private final CommandHistory commandHistory = new CommandHistory(COMMAND_HISTORY_SIZE);
    private final LinkedHashMap<String, Command> commands = new LinkedHashMap<>() {{
        put("login", new LoginCommand());
        put("register", new RegisterCommand());
        put("logout", new LogoutCommand());
        put("info", new InfoCommand());
        put("show", new ShowCommand());
        put("add", new AddCommand());
        put("update", new UpdateCommand());
        put("remove_by_id", new RemoveByIdCommand());
        put("clear", new ClearCommand());
        put("remove_greater", new RemoveGreaterCommand());
        put("remove_lower", new RemoveLowerCommand());
        put("filter_by_group_admin", new FilterByGroupAdminCommand());
        put("filter_contains_name", new FilterContainsNameCommand());
        put("print_unique_student_count", new PrintUniqueStudentCount());
        put("exit", new ExitCommand());
        put("history", new HistoryCommand(commandHistory));
        put("execute_script", new ExecuteScriptCommand());
        forEach((name, command) -> command.setName(name));
    }};

    public void mainLoop() {
        System.out.println("\nWelcome to StudyGroup's collection manager! Thank you for choosing us!");
        System.out.println("Type 'help' to see the list of available commands.");

        while (true) {
            System.out.print("$ ");
            try {
                String command = ConsoleInput.nextLine();
                executeCommand(command);
            } catch (NoSuchElementException e) {
                System.out.println("Exiting...");
                break;
            }
        }
    }

    private void executeCommand(String enteredCommand) {
        String[] commandParts = enteredCommand.split(" ", 2);
        String commandName = commandParts[0];
        String commandArg = commandParts.length > 1 ? commandParts[1].strip() : "";

        if (commandName.equals("help")) {
            printHelp();
            return;
        }

        Command command = commands.get(commandName);
        if (command != null) {
            if (command.execute(commandArg))
                commandHistory.push(command);
        } else {
            System.out.println("Unknown command. Type 'help' to see the list of available commands.");
        }
    }

    private void printHelp() {
        StringBuilder output = new StringBuilder();
        output.append("Available commands:\n");
        commands.values().forEach(command -> output.append(command.verboseName()).append(" : ").append(command.description()).append("\n"));
        output.append("\nPlease note that any extra arguments will be ignored.");
        System.out.println(output);
    }
}
