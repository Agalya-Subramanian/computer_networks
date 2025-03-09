
import java.lang.*;
import java.util.*;
import java.net.*;
import java.io.*;
public class fullclserver {
 public static void main(String args[]) throws Exception{
 DatagramSocket ss = new DatagramSocket(7225);
 byte[] data=new byte[1024];
 String str1= "",str2="";
 while(!str1.equals("close")) 
 {
 	 DatagramPacket dp = new DatagramPacket(data,data.length);
 	 ss.receive(dp);
	 str1 = new String(dp.getData(),0,dp.getLength());
	 System.out.println("Client Sends...."+str1);
 	 System.out.println("Enter Your Message:");
	 BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
	 str2 = consoleReader.readLine();
	 byte[] data1 = str2.getBytes();
	 DatagramPacket dp1 = new
	 DatagramPacket(data1,data1.length,dp.getAddress(),dp.getPort());
	 ss.send(dp1); 
 }
 System.out.println("Connection gets Closed");
 ss.close();
 }
}