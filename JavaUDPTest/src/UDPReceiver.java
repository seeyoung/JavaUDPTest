import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class UDPReceiver {
	public static void main(String args[]) throws Exception {
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(
				System.in));
		String sentence = inFromUser.readLine();

		DatagramSocket receiverSocket = new DatagramSocket(11080);
		getMySocketInfo(sentence, receiverSocket);
		receiverSocket.close();
	}

	public static void getMySocketInfo(String sentence,
			DatagramSocket receiverSocket) throws UnknownHostException,
			IOException {
		InetAddress IPAddress = InetAddress.getByName("infocept.kr");
		byte[] receiveData = new byte[1024];
		byte[] sendData = new byte[1024];
		sendData = sentence.getBytes();
		DatagramPacket sendPacket = new DatagramPacket(sendData,
				sendData.length, IPAddress, 9876);
		receiverSocket.send(sendPacket);
		DatagramPacket receivePacket = new DatagramPacket(receiveData,
				receiveData.length);
		receiverSocket.receive(receivePacket);
		String modifiedSentence = new String(receivePacket.getData());
		System.out.println("FROM SERVER:" + modifiedSentence);
	}

}
