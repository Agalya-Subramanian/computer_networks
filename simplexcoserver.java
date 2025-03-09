//simplex connection oriented 
import java.util.*; 
import java.net.*; 
import java.io.*; 
public class simplexcoserver 
{ 
    public static void main(String args[]) throws Exception 
    { 
        ServerSocket ss=new ServerSocket(12345); 
        System.out.println("Server gets Started"); 
        System.out.println("Waiting for an Client to Connect"); 
        Socket cs=ss.accept(); 
        System.out.println("Connection builded with Server"); 
        DataInputStream in = new DataInputStream(cs.getInputStream()); 
        System.out.println("Reading from Client Side"); 
        String msg =""; 
        while (!msg.equals("Stop"))  
        { 
           msg=in.readUTF(); 
           System.out.println(msg);     
        } 
        ss.close(); 
        System.out.println("Connection closed");       
    } 
}