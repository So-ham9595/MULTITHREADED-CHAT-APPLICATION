import java.io.*;
import java.net.*;
import java.util.*;

public class ChatClient {
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    private Scanner scanner;

    public ChatClient(String hostname, int port) throws UnknownHostException, IOException {
        socket = new Socket(hostname, port);
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new PrintWriter(socket.getOutputStream(), true);
        scanner = new Scanner(System.in);
    }

    public void start() {
        Thread receiverThread = new Thread(new MessageReceiver());
        receiverThread.start();

        while (true) {
            System.out.print("Enter message: ");
            String message = scanner.nextLine();
            writer.println(message);
        }
    }

    private class MessageReceiver implements Runnable {
        public void run() {
            try {
                String message;
                while ((message = reader.readLine()) != null) {
                    System.out.println("Received message: " + message);
                }
            } catch (IOException e) {
                System.out.println("Error reading from server: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) throws UnknownHostException, IOException {
        ChatClient client = new ChatClient("localhost", 8000);
        client.start();
    }
}
