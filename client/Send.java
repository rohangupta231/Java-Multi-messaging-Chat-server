import java.net.Socket;
import java.io.*;
class Send extends Thread
{
	Socket skt;
	public Send(Socket skt)
	{
		this.skt=skt;
	}
	public void run()
	{
		InputStreamReader isr=null;
		BufferedReader br=null;
		PrintWriter pw=null;
		try
		{
			isr= new InputStreamReader(System.in);
			br= new BufferedReader(isr);
			pw=new PrintWriter(skt.getOutputStream(),true);
			String msg="";
			do
			{
				msg=br.readLine();
				pw.println(msg);
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
				pw.close();
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