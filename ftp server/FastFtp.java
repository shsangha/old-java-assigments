
/**
 * FastFtp Class
 *
 */

import cpsc441.a3.shared.*;
import java.util.*;
import java.net.*;
import java.io.*;

public class FastFtp {


	int time;
	String server;
	int remotePort;
	int localPort;
	String fileName;
	DatagramSocket udpSock=null;
	Socket socket=null;
	Timer timer;
	TxQueue window;
	File file;
	int maxPayload = 1000;
	InetAddress IPAddress=null;



	/**
     * Constructor to initialize the program 
     * 
     * @param windowSize	Size of the window for Go-Back_N in terms of segments
     * @param rtoTimer		The time-out interval for the retransmission timer
     */
	public FastFtp(int windowSize, int rtoTimer) {
		// to be completed
		time= rtoTimer;
		window=new TxQueue(windowSize);
	}
	

    /**
     * Sends the specified file to the specified destination host:
     * 1. send file/connection infor over TCP
     * 2. start receving thread to process coming ACKs
     * 3. send file segment by segment
     * 4. wait until transmit queue is empty, i.e., all segments are ACKed
     * 5. clean up (cancel timer, interrupt receving thread, close sockets/files)
     * 
     * @param serverName	Name of the remote server
     * @param serverPort	Port number of the remote server
     * @param fileName		Name of the file to be trasferred to the rmeote server
     */
	public void send(String serverName, int serverPort, String fileName) {
		// to be completed
		try{
			socket = new Socket(serverName,serverPort);
			IPAddress = InetAddress.getByName(serverName);
			udpSock = new DatagramSocket();
			DataOutputStream os = new DataOutputStream(socket.getOutputStream());
			DataInputStream is = new DataInputStream(socket.getInputStream());
			
			//Get the file
			file = new File(fileName);
			//System.out.println(fileName);
			//System.out.println(file.exists());
			//System.out.println("this runs");

			//TODO MAKE sure the file actually exists in the directory

	
			//HANDSHAKE
			os.writeUTF(fileName);
			os.writeLong(file.length());
			os.writeInt(udpSock.getLocalPort());
			os.flush();

			remotePort = is.readInt();

			Reciever reciever = new Reciever(this,udpSock);
			reciever.start();


			//SENDING LOGIC 
			byte[] temp = new byte[maxPayload];
			Segment senderSeg;
			int read= 0;
			int seq=0;
			FileInputStream fs = new FileInputStream(file);
			while((read = fs.read(temp))!=-1)
			{
				//the case of the last segment
				if(read < maxPayload){
					byte[] smaller = new byte[read];
					System.arraycopy(temp,0,smaller,0,read);
					senderSeg = new Segment(seq,smaller);
					System.out.println("small segment");
				}
				//sending segments of normal max payload size
				else{
					senderSeg=new Segment(seq,temp);
					System.out.println("normal segment "+seq);
				}

				//waits while the window is full
				while(window.isFull()){

				}

				processSend(senderSeg);
				seq++;
				System.out.println("bottom of loop");
			}
			while(!window.isEmpty()){

			}
		
				

		}catch(Exception e){}
	


	}
	
	//METHOD TO PROCESS SEND
	public synchronized void processSend(Segment seg){
			try{
			byte[] segToByte = seg.getBytes();
			DatagramPacket sendPack = new DatagramPacket(segToByte,segToByte.length,IPAddress,remotePort);
			window.add(seg);
			
			udpSock.send(sendPack);
			
			if(window.size()==1){
				timer = new Timer();
				timer.schedule(new TimeoutHandler(this),time);
			}			
		
	
		}catch(Exception e){

		}
	}


	//removes elements from the window that we have acks for 
	public synchronized void processAck(Segment seg){
		if(!window.isEmpty()){
			if(seg.getSeqNum()>window.element().getSeqNum()){
				timer.cancel();
				boolean go = true;
				while(go){
					if(window.element()==null){
						go=false;
					}
					if(window.element().getSeqNum()<seg.getSeqNum()){
						try{
							window.remove();
						}catch(Exception e){
				
						}
					}else{
						go=false;
					}
				}
				//restarts the timer if we still have stuff in the window un-acked
				if(!window.isEmpty()){
					timer = new Timer();
					timer.schedule(new TimeoutHandler(this),time);
				}
			}
		}		

	}

	
	//resends all the packets in the window during a timeout 
	public synchronized void processTimeout(){
		Segment[] qSeg = window.toArray();
		for(int i=0;i<window.size();i++){
			byte[] segBytes = qSeg[i].getBytes();
			DatagramPacket sendPack = new DatagramPacket(segBytes,segBytes.length,IPAddress,remotePort);
		} 

	}


    /**
     * A simple test driver
     * 
     */
	public static void main(String[] args) {
		// all srguments should be provided
		// as described in the assignment description 
		if (args.length != 5) {
			System.out.println("incorrect usage, try again.");
			System.out.println("usage: FastFtp server port file window timeout");
			System.exit(1);
		}
		
		// parse the command line arguments
		// assume no errors
		String serverName = args[0];
		int serverPort = Integer.parseInt(args[1]);
		String fileName = args[2];
		int windowSize = Integer.parseInt(args[3]);
		int timeout = Integer.parseInt(args[4]);

		// send the file to server
		FastFtp ftp = new FastFtp(windowSize, timeout);
		System.out.printf("sending file \'%s\' to server...\n", fileName);
		ftp.send(serverName, serverPort, fileName);
		System.out.println("file transfer completed.");
	}
}

class Reciever extends Thread{

	DatagramSocket socket = null;
	FastFtp ftp = null;
	boolean run = true;
	public Reciever(FastFtp ftp,DatagramSocket socket){
		this.ftp = ftp;
		this.socket=socket;
	}

	//sets up a black datagram socket and recieve the segment number into it and then calls to process in the main thread
	public void run(){
		while(run){
			byte[] ackBytes = new byte[1000];
			try{
				DatagramPacket inPack = new DatagramPacket(ackBytes,ackBytes.length);
				socket.receive(inPack);
				ftp.processAck(new Segment(inPack));
			}catch(Exception e){}
		}
	}
}

//thread that handles the timeouts and then calls the process method in the main class
class TimeoutHandler extends TimerTask{
	FastFtp ftp= null;
	

	public TimeoutHandler(FastFtp ftp){
		this.ftp = ftp;
	}
	//calls the timeout method
	public void run(){
		ftp.processTimeout();
	}

}
