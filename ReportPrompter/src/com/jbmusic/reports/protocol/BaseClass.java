package com.jbmusic.reports.protocol;

public abstract class BaseClass implements Base{
	private byte identifier = 0;

	public byte getIdentifier() {
		return identifier;
	}

	public void setIdentifier(byte identifier) {
		this.identifier = identifier;
	}
}
