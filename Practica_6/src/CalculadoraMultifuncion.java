import java.io.IOException;
import java.net.*;
import java.nio.charset.Charset;



public class CalculadoraMultifuncion {
	public static void cliente(String direc, String msg, int puerto) throws IOException {
		DatagramSocket s = new DatagramSocket();
		InetAddress dir = InetAddress.getByName(direc);
		//InetAddress dir = InetAddress.getLocalHost();
		byte[] buf = new byte[256];
		msg=msg+"*";
		buf=msg.getBytes(Charset.forName("UTF-8"));
		DatagramPacket p = new DatagramPacket(buf, buf.length, dir, puerto);
		s.send(p);
		s.receive(p); // se bloquea hasta que recibe un datagrama
		String values =new String(p.getData(),"UTF-8");
		System.out.println(values);
		s.close();
	}
	public static void servidor(int puerto) throws IOException
	{
		DatagramSocket s = new DatagramSocket(puerto);
		DatagramPacket p = new DatagramPacket(new byte[256], 256);
		s.receive(p); // se bloquea hasta que recibe un datagrama
		byte[] aux =p.getData();
		String values = new String(aux,"UTF-8");
		String[] valores=CalculadoraMultifuncion.getNum(values);
		System.out.println(valores[0]);
		System.out.println(valores[1]);
		System.out.println(p.getAddress());
		String[] num=valores[1].split("\\*");
		Float i=(float) Integer.parseInt(num[0]);
		switch(valores[0])
		{
			case "SQR": 
				i=(float) Math.sqrt(i);
				break;
			case "SIN":
				i= (float) Math.sin(i);
			break;
			case "COS":
				i= (float) Math.cos(i);
			break;
		}
		
		String  dev= String.valueOf(i);
		byte[] buf=dev.getBytes(Charset.forName("UTF-8"));
		p.setData(buf);
		p.setAddress(p.getAddress());
		p.setPort(p.getPort());
		s.send(p);
		s.close();
	}
	
	private static String[] getNum(String s)
	{
		return s.split("\\-");
	}
}