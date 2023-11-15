import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Please enter localhost:12345 to continue");
            return;
        }

        String[] hostPort = args[0].split(":");
        String host = hostPort[0];
        int port = Integer.parseInt(hostPort[1]);

        try {
            requestCalculation(host, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void requestCalculation(String host, int port) throws IOException {
        try (Socket clientSocket = new Socket(host, port);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            Scanner scanner = new Scanner(System.in);
            String input;

            while (true) {
                System.out.println("Please enter your equation or 'stop' to exit");
                input = scanner.nextLine();

                if (input.equals("stop")) {
                    out.println("stop");
                    break;
                }

                out.println("Calculate: " + input);

                String response = in.readLine();
                if (response != null && response.startsWith("Answer is")) {
                    String answer = response.substring(10);
                    System.out.println("Result: " + answer);
                } else {
                    System.out.println("Unexpected response from server: " + response);
                }

            }

            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}