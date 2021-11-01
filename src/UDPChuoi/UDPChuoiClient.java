package UDPChuoi;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.event.*;
import java.awt.*;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPChuoiClient extends JFrame implements ActionListener {
	JTextArea txtgui,txtnhan;
	 JButton send;
	 JPanel pn, pn1, pn2, pn3;
	 PrintWriter pw = null;
	 BufferedReader br = null;
	 Socket socket;

	public void GUI() {
	
		txtgui=new JTextArea(2,20);
		txtnhan=new JTextArea(5,20);
		txtnhan.setEditable(false);

		send = new JButton("Send");
		send.addActionListener(this);
		pn = new JPanel(new GridLayout(3, 1));
		pn1 = new JPanel(new FlowLayout());
		pn2 = new JPanel(new FlowLayout());
		pn3 = new JPanel(new FlowLayout());
		pn1.add(txtgui);
		pn2.add(send);
		pn3.add(txtnhan);
		pn.add(pn1);
		pn.add(pn2);
		pn.add(pn3);// add vao frame
		add(pn);
		setSize(400, 400);// thiet lap kich thuwoc
		setVisible(true);// hien thi

	}

	public UDPChuoiClient(String st) {
		super(st);
		GUI();

	}

	//------------
	public void actionPerformed(ActionEvent e) {
			if (e.getSource()== send) {
				try {
					DatagramSocket clientSocket = new DatagramSocket(6998); 
					InetAddress iPAddress = InetAddress.getByName("localhost");
					
					byte[] sendData = new byte[1024];
					byte[] receiveData = new byte[1024];
					DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length); 
					sendData = txtgui.getText().getBytes("UTF-8"); 
					DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,iPAddress, 6997);
					clientSocket.send(sendPacket);
					while(true) {
						clientSocket.receive(receivePacket);
						String str = new String(receivePacket.getData(), 0, receivePacket.getLength());
						System.out.println( str); 
						txtnhan.setText(str); 
						break; 
					}
					clientSocket.close(); // chay xong phai dong ket noi// 1 lan nua tohi ne tuc qua
					
				} catch (Exception e2) {
				}
			}
	
			

	}

	public static void main(String[] args) throws Exception{
		new UDPChuoiClient("Chuoi Ky Tu");
//		DatagramSocket clientSocket = new DatagramSocket();
//		InetAddress iPAddress = InetAddress.getByName("localhost");
//		byte[] sendData = new byte[1024];
//		byte[] receiveData = new byte[1024];
//		System.out.println("Nhap chuoi: ");
//		Scanner sc = new Scanner(System.in);
//		sendData = sc.nextLine().getBytes();
//		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,iPAddress, 6997);
//		clientSocket.send(sendPacket);
//		
//		DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length,iPAddress, 6997);
//		clientSocket.receive(receivePacket);
//		String str = new String(receivePacket.getData());
//		System.out.println(str);
//		clientSocket.close();
		
		
		
		
	}

}
