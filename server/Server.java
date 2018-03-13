import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
class Server
{
	public static void main(String dt[])
	{
		ServerSocket sskt=null;
		Socket skt=null;
		DataInputStream dis=null;
		DataOutputStream dos=null;
		try
		{
			sskt=new ServerSocket(1234);
			System.out.println("Waiting for client request");
			skt=sskt.accept();
			dis=new DataInputStream(skt.getInputStream());
			dos=new DataOutputStream(skt.getOutputStream());
			System.out.println("Connected to Client");
			String username="Rohan";
			String password="Rohan";
			String str=dis.readUTF();
			String arr[]=str.split(";");
			if((username.equals(arr[0]))&&(password.equals(arr[1])))
			{
				dos.writeBoolean(true);
				System.out.println("Valid User");
				Send s=new Send(skt);
				Receive r=new Receive(skt);
				s.start();
				r.start();
				s.join();
				r.join();
			}
			else
			{
				dos.writeBoolean(false);
				System.out.println("Invalid User");
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception "+e);
		}
		finally
		{
			try
			{
				dos.close();
				dis.close();
				skt.close();
				sskt.close();
			}
			catch(Exception e)
			{
				System.out.println("Finally "+e);
			}
		}
	}
}