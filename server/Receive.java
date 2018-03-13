import java.net.Socket;
import java.io.*;
import java.util.Date;
import java.text.SimpleDateFormat;
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
		FileWriter fw=null;
		BufferedWriter bw=null;
		try
		{
			isr= new InputStreamReader(skt.getInputStream());
			br= new BufferedReader(isr);
			SimpleDateFormat sdf1=new SimpleDateFormat("dd_MM_YYYY");
			SimpleDateFormat sdf2=new SimpleDateFormat("HH:mm:ss");
			Date d=new Date();
			String filedate=sdf1.format(d);
			fw=new FileWriter(filedate+".txt",true);
			bw=new BufferedWriter(fw);
			String msg="";
			do
			{
				msg=br.readLine();
				Date d1=new Date();
				String cdate=sdf2.format(d1);
				bw.write("Client: "+cdate+msg);
				bw.newLine();
				System.out.println("Client:"+msg);
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