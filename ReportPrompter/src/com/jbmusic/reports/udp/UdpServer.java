package com.jbmusic.reports.udp;

import java.net.InetSocketAddress;

import com.jbmusic.reports.App;
import com.jbmusic.reports.data.UdpData;
	// inherit BaseThread class
	// implements udpServerListener to the class
public class UdpServer extends com.mayforever.thread.BaseThread implements 
		com.mayforever.network.udp.ServerListener{
	// declaring udp server
	public com.mayforever.network.udp.UDPServer udpServer = null;
	// declaring queue
	public com.mayforever.queue.Queue<UdpData> udpData = null;
	
	public UdpServer(){
		try{
			// initializing udp server
			udpServer = new com.mayforever.network.udp.UDPServer(15000);
			// add listener to udp server and the listener is this class
			udpServer.addListener(this);
			// initializing queue
			udpData = new com.mayforever.queue.Queue<UdpData>();
		}catch(Exception e){
			System.exit(0);
		}		
	}
	
	public void startUdpServer(){
		// starting the base thread
		this.startThread();
	}
	
	@Override
	public void errorDatagram(Exception e) {
		e.printStackTrace();
		// TODO Auto-generated method stub
	}

	@Override
	public void recievePacket(byte[] data, InetSocketAddress isa) {
		// inserting udp data to the queue
		udpData.add(new UdpData(isa, data));
		// System.out.println(isa + " : " + data);
	}

	@Override
	public void run() {
		// need to sure that the service thread is running
		while(this.getServiceState()==com.mayforever.thread.state.ServiceState.RUNNING){
            try {
            	// getting data to queue
                UdpData data = udpData.get();
                if (data != null){
                	// do something to process the data
                   App.udpdata.add(data);
                }
            } catch (InterruptedException ex) {
            	
            } 
        }
	}
}
