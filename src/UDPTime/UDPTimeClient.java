package UDPTime;
import java.io.*;
import java.net.*;

public class UDPTimeClient {
  public static void main(String args[]) throws Exception{
	  DatagramSocket clientSocket = new DatagramSocket();// gan cong
	  InetAddress IPAddress = InetAddress.getByName("localhost");
	  byte[] sendData = new byte[1024];
	  byte[] receiveData = new byte[1024];
	  sendData = "getDate".getBytes();
	  //tao datagram co noi dung yeu cau loaij du lieu de gui cho server
	  DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
	  clientSocket.send(sendPacket); // gui du lieu cho server
	  //tao datagram rong de nhan du lieu
	  DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
	  //nhan du lieu tu server
	  clientSocket.receive(receivePacket);
	  //lay du lieu tu packet nhan duoc
	  String str = new String(receivePacket.getData());
	  System.out.println(str);
	  clientSocket.close();
  }
}
