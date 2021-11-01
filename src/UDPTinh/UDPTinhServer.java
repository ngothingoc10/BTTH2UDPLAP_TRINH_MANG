package UDPTinh;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPTinhServer extends Thread{
	private int port;

	public UDPTinhServer(int port) {
		this.port = port;
	}

	private void execute() throws IOException {
		// TODO Auto-generated method stub
		DatagramSocket serverSocket = new DatagramSocket(this.port);
		System.out.println("Server is waiting for Client");
		while (true) {
	
			byte[] sendData;
			byte[] receiveData = new byte[1024];
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			serverSocket.receive(receivePacket);
			InetAddress ipAddress = receivePacket.getAddress(); // cho nay dang null
			int portClient = receivePacket.getPort();
			
			String sendedString = new String(receivePacket.getData(), "UTF-8");
			System.out.println(sendedString);
			System.out.println("Processing");
			
//			System.out.println(s);
			sendData =(String.valueOf(TinhToan.calculate(sendedString))).getBytes("UTF-8");
			
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, portClient); 
			serverSocket.send(sendPacket);
			
		}
		
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		UDPTinhServer server = new UDPTinhServer(6997);
		server.execute();

	}

}
