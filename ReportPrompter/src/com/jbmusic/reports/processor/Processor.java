package com.jbmusic.reports.processor;

import java.util.Arrays;

import com.jbmusic.reports.App;
import com.jbmusic.reports.ReportViewerFrame;
import com.jbmusic.reports.data.UdpData;
import com.jbmusic.reports.protocol.PrintRequest;

public class Processor extends com.mayforever.thread.BaseThread
{
	public Processor() {
		// TODO Auto-generated constructor stub
		
	}
	
	public void startProcessor(){
		this.startThread();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(this.getServiceState()==com.mayforever.thread.state.ServiceState.RUNNING){
            try {
                UdpData data = App.udpdata.get();
                if (data != null){
                   // App.udpdata.add(data);
                	try{
                    	System.out.println(Arrays.toString(data.getData()));
                    	System.out.println(data.getData().length);
                    	PrintRequest printRequest = new PrintRequest();
                    	printRequest.fromBytes(data.getData());
                    	
                    	System.out.println(printRequest.getPath());
                    	System.out.println(Arrays.toString(printRequest.getParam()));
                    	System.out.println(Arrays.toString(printRequest.getParamMeta()));
                    	// ReportViewerFrame.showViewer(printRequest.getPath(), printRequest.getParamMeta(), printRequest.getParam());
                    	ReportViewerFrame viewerFrame = new ReportViewerFrame(printRequest.getPath(), printRequest.getParamMeta(), printRequest.getParam());
                    	viewerFrame.startReport();
                	}catch(Exception e){
                		System.out.println(e.toString());
                		e.printStackTrace();
                	}
                }
            } catch (InterruptedException ex) {
            	
            } 
        }
	}
	
}
