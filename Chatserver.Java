import java.io.*;
import java.net.*;
import java.util.*;

public class Chatserver {
    private ServerSocket serverSocket;
    private List<ClientHandler> clients;

    public Chatserver(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        clients = new ArrayList<>();
    }

    public void start() {
        System.out.println("Chat Server started. Listening for incoming connections...");

        while (true) {
            try {
                Socket socket = serverSocket.accept();
                System.out.println("Incoming connection from " + socket.getInetAddress());

                ClientHandler clientHandler = new ClientHandler(socket);
                clients.add(clientHandler);
                clientHandler.start();
            } catch (IOException e) {
                System.out.println("Error accepting incoming connection: " + e.getMessage());
            }
        }
    }

    private class ClientHandler extends Thread {
        private Socket socket;
        private BufferedReader reader;
        private PrintWriter writer;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                writer = new PrintWriter(socket.getOutputStream(), true);

                String message;
                while ((message = reader.readLine()) != null) {
                    System.out.println("Received message from client: " + message);
                    broadcastMessage(message);
                }
            } catch (IOException e) {
                System.out.println("Error reading from client: " + e.getMessage());
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    System.out.println("Error closing socket: " + e.getMessage());
                }
            }
        }

        private void broadcastMessage(String message) {
            for (ClientHandler client : clients) {
                if (client != this) {
                    client.writer.println(message);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Chatserver server = new Chatserver(8000);
        server.start();
    }
}
