//simplex connection oriented 
import java.util.*; 
import java.net.*; 
import java.io.*; 
public class simplexcoclient 
{ 
    public static void main(String args[])throws Exception 
    { 
        DataInputStream in = new DataInputStream(System.in); 
        Socket ss = new Socket("localhost",12345); 
        System.out.println("Connection was made with Server"); 
        System.out.println("Give an Message to Server:"); 
        System.out.println("Enter 'Stop' to Close the Connection"); 
        DataOutputStream out = new DataOutputStream(ss.getOutputStream()); 
        String msg = ""; 
        while (!msg.equals("Stop"))  
        { 
              msg = in.readLine(); 
              out.writeUTF(msg); 
        }       
        ss.close(); 
        System.out.println("Connection closed");    
    }            
} 
 
