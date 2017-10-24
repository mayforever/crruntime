package com.jbmusic.reports.data;

import java.net.InetSocketAddress;

public class UdpData {
	private InetSocketAddress isa = null;


	private byte[] data = null;
	
	public UdpData(InetSocketAddress isa, byte[] data){
		this.isa = isa;
		this.data = data;
	}
	
	public InetSocketAddress getIsa() {
		return isa;
	}

	public byte[] getData() {
		return data;
	}
}
