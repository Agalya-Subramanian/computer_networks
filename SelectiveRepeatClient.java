import java.io.*;
import java.net.*;

public class SelectiveRepeatClient {
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
            boolean[] ackReceived = new boolean[totalFrames]; // Ensure this is the correct size

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
                    if (ack >= base && ack < totalFrames) {
                        System.out.println("Acknowledgment received for frame: " + ack);
                        ackReceived[ack] = true;

                        // Move base forward
                        while (base < totalFrames && ackReceived[base]) {
                            base++;
                        }
                    }
                } catch (SocketTimeoutException e) {
                    System.out.println("Timeout! Resending frames from: " + base);
                    nextSeqNum = base; // Reset nextSeqNum to start resending from base
                }
            }

            out.writeInt(-1); // Indicate end of transmission
            System.out.println("All frames sent successfully!");
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}