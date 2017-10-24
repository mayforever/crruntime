package com.jbmusic.reports;

import com.jbmusic.reports.data.UdpData;
import com.jbmusic.reports.processor.Processor;

public class App {
	
	public static com.mayforever.queue.Queue<UdpData> udpdata = null;
	public static com.jbmusic.reports.udp.UdpServer udpServer = null;
	
	// public static com.mayforever.queue.Queue<UdpData>
	
	public static void main(String[] args){
		udpdata = new com.mayforever.queue.Queue<UdpData>();
		
		udpServer = new com.jbmusic.reports.udp.UdpServer();
		udpServer.startUdpServer();
		
		Processor[] processors = new Processor[5];
		
		for(Processor processor : processors){
			processor = new Processor();
			processor.startProcessor();
		}
		
	}
}
