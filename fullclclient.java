import java.lang.*;
import java.util.*;
import java.net.*;
import java.io.*;
public class fullclclient 
{
 public static void main(String args[]) throws Exception
 {
 	DatagramSocket ss = new DatagramSocket();
 	String str1= "", str2="";
	byte[] sendData;
 	byte[] recData=new byte[1024];
 	BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
 	while(!str1.equals("close"))
	{
 		System.out.println("Client:");
 		str1=consoleReader.readLine();
 		sendData=str1.getBytes();
 		DatagramPacket sendPacket= new
		DatagramPacket(sendData,sendData.length,InetAddress.getByName("localhost"),7225);
 		ss.send(sendPacket);
 		DatagramPacket recPacket=new DatagramPacket(recData,recData.length);
 		ss.receive(recPacket);
 		str1=new String(recPacket.getData(),0,recPacket.getLength());
 		System.out.println("Server sends..."+str1); 
	}
	System.out.println("Connection gets closed...");
 	ss.close(); 
 }
}
