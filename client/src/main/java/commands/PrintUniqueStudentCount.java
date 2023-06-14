package commands;

import client.Client;
import network.*;

public class PrintUniqueStudentCount extends Command {
    {
        name = "print_unique_student_count";
        description = "print the unique values of the studentsCount field of all elements in the collection";
    }

    @Override
    public boolean execute(String commandArg) {
        CollectionRequest request = new CollectionRequest(name());
        CollectionResponse response = Client.getResponse(request);
        System.out.println(response.message);
        return response.success;
    }
}
