/*
 * This sample code is an example of how to use the Business Objects APIs. 
 * Because the sample code is designed for demonstration only, it is 
 * unsupported.  You are free to modify and distribute the sample code as needed.   
 */
package com.jbmusic.reports;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.crystaldecisions.ReportViewer.ReportViewerBean;
import com.crystaldecisions.sdk.occa.report.application.OpenReportOptions;
import com.crystaldecisions.sdk.occa.report.application.ReportClientDocument;
import com.crystaldecisions.sdk.occa.report.lib.ReportSDKException;

/**
 * A sample report viewer class which can load a report and display it in 
 * a ReportViewerBean embedded in a JFrame.
 */
@SuppressWarnings("serial")
public class ReportViewerFrame extends JFrame
{
	
	// the following code is edit by aaron15
	public static String[] paramMeta;
	public static String[] param;
	
	
    // public static final String FRAME_TITLE = "Sample Report Viewer";

    /** The report viewer bean instance. */ 
    private final ReportViewerBean reportViewer;

    /** The ReportClientDocument instance being used. 
     *  Set by loadReport(). */
    private ReportClientDocument reportClientDocument;
    
    /**
     * Private constructor for this class.
     * Call showViewer() to obtain instances.
     */
    
    private static String reportUrl_ = null;
    
    public ReportViewerFrame(String reportUrl, String[] meta, String[] parameter) {
        
    	reportUrl_ = reportUrl;
    	paramMeta = meta;
    	param = parameter;
    	
        setTitle ("Report");
        
        this.reportViewer = new ReportViewerBean();
        reportViewer.init ();

        // A menu bar can be added here if desired
        
        // Handle closing of the viewer.
        addWindowListener (new WindowAdapter ()
        {
            public void windowClosing (WindowEvent e)
            {
                closeViewer ();
            }
        });
        
        getContentPane ().add (reportViewer, BorderLayout.CENTER);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        // Show in a sensible location for the platform.
        setLocationByPlatform (true);
        
//        reportViewer.start();
//        
//        boolean success = this.showReport ();
//        if (!success) {
//            this.closeViewer ();
//        }
    }
    
    public boolean startReport(){
    	this.setVisible(true);
    	
    	reportViewer.start();
    	boolean success = this.showReport ();
        if (!success) {
            this.closeViewer ();
        }
        return success;
    }
    
    /**
     * Create a new instance of this viewer class and show it.
     *
     * @return the new instance of this class that was created.
     */
//    private ReportViewerFrame showViewerFrame () 
//    {
//        ReportViewerFrame viewerFrame = new ReportViewerFrame();
//        viewerFrame.setVisible (true);
//        
//        // Start the viewer
//        viewerFrame.reportViewer.start ();
//        
//        return viewerFrame;
//    }

    /**
     * Entry point for this class.
     * Create and show the report viewer frame, then bind a report to it so that it can be viewed. 
     */
//    public void showViewer (String reportUrl, String[] meta, String[] parameter) 
//    {
//    	
//        // ReportViewerFrame viewerFrame = showViewerFrame ();
//       
//    }
    
    /**
     * Close the viewer.
     */
    private void closeViewer ()
    {
        if (reportViewer != null)
        {
            reportViewer.stop ();
            reportViewer.destroy ();
        }
        
        removeAll ();
        dispose ();
    }
    
    /**
     * Load a report and show it in the viewer.
     * @return whether a report was successfully displayed.
     */
    private boolean showReport () 
    {
        try
        {
            loadReport ();

            if (reportClientDocument != null) {
                setDatabaseLogon ();
                setParameterFieldValues ();
                setReportSource ();
                
                return true;
            }
        }
        catch (ReportSDKException e)
        {
            String localizedMessage = e.getLocalizedMessage ();
            int errorCode = e.errorCode ();
            
            String title = "Problem showing report";
            String message = localizedMessage + "\nError code: " + errorCode;
            JOptionPane.showMessageDialog (ReportViewerFrame.this, message, title, JOptionPane.WARNING_MESSAGE);
        }
        return false;
    }
    
    /**
     * Determine which report to display, and use this to set the reportClientDocument field.
     * @throws ReportSDKException if there is a problem opening the specified document. 
     */
    private void loadReport () throws ReportSDKException
    {
    	String reportFilePath = reportUrl_;
    	reportClientDocument = new ReportClientDocument ();
    	reportClientDocument.setReportAppServer(ReportClientDocument.inprocConnectionString);
    	reportClientDocument.open (reportFilePath, OpenReportOptions._openAsReadOnly);
    }
    
    
    /**
     * Set the database logon associated with the report document.
     * @throws ReportSDKException if there is a problem setting the database logon. 
     */
    private void setDatabaseLogon () throws ReportSDKException
    {
//    	CRJavaHelper.changeDataSource(reportClientDocument, null, null, "jdbc:sqlserver://192.168.0.1:1433;databaseName=PSEDB;user=an2ny;password=reset08", "com.microsoft.sqlserver.jdbc.SQLServerDriver", "java:/dada");
//    	System.out.println("dadan");
    	// CRJavaHelper cRJavaHelper = new CRJavaHelper();

//
        // TODO Set up database logon here to have the report log onto the
    	// data sources defined in the report automatically, without prompting the
    	// user.  For more information about this feature, refer to the documentation.
    	// For example:
    	//  CRJavaHelper.logonDataSource (reportClientDocument, "username", "password");
    	// will log onto the data sources defined in the report with username "username"
    	// and password "password".
    }
    
    /**
     * Set values for parameter fields in the report document.
     * @throws ReportSDKException if there is a problem setting parameter field values. 
     */
    private void setParameterFieldValues () throws ReportSDKException
    {
    	
    	for(int i = 0; i < paramMeta.length; i++){
    		CRJavaHelper.addDiscreteParameterValue(reportClientDocument, "", paramMeta[i], param[i]);
    	}
//    	CRJavaHelper.addDiscreteParameterValue(reportClientDocument, "", "pLess", "");
//    	CRJavaHelper.addDiscreteParameterValue(reportClientDocument, "", "pStore", "");
//    	CRJavaHelper.addDiscreteParameterValue(reportClientDocument, "", "pDateStart", "");
//    	CRJavaHelper.addDiscreteParameterValue(reportClientDocument, "", "pDateEnd", "");
    	// CRJavaHelper.addDiscreteParameterValue(reportClientDocument, "", "names", "bry");
    	// TODO Populate the parameter fields in the report document here, to
    	// view the report without prompting the user for parameter values.  For
    	// more information about this feature, refer to the documentation.
    	// For example:
        //  CRJavaHelper.addDiscreteParameterValue(reportClientDocument, "subreportName", 
    	//			"parameterName", newValue);
    	// will populate the discrete parameter "parameterName" under the subreport
    	// "subreportName" with a new value (newValue).  To set a parameter in the main
    	// report, use an empty string ("") instead of "subreportName".
    }
    
    public void addParameter (Map<String, String> paramMap) throws ReportSDKException
    {
    	
    }
    
    /**
     * Bind the report document to the report viewer so that the report is displayed.
     */
    private void setReportSource ()
    {
        reportViewer.setReportSource (reportClientDocument.getReportSource ());
    }
}
