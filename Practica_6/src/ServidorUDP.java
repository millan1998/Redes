import java.io.IOException;
import java.net.*;

public class ServidorUDP {

	public static void main(String[] args) throws IOException {
		DatagramSocket s = new DatagramSocket(7777);
		DatagramPacket p = new DatagramPacket(new byte[256], 256);
		s.receive(p);
		p.setAddress(p.getAddress());
		p.setPort(p.getPort());
		s.send(p);
		s.close();
	}

}
