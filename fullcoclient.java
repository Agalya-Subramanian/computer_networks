import java.net.*;
import java.io.*;
public class fullcoclient {
public static void main(String[] args) throws Exception
{
Socket s=new Socket("localhost",33333);
System.out.println("Connected to server.");
DataInputStream din=new DataInputStream(s.getInputStream());
DataOutputStream dout=new DataOutputStream(s.getOutputStream());
BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
String str="",str2="";
while(!str.equals("bye"))
{
System.out.println("Enter your message : ");
str=br.readLine();
dout.writeUTF(str);
dout.flush();
str2=din.readUTF();
System.out.println("Server says: "+str2);
}
dout.close();
s.close();
}
}

