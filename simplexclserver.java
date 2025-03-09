import java.util.*;
import java.io.IOException;
import java.net.*;
public class simplexclserver 
{
 public static void main(String[] args) throws Exception
 {
 DatagramSocket socket = new DatagramSocket(7222);
 String msg = "";
 System.out.println("Client Sends....");
 while(!msg.equals("close"))
 {
 byte[] data = new byte[65842];
 DatagramPacket dp = new DatagramPacket(data,data.length);
 socket.receive(dp);
 msg = new String(dp.getData(),0,dp.getLength());
 System.out.println(msg);
 }
 System.out.println("Connection gets Closed");
 socket.close();
 }
}
