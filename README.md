# MULTITHREADED-CHAT-APPLICATION

COMPANY: CODETECH IT SOLUTIONS

NAME: Soham Santosh Salunkhe

Intern ID :CITS0D507

DOMAIN: JAVA PROGRAMMING

BATCH DURATION: 4 Weeks

MENTOR NAME: NEELA SANTHOSH

This Java-based Chat Application demonstrates a basic client-server communication system using sockets. The application consists of two main components: ChatServer and ChatClient. The server listens for incoming client connections on a specified port, while the client connects to the server and allows users to send and receive messages. This implementation highlights the core principles of network programming in Java, including socket communication, multithreading, and input/output stream handling.

The ChatServer class initializes a ServerSocket on port 8000 and waits for incoming client connections. When a connection is established, the server logs the client's IP address and starts a new thread using a custom ClientHandler (not shown in full here but implied in the code). This design allows the server to handle multiple clients simultaneously, each on a separate thread, enabling concurrent message reception without blocking the server’s main thread. The server maintains a list of all connected clients in an ArrayList, which would typically be used for broadcasting messages in a full-featured chat app.

On the other side, the ChatClient class connects to the server using a Socket pointed to localhost and port 8000. It sets up both input (BufferedReader) and output (PrintWriter) streams to communicate with the server. Additionally, a Scanner is used to read user input from the command line. The client starts by launching a separate thread called MessageReceiver, which continuously listens for messages from the server. This allows the client to simultaneously send and receive messages, an essential feature for real-time chat systems.

The main thread of the client remains in a loop, prompting the user to input messages, which are then sent to the server using the output stream. Although the server currently just logs the received messages rather than broadcasting them back to other clients, the basic structure is in place to expand this into a group chat by forwarding received messages to all connected clients.

In the terminal screenshots provided, we see the server compiling and running first. The server outputs a message indicating it's waiting for connections. The client is then started, prompts the user for input, and sends messages like “soham santosh salunkhe” and “how are you?” to the server. The server receives and prints each message, confirming successful communication between the client and server through localhost.

This project is an excellent introduction to multithreaded network communication in Java. It teaches fundamental concepts such as client-server architecture, TCP/IP socket programming, handling multiple connections using threads, and basic input/output stream operations. With some enhancements—such as a message broadcasting system, user authentication, or a GUI interface—this project could be developed into a fully functional chat application.

Overall, the Chat Server and Client Java program is a practical, hands-on project that provides a foundational understanding of how real-time communication works over a network, making it ideal for students, developers, or anyone learning about Java network programming.

