import java.io.*;
import java.net.*;

public class Receiver11081 implements Runnable {
	DatagramSocket socket = null;
	DatagramPacket packetOut = null;
	DatagramPacket packetIn = null;
	BufferedReader readFromKeyBoard = null;
	
	byte[] dataIn;
	byte[] dataOut;

	public Receiver11081() {
		try {
			readFromKeyBoard = new BufferedReader(new InputStreamReader(
					System.in));
			dataIn = new byte[1024];
			dataOut = new byte[1024];
			socket = new DatagramSocket(11081);

			UDPReceiver.getMySocketInfo("tst", socket);
			new Thread(this).start();

			//InetAddress inetAddress = InetAddress.getByName("203.249.17.66");
			InetAddress inetAddress = InetAddress.getLocalHost();
			while (true) {
				String message = readFromKeyBoard.readLine();
				dataIn = message.getBytes();
				packetOut = new DatagramPacket(dataIn, message.length(),
						inetAddress, 11082);
				socket.send(packetOut);
			}
		} catch (Exception exp) {
			exp.printStackTrace();
		}
	}

	public void run() {
		try {
			while (true) {
				packetIn = new DatagramPacket(dataOut, dataOut.length);
				socket.receive(packetIn);
				String message = new String(packetIn.getData(), 0,
						packetIn.getLength());
				System.out.println(message);
			}
		} catch (Exception exp) {
			exp.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Receiver11081();
	}
}