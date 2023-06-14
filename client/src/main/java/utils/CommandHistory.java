package utils;

import commands.Command;

import java.util.ArrayDeque;

public class CommandHistory {
    private final ArrayDeque<Command> history;
    private final int size;

    public CommandHistory(int size) {
        this.history = new ArrayDeque<>(size);
        this.size = size;
    }

    public int size() {
        return size;
    }

    public void push(Command command) {
        if (history.size() == size)
            history.pollLast();
        history.push(command);
    }

    public void print() {
        if (history.isEmpty())
            System.out.println("The history is empty now.");
        else
            history.forEach(c -> System.out.println(c.name()));
    }
}
