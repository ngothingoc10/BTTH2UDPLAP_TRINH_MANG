package UDPChuoi;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPChuoiServer extends Thread {
	private int port;

	public UDPChuoiServer(int port) {
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
			String chuoihoa = ChuHoa(sendedString).trim();
			String chuoithuong = ChuThuong(sendedString).trim();
			String hoathuong = VuaHoaVuaThuong(sendedString).trim();
			int demtu = DemSoTu(sendedString);
			String s = chuoithuong+"\n"+chuoihoa+"\n"+hoathuong+"\n"+"So tu trong chuoi: "+demtu;
			System.out.println(s);
			sendData =s.toString().getBytes("UTF-8");
			
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, portClient); //van de o luc send nek
			serverSocket.send(sendPacket);
			
		}
		
	}

	public static void main(String[] args) throws Exception {
		UDPChuoiServer server = new UDPChuoiServer(6997);
		server.execute();
	}
	// TODO Auto-generated method stub
	// Gan cong cong 9876 cho chuong trinh
	// DatagramSocket serverSocket = new DatagramSocket(6997);
	// System.out.println("Server is started");
	// Tao cac mang byte de chua du lieu gui va nhan
//		byte[] sendData = new byte[1024];
//		byte[] receiveData = new byte[1024];
//		while (true) {
//			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
//			serverSocket.receive(receivePacket);
//			InetAddress ipAddress = receivePacket.getAddress();
//			int port = receivePacket.getPort();
//			String request = new String(receivePacket.getData(), "UTF-8");
//			String daoNguoc = ChuoiDaoNguoc(request).trim();
//			String chuThuong = ChuThuong(request).trim();
//			String chuHoa = ChuHoa(request).trim();
//			String vuaHoaVuaThuong = VuaHoaVuaThuong(request).trim();
//			int soTu = DemSoTu(request);
//			int soNguyenAm = DemNguyenAm(request);
//			String s = "";
//			s += "Chuoi dao nguoc: " + daoNguoc + "\n";
//			s += "Chu Hoa: " + chuHoa + "\n";
//			s += "Chu Thuong: " + chuThuong + "\n";
//			s += "Chu vua hoa vua thuong: " + vuaHoaVuaThuong + "\n";
//			s += "So Tu: " + soTu + "\n";
//			s += "So nguyen am: " + soNguyenAm + "\n";
//			System.out.println(s);
//			sendData = s.toString().getBytes("UTF-8");
//			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, port);
//			serverSocket.send(sendPacket);
//
//		}

	private static String ChuHoa(String str) {
		String temp = "";
		int len = str.length();
		for (int i = 0; i < len; i++) {
			char ch = str.charAt(i);
			if (ch >= 'a' && ch <= 'z') {
				ch = (char) (ch - 32);
			}
			temp += ch;
		}
		return temp;

	}

	private static String ChuThuong(String str) {
		String temp = "";
		int len = str.length();
		for (int i = 0; i < len; i++) {
			char ch = str.charAt(i);
			if (ch >= 'A' && ch <= 'Z') {
				ch = (char) (ch + 32);
			}
			temp += ch;
		}
		return temp;

	}

	private static String VuaHoaVuaThuong(String str) {
		String temp = "";
		int len = str.length();
		for (int i = 0; i < len; i++) {
			char ch = str.charAt(i);
			if (ch >= 'A' && ch <= 'Z') {
				ch = (char) (ch + 32);
			} else if (ch >= 'a' && ch <= 'z') {
				ch = (char) (ch - 32);
			}
			temp += ch;
		}
		return temp;

	}

	private static String ChuoiDaoNguoc(String str) {
		String temp = "";
		int len = str.length();
		for (int i = 0; i < len; i++) {
			char ch = str.charAt(len - i - 1);
			temp += ch;
		}
		return temp;

	}

	private static int DemSoTu(String str) {
		int count = 0;
		boolean notCounted = true;
		if (str == null) {
			return -1;
		}
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) != ' ' && str.charAt(i) != '\t' && str.charAt(i) != '\n') {
				if (notCounted) {
					count++;
					notCounted = false;

				}
			} else {
				notCounted = true;

			}

		}
		return count;

	}

	private static int DemNguyenAm(String str) {
		int count = 0;
		String temp = str.toLowerCase();
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
				count++;
			}
		}
		return count;

	}

}
