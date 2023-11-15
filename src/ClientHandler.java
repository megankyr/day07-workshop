import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.io.PrintWriter;

public class ClientHandler implements Runnable {

    private Socket clientSocket;
    private Calculator calculatorSession;

    public ClientHandler(Socket clientSocket, Calculator calculatorSession) {
        this.clientSocket = clientSocket;
        this.calculatorSession = calculatorSession;
    }

    @Override

    public void run() {
        try {
            handleClient(clientSocket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleClient(Socket clientSocket) throws IOException {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            String request;
            while ((request = in.readLine()) != null) {
                if (request.startsWith("Calculate:")) {
                    sendAnswer(clientSocket, request);
                } else if (request.equals("stop")) {
                    System.out.println("Client has requested to close the connection");
                    break;
                } else {
                    System.out.println("Invalid request");
                }
            }

        }
    }

    private void sendAnswer(Socket clientSocket, String request) throws IOException {
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            float answer = calculatorSession.evaluate(request.substring(11));
            out.println("Answer is " + answer);
        }
        }