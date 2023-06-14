package ux;

import io.BufferedFileReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Scanner;

public class ConsoleInput {
    private static final Scanner console = new Scanner(System.in);
    private static final ArrayDeque<BufferedFileReader> inputFileReaders = new ArrayDeque<>();

    public static void setFile(String fileName) {
        try {
            if (inputFileReaders.stream().noneMatch(reader -> reader.getFilename().equals(fileName))) {
                inputFileReaders.push(new BufferedFileReader(fileName));
            }
            else {
                System.out.println("Ая-яй, кто же это рекурсию вызвал... нинада так пожалуйста");
                while (!inputFileReaders.isEmpty()) {
                    inputFileReaders.pop().close();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException e) {
            System.out.println("Error reading file.");
        }
    }

    public static String nextLine() {
        try {
            if (inputFileReaders.isEmpty())
                return console.nextLine().strip();
            BufferedFileReader inputReader = inputFileReaders.peek();
            if (inputReader.hasNext()) {
                String line = inputReader.readLine();
                System.out.println("[auto-mode] "+line);
                return line;
            }
            inputFileReaders.pop().close();
            return nextLine();
        } catch (IOException e) {
            System.out.println("Fatal error reading input.");
            throw new RuntimeException(e);
        }
    }
}
