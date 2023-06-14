package client;

import network.CollectionRequest;
import network.CollectionResponse;
import models.User;
import ux.CommandHandler;
import ux.ConsoleInput;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.NoSuchElementException;

public class Client {
    private static Socket socket;
    private static ObjectOutputStream outStream;
    private static ObjectInputStream inStream;
    private static final String HOST = "localhost";
    private static final int PORT;
    static {
        int port = 5454;
        try {
            port = Integer.parseInt(System.getenv().get("SERVER_PORT"));
        } catch (NumberFormatException e) {
            System.out.println("Port variable SERVER_PORT isn't set or invalid, using default port "+port);
        }
        PORT = port;
    }
    private static final int CONNECTION_MAX_RETRIES = 5;
    private static User user;

    public static void setUser(User user) {
        Client.user = user;
    }

    public void launch() {
        if (connectToServer()) {
            CommandHandler ch = new CommandHandler();
            ch.mainLoop();
            try {
                outStream.close();
                inStream.close();
                socket.close();
            } catch (IOException e) {
                System.out.println("Error closing socket: " + e.getMessage());
            }
        }
    }

    private static boolean connectToServer() {
        int retryCount = 0;
        while (retryCount++ < CONNECTION_MAX_RETRIES) {
            try {
                socket = new Socket(HOST, PORT);
                outStream = new ObjectOutputStream(socket.getOutputStream());
                inStream = new ObjectInputStream(socket.getInputStream());
                return true;
            } catch (IOException e) {
                System.out.print("Connection failed. Try to reconnect? (y/n) ");
                try {
                    if (ConsoleInput.nextLine().strip().equals("y"))
                        System.out.println((CONNECTION_MAX_RETRIES-retryCount)+" attempts left. Retrying...");
                    else break;
                } catch (NoSuchElementException ee) {
                    break;
                }
            }
        }
        System.out.println("Connection failed after " + retryCount + " retries.");
        return false;
    }

    public static CollectionResponse getResponse(CollectionRequest request) {
        request.setUser(user);
        try {
            outStream.writeObject(request);
            outStream.flush();
            return (CollectionResponse) inStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            if (connectToServer()) return getResponse(request);
            return new CollectionResponse(false, "Server is temporarily unavailable.");
        }
    }
}