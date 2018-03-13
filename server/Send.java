import java.net.Socket;
import java.io.*;
import java.util.Date;
import java.text.SimpleDateFormat;
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
		FileWriter fw=null;
		BufferedWriter bw=null;
		try
		{
			isr= new InputStreamReader(System.in);
			br= new BufferedReader(isr);
			pw=new PrintWriter(skt.getOutputStream(),true);
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
				String sdate=sdf2.format(d1);
				bw.write("Server: "+sdate+msg);
				bw.newLine();
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
				bw.close();
				fw.close();
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