import java.io.IOException;
import java.net.*;

public class ClienteUDP {

	public static void main(String[] args) throws IOException {
		DatagramSocket s = new DatagramSocket();
		InetAddress dir = InetAddress.getByName("10.11.67.21");
		String msg = "Hola, esto es un mensaje\n";
		byte[] buf = new byte[256];
		buf = msg.getBytes();
		DatagramPacket p = new DatagramPacket(buf, buf.length, dir, 7777);
		s.send(p);
		s.receive(p);
		System.out.println(p.getData());
		s.close();
	}

}
