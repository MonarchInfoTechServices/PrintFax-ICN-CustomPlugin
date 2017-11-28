package ibm.com.plugin;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.ecm.extension.PluginService;
import com.ibm.ecm.extension.PluginServiceCallbacks;
import com.ibm.json.java.JSONArray;
import com.ibm.json.java.JSONObject;

import ibm.com.dao.PrintDeviceDAO;
import ibm.com.dao.PrintDeviceDAOImpl;
import ibm.com.pojo.DocValues;
import ibm.com.pojo.FaxValues;
import ibm.com.pojo.PrintValues;
import ibm.com.pojo.RequestValues;
import ibm.com.xml.PFS;
import ibm.com.xml.PrintFaxRequestType;

/**
 * Provides an abstract class that is extended to create a class implementing
 * each service provided by the plug-in. Services are actions, similar to
 * servlets or Struts actions, that perform operations on the IBM Content
 * Navigator server. A service can access content server application programming
 * interfaces (APIs) and Java EE APIs.
 * <p>
 * Services are invoked from the JavaScript functions that are defined for the
 * plug-in by using the <code>ecm.model.Request.invokePluginService</code>
 * function.
 * </p>
 * Follow best practices for servlets when implementing an IBM Content Navigator
 * plug-in service. In particular, always assume multi-threaded use and do not
 * keep unshared information in instance variables.
 */
public class PrintFaxValuesServicePlugin extends PluginService {

	/**
	 * Returns the unique identifier for this service.
	 * <p>
	 * <strong>Important:</strong> This identifier is used in URLs so it must
	 * contain only alphanumeric characters.
	 * </p>
	 * 
	 * @return A <code>String</code> that is used to identify the service.
	 */
	public String getId() {
		return "PrintFaxvaluesservicePlugin";
	}

	/**
	 * Returns the name of the IBM Content Navigator service that this service
	 * overrides. If this service does not override an IBM Content Navigator
	 * service, this method returns <code>null</code>.
	 * 
	 * @returns The name of the service.
	 */
	public String getOverriddenService() {
		return null;
	}

	/**
	 * Performs the action of this service.
	 * 
	 * @param callbacks
	 *            An instance of the <code>PluginServiceCallbacks</code> class
	 *            that contains several functions that can be used by the
	 *            service. These functions provide access to the plug-in
	 *            configuration and content server APIs.
	 * @param request
	 *            The <code>HttpServletRequest</code> object that provides the
	 *            request. The service can access the invocation parameters from
	 *            the request.
	 * @param response
	 *            The <code>HttpServletResponse</code> object that is generated
	 *            by the service. The service can get the output stream and
	 *            write the response. The response must be in JSON format.
	 * @throws Exception
	 *             For exceptions that occur when the service is running. If the
	 *             logging level is high enough to log errors, information about
	 *             the exception is logged by IBM Content Navigator.
	 */
	public void execute(PluginServiceCallbacks callbacks, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		System.out.println("in service");
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObj = new JSONObject();
		String result = "Failed";

		try {

			/* *********** Geeting values ************** */

			String repositoryId = request.getParameter("repositoryId");
			String userId = request.getParameter("userId");
			String printervalue = request.getParameter("printervalue");
			String printfaxname = request.getParameter("printfaxname");
			String papersize = request.getParameter("papersize");
			String annotation = request.getParameter("annotation");
			String ejecttrayvalue = request.getParameter("ejecttrayvalue");
			String orienatationvalue = request.getParameter("Orienatationvalue");
			String scalingvalue = request.getParameter("scalingvalue");
			String copiesvalue = request.getParameter("copiesvalue");
			String priorityvalue = request.getParameter("priorityvalue");
			String printtimevalue = request.getParameter("printtimevalue");
			String collatevalue = request.getParameter("collatevalue");
			String coverPagevalue = request.getParameter("CoverPagevalue");
			String qualityvalue = request.getParameter("qualityvalue");
			String faxttovalue = request.getParameter("faxttovalue");
			String tovalue = request.getParameter("tovalue");
			String faxfromvalue = request.getParameter("faxfromvalue");
			String phonenovalue = request.getParameter("phonenovalue");
			String companyvalue = request.getParameter("companyvalue");
			String faxnovalue = request.getParameter("faxnovalue");
			String notesvalue = request.getParameter("notesvalue");
			String documentsarray = request.getParameter("documentsarray");
			String startpagevalues = request.getParameter("startpagevalues");
			String documentsidarray = request.getParameter("documentsidarray");
			String mimetypearray = request.getParameter("mimetypearray");
			String lastpagevalues = request.getParameter("lastpagevalues");

			JSONArray documentsarrayvalues = JSONArray.parse(documentsarray);
			JSONArray startpagevaluesarray = JSONArray.parse(startpagevalues);
			JSONArray documentsidarraylist = JSONArray.parse(documentsidarray);
			JSONArray mimetypearraylist = JSONArray.parse(mimetypearray);
			JSONArray lastpagevaluesarray = JSONArray.parse(lastpagevalues);

			System.out.println("lastpagevaluesarray" + lastpagevaluesarray);

			/*
			 * *********** Adding Values to printvlaues pojo class
			 * **************
			 */

			PrintValues printValues = new PrintValues();

			printValues.setPrintervalue(printervalue);
			printValues.setPrintfaxname(printfaxname);
			printValues.setPapersize(papersize);
			printValues.setAnnotation(annotation);
			printValues.setEjecttrayvalue(ejecttrayvalue);
			printValues.setOrienatationvalue(orienatationvalue);
			printValues.setScalingvalue(scalingvalue);
			printValues.setCopiesvalue(copiesvalue);
			printValues.setPriorityvalue(priorityvalue);
			printValues.setCollatevalue(collatevalue);
			printValues.setPrinttimevalue(printtimevalue);
			printValues.setUserId(userId);

			/*
			 * *********** Adding Values to faxvalues pojo class **************
			 */

			FaxValues faxValues = new FaxValues();

			faxValues.setPrintfaxname(printfaxname);
			faxValues.setPapersize(papersize);
			faxValues.setAnnotation(annotation);
			faxValues.setCoverPagevalue(coverPagevalue);
			faxValues.setQualityvalue(qualityvalue);
			faxValues.setFaxfromvalue(faxfromvalue);
			faxValues.setFaxttovalue(faxttovalue);
			faxValues.setTovalue(tovalue);
			faxValues.setPhonenovalue(phonenovalue);
			faxValues.setCompanyvalue(companyvalue);
			faxValues.setFaxnovalue(faxnovalue);
			faxValues.setNotesvalue(notesvalue);

			/*
			 * *********** Adding Values to docvalues pojo class **************
			 */

			DocValues docValues = new DocValues();

			docValues.setDocumentsidarraylist(documentsidarraylist);
			docValues.setDocumentsarrayvalues(documentsarrayvalues);
			docValues.setLastpagevaluesarray(lastpagevaluesarray);
			docValues.setStartpagevaluesarray(startpagevaluesarray);
			docValues.setMimetypearraylist(mimetypearraylist);

			RequestValues requestValues = new RequestValues();

			requestValues.setUserId(userId);

			PrintDeviceDAO dao = new PrintDeviceDAOImpl();

			String libraryname = dao.libraryNamesRetrieval();

			docValues.setLibraryname(libraryname);

			PrintFaxRequestType PrintFaxRequestxmltype = new PrintFaxRequestType();

			PFS pfs = PrintFaxRequestxmltype.printvalues(printValues, faxValues, docValues);

			StringWriter writer = PrintFaxRequestxmltype.createXML(pfs);

			int valueafterinsertion = dao.printRequestInsertion(writer, docValues);

			jsonObj.put("printrequestinsertionvalue", valueafterinsertion);
			// result = "success";
			jsonArray.add(jsonObj);

			PrintWriter responseWriter = response.getWriter();
			responseWriter.write("{\"result\":" + jsonArray + "}");
			responseWriter.flush();
		} catch (Exception e) {
			e.printStackTrace();

		}

	}
}
