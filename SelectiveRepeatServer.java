import java.io.*;
import java.net.*;

public class SelectiveRepeatServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(9999)) {
            System.out.println("Receiver (Server) is waiting for sender...");

            try (Socket socket = serverSocket.accept();
                 DataInputStream in = new DataInputStream(socket.getInputStream());
                 DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {

                boolean[] receivedFrames = new boolean[100]; // Assuming a maximum of 100 frames

                while (true) {
                    int frame = in.readInt();
                    if (frame == -1) break; // End of transmission

                    if (frame >= 0 && frame < receivedFrames.length) {
                        if (!receivedFrames[frame]) {
                            System.out.println("Received Frame: " + frame);
                            receivedFrames[frame] = true;

                            // Always send ACK for received frame
                            System.out.println("Sending ACK for Frame: " + frame);
                            out.writeInt(frame);
                            out.flush();
                        } else {
                            System.out.println("Duplicate frame received: " + frame);
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}