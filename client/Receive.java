import java.net.Socket;
import java.io.*;
class Receive extends Thread
{
	Socket skt;
	public Receive(Socket skt)
	{
		this.skt=skt;
	}
	public void run()
	{
		InputStreamReader isr=null;
		BufferedReader br=null;
		try
		{
			isr= new InputStreamReader(skt.getInputStream());
			br= new BufferedReader(isr);
			String msg="";
			do
			{
				msg=br.readLine();
				System.out.println("Server:"+msg);
			}while((msg.equals("bye"))!=true);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		finally
		{
			try
			{
				br.close();
				isr.close();
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
	}
}