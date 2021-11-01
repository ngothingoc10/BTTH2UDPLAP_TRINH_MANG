package UDPTinh;

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
public class UDPTinhClient extends JFrame implements ActionListener {
	JTextArea txtgui,txtnhan;
	JButton send, reset, exit;
	JPanel pn, pn1, pn2, pn3;
	
	public void GUI() {
		
		txtgui=new JTextArea(2,20);
		txtnhan=new JTextArea(5,20);
		txtnhan.setEditable(false);

		send = new JButton("Send");
		reset = new JButton("Reset");
		exit = new JButton("Exit");
		
		send.addActionListener(this);
		reset.addActionListener(this);
		exit.addActionListener(this);

		
		pn = new JPanel(new GridLayout(3, 1));
		pn1 = new JPanel(new FlowLayout());
		pn2 = new JPanel(new FlowLayout());
		pn3 = new JPanel(new FlowLayout());
		pn1.add(txtgui);
		pn2.add(send);
		pn2.add(reset);
		pn2.add(exit);

		pn3.add(txtnhan);
		pn.add(pn1);
		pn.add(pn2);
		pn.add(pn3);// add vao frame
		add(pn);
		setSize(400, 400);// thiet lap kich thuwoc
		setVisible(true);// hien thi

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
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
				clientSocket.close(); // chay xong phai dong ket noi
				
			} catch (Exception e2) {
			}
		}
		if (e.getSource()== exit) {
			System.exit(0);
		}
		if (e.getSource()== reset) {
			txtgui.setText(" ");
			txtnhan.setText(" ");
		}
		

		
	}
	
	public UDPTinhClient(String st) {
		super(st);
		GUI();

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new UDPTinhClient("Tinh!!!");

	}

	

}
