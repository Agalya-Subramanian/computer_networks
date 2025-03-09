
import java.net.*;
import java.io.*;
public class fullcoserver {
public static void main(String[] args)throws Exception{
ServerSocket ss=new ServerSocket(33333);
System.out.println("Server started. Waiting for client...");
Socket s=ss.accept();
System.out.println("Client connected.");
DataInputStream din=new DataInputStream(s.getInputStream());
DataOutputStream dout=new DataOutputStream(s.getOutputStream());
BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
String str="",str2="";
while(!str.equals("bye")) {
str=din.readUTF();
System.out.println("Client says: "+str);
System.out.println("Enter your message : ");
str2=br.readLine();
dout.writeUTF(str2);
dout.flush();
}
din.close();
s.close();
ss.close();
}
}