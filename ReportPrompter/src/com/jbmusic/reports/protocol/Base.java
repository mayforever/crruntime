package com.jbmusic.reports.protocol;

public interface Base {
	public byte[] toBytes();
	public void fromBytes(byte[] data);
}
