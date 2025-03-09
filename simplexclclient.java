import java.net.*;
import java.util.*;
public class simplexclclient
{
 public static void main(String args[]) throws Exception
 {
 DatagramSocket socket = new DatagramSocket();
 Scanner s = new Scanner(System.in);
 String msg = "";
 while(!msg.equals("close"))
 {
 System.out.println("Enter Your Message (Enter 'close' to quit):");
 msg = s.nextLine();
 byte[] data = msg.getBytes();
 DatagramPacket dp = new DatagramPacket(data,data.length,InetAddress.getByName("localhost"),7222);
 socket.send(dp);
 }
 socket.close();
 }
} 