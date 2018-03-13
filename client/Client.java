import java.net.Socket;
import java.io.*;
class Client
{
	public static void main(String dt[])
	{
		Socket skt=null;
		InputStreamReader isr=null;
		BufferedReader br=null;
		DataOutputStream dos=null;
		DataInputStream dis=null;
		try
		{
			skt=new Socket("127.0.0.1",1999);//reference is created when there is positive feedback from the slient
			System.out.println("Connect to Server");
			isr=new InputStreamReader(System.in);
			br=new BufferedReader(isr);
			dos=new DataOutputStream(skt.getOutputStream());
			dis=new DataInputStream(skt.getInputStream());
			System.out.println("Enter Username");
			String username=br.readLine();
			System.out.println("Enter Password");
			String password=br.readLine();
			dos.writeUTF(username+";"+password);
			if(dis.readBoolean())
			{
				System.out.println("Access Granted!!");
				Send s=new Send(skt);
				Receive r=new Receive(skt);
				s.start();
				r.start();
				s.join();
				r.join();
			}	
			else
			{
				System.out.println("Wrong Username or Password");
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
				dis.close();
				dos.close();
				br.close();
				isr.close();
				skt.close();
			}
			catch(Exception e)
			{
				System.out.println("Finally "+e);
			}
		}
	}
}