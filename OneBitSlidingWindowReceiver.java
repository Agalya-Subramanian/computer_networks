import java.net.*;
import java.io.*;

public class OneBitSlidingWindowReceiver {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8000);
        Socket socket = serverSocket.accept();

        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());

        int numFrames = in.readInt();
        int expectedSeqNum = 0;

        for (int i = 0; i < numFrames; i++) {
            int frame = in.readInt();
            System.out.println("Receiver: Received frame " + frame);

            if (frame == expectedSeqNum) {
                out.writeInt(frame);
                System.out.println("Receiver: Sent ACK " + frame);
                expectedSeqNum++;
            } else {
                out.writeInt(-1);
                System.out.println("Receiver: Sent NACK");
            }
        }

        if (expectedSeqNum == numFrames) {
            out.writeInt(1);
            System.out.println("Receiver: Sent confirmation");
        }

        socket.close();
        serverSocket.close();
    }
}
