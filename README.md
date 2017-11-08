## Crystal Report Runtime

### this runtime can execute your giving report with parameter and parameter meta by sending udp data to the listening port 15000;

the following udp data is 

	identifier - 1 byte
	size of path - 4 byte or 1 int
	path - size of path byte
	size of all paramMeta - 4byte or 1 int
	paraMeta - size of all param
	size of all param - 4 byte or 1 int
	param - size of all param


paramMeta & param
	param/paramMeta + byte(0)[as seperator];

copy the PrintRequest.java to ur project for easyly use.
	package com.jbmusic.reports.protocol;

	import java.nio.ByteOrder;
	import java.util.ArrayList;

	public class PrintRequest extends BaseClass{
		private int pathSize;
		private String path;
		private int metaSize;
		private String[] paramMeta;
		private int paramSize;
		private String[] param;
		
		@Override
		public byte[] toBytes() {
			int index = 0;
			int paramSize_ = 0;
			for(String paramValue : param){
				paramSize_ += paramValue.length();
				paramSize_ += 1;
			}
			
			int metaSize_ = 0;
			for(String meta : paramMeta){
				metaSize_ += meta.length();
				metaSize_ += 1;
			}
			byte[] data = new byte[1 + 4 + 4 + 4 + path.length() + paramSize_ + metaSize_];
			data[index] = this.getIdentifier();
			index++;
			System.arraycopy(com.mayforever.tools.BitConverter.intToBytes(path.length(), ByteOrder.BIG_ENDIAN), 0, data, index, 4);
			index+=4;
			System.arraycopy(path.toString().getBytes(), 0, data, index, path.length());
			index+=path.length();
			
			System.arraycopy(com.mayforever.tools.BitConverter.intToBytes(metaSize_, ByteOrder.BIG_ENDIAN), 0, data, index, 4);
			index+=4;
			for(String meta : paramMeta){
				System.arraycopy(meta.toString().getBytes(), 0, data, index, meta.length());
				index+=meta.length();
				data[index] = (byte)0;
				index++;
			}
			
			System.arraycopy(com.mayforever.tools.BitConverter.intToBytes(paramSize_, ByteOrder.BIG_ENDIAN), 0, data, index, 4);
			index+=4;
			for(String paramValue : param){
				// paramSize+= paramValue.length();
				System.arraycopy(paramValue.toString().getBytes(), 0, data, index, paramValue.length());
				index+=paramValue.length();
				data[index] = (byte)0;
				index++;
			}
			
			return data;
		}

		@Override
		public void fromBytes(byte[] data) {
			// TODO Auto-generated method stub
			int index = 0;
			this.setIdentifier(data[index]);
			index++;
			pathSize = com.mayforever.tools.BitConverter.bytesToInt(data, index, ByteOrder.BIG_ENDIAN);
			index+=4;
			// System.arraycopy(data, index, this.path, 0, pathSize);
			this.setPath(new java.lang.String(data, index, pathSize));
			index+=pathSize;
			
			metaSize = com.mayforever.tools.BitConverter.bytesToInt(data, index, ByteOrder.BIG_ENDIAN);
			index+=4;
			ArrayList<String> alMeta = new ArrayList<String>();
			String meta = "";
			for(int i = 0;i < metaSize ;i++){
				if(data[index] == 0){
					alMeta.add(meta);
					meta = "";
				}else{
					meta += (char)data[index];
				}
				index++;
			}
			paramMeta = alMeta.toArray(new String[alMeta.size()]);
			
			paramSize = com.mayforever.tools.BitConverter.bytesToInt(data, index, ByteOrder.BIG_ENDIAN);
			index+=4;
			ArrayList<String> alParam = new ArrayList<String>();
			String parameter = "";
			for(int i = 0;i < paramSize ;i++){
				if(data[index] == 0){
					alParam.add(parameter);
					parameter = "";
				}else{
					parameter += (char)data[index];	
				}
				index++;
			}
			param = alParam.toArray(new String[alParam.size()]);
		}

		public String[] getParam() {
			return param;
		}

		public void setParam(String[] param) {
			this.param = param;
		}

		public int getParamSize() {
			return paramSize;
		}

		public void setParamSize(int paramSize) {
			this.paramSize = paramSize;
		}

		public String[] getParamMeta() {
			return paramMeta;
		}

		public void setParamMeta(String[] paramMeta) {
			this.paramMeta = paramMeta;
		}

		public int getMetaSize() {
			return metaSize;
		}

		public void setMetaSize(int metaSize) {
			this.metaSize = metaSize;
		}

		public String getPath() {
			return path;
		}

		public void setPath(String path) {
			this.path = path;
		}

		public int getPathSize() {
			return pathSize;
		}

		public void setPathSize(int pathSize) {
			this.pathSize = pathSize;
		}	
	}


to use PrintRequest. 

	String paramMeta[] = {"paramMeta 1","paramMeta 2","paramMeta 3"};
	String param[]= {"param 1","param 2","param 3"};
	PrintRequest printRequest = new PrintRequest();
    printRequest.setIdentifier((byte)0);
    printRequest.setPath("path of file");
    printRequest.setParam(param);
    printRequest.setParamMeta(paramMeta);

then send udp data to 15000

its good to make a shorcut jar of prompter and copy it to start up.

see https://github.com/mayforever/JavaTools for other lib files