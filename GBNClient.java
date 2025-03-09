import java.io.*;
import java.net.*;
import java.util.Random;

public class GBNClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 9999);
             DataOutputStream out = new DataOutputStream(socket.getOutputStream());
             DataInputStream in = new DataInputStream(socket.getInputStream());
             BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.print("Enter total frames to send: ");
            int totalFrames = Integer.parseInt(reader.readLine());

            System.out.print("Enter window size: ");
            int windowSize = Integer.parseInt(reader.readLine());

            int base = 0, nextSeqNum = 0;
            boolean[] ackReceived = new boolean[totalFrames];

            Random rand = new Random();
            socket.setSoTimeout(1000); // Set timeout for receiving ACKs

            while (base < totalFrames) {
                // Send frames in the window
                while (nextSeqNum < base + windowSize && nextSeqNum < totalFrames) {
                    System.out.println("Sending Frame: " + nextSeqNum);
                    out.writeInt(nextSeqNum);
                    out.flush();
                    nextSeqNum++;
                }

                // Wait for ACKs
                try {
                    int ack = in.readInt();
                    if (ack >= base) {
                        System.out.println("Acknowledgment received for frames up to: " + ack);
                        for (int i = base; i <= ack; i++) {
                            ackReceived[i] = true;
                        }
                        base = ack + 1;
                    }
                } catch (SocketTimeoutException e) {
                    System.out.println("Timeout! Resending window from frame: " + base);
                    nextSeqNum = base; // Reset nextSeqNum to start resending from base
                }
            }

            System.out.println("All frames sent successfully!");
            out.writeInt(-1); // Indicate end of transmission
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
