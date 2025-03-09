import java.io.*;
import java.net.*;
import java.util.Random;

public class GBNServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(9999)) {
            System.out.println("Receiver (Server) is waiting for sender...");

            try (Socket socket = serverSocket.accept();
                 DataInputStream in = new DataInputStream(socket.getInputStream());
                 DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {

                int expectedFrame = 0;
                Random rand = new Random();

                while (true) {
                    int frame = in.readInt();
                    if (frame == -1) break; // End of transmission

                    if (frame == expectedFrame) {
                        System.out.println("Received Frame: " + frame);
                        expectedFrame++;
                    } else {
                        System.out.println("Duplicate or out-of-order frame received: " + frame);
                    }

                    // Simulate ACK loss
                    if (rand.nextInt(10) < 2) {
                        System.out.println("ACK for Frame " + (expectedFrame - 1) + " lost!");
                        continue;
                    }

                    System.out.println("Sending ACK for Frame: " + (expectedFrame - 1));
                    out.writeInt(expectedFrame - 1);
                    out.flush();
                }
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
