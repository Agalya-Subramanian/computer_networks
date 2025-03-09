import java.net.*;
import java.io.*;
import java.util.Scanner;

public class OneBitSlidingWindowSender {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 8000);
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of frames to send: ");
        int numFrames = scanner.nextInt();

        out.writeInt(numFrames);

        for (int i = 0; i < numFrames; i++) {
            out.writeInt(i);
            System.out.println("Sender: Sent frame " + i);
        }

        // Wait for receiver to finish sending ACKs
        Thread.sleep(1000);

        DataInputStream in = new DataInputStream(socket.getInputStream());
        int confirmation = in.readInt();
        System.out.println("Sender: Received confirmation " + confirmation);

        socket.close();
    }
}

