import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {


    private static Calculator calculatorSession = new Calculator();

    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Please enter 12345 to continue");
            return;
        }

        int port = Integer.parseInt(args[0]);

        try {
            startServer(port);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void startServer(int port) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is now running on port: " + port);
            while (true) {
            Socket clientSocket = serverSocket.accept();
            ClientHandler clientHandler = new ClientHandler(clientSocket, calculatorSession);
            Thread clientThread = new Thread(clientHandler);
            clientThread.start();
            }
        }
    }

}