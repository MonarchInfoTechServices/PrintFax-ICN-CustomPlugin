package ibm.com.plugin;

import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.security.auth.Subject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.filenet.api.collection.ContentElementList;
import com.filenet.api.collection.StringList;
import com.filenet.api.core.ContentTransfer;
import com.filenet.api.core.Document;
import com.filenet.api.core.Factory;
import com.filenet.api.core.ObjectStore;
import com.filenet.api.util.UserContext;
import com.ibm.ecm.extension.PluginService;
import com.ibm.ecm.extension.PluginServiceCallbacks;
import com.ibm.json.java.JSONArray;
import com.ibm.json.java.JSONObject;

import ibm.com.dao.PrintDeviceDAOImpl;

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
public class ContentElementSizeService  extends PluginService {

	private int size;

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
		return "ContentElementSizeService";
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
	@SuppressWarnings("null")
	public void execute(PluginServiceCallbacks callbacks,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

	
		 final Logger LOGGER = Logger.getLogger(ContentElementSizeService.class);
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObj = null;
		
		Subject subject = null;
		UserContext userContext = null;
		Document document = null;
		
		String DocumentID=null;
		try{
		String documentIDForTiff=request.getParameter("tiffdocids");		
		JSONArray documentsIDTiffValues = JSONArray.parse(documentIDForTiff);
		String repositoryId=request.getParameter("repositoryIdfortiff");
		subject =	callbacks.getP8Subject(repositoryId);
		userContext = UserContext.get();
		userContext.pushSubject(subject);
		ObjectStore p8ObjectStore  = callbacks.getP8ObjectStore(repositoryId);
		for (int i=0; i<documentsIDTiffValues.size(); i++) {
		DocumentID=documentsIDTiffValues.get(i).toString();
		LOGGER.info("DocumentID in ContentElementSizeService class"+DocumentID);
		document = Factory.Document.fetchInstance(p8ObjectStore,documentsIDTiffValues.get(i).toString(),null);
		document.refresh();
         StringList cel = document.get_ContentElementsPresent();
		size = cel.size();
		LOGGER.info("size of document in ContentElementSizeService class"+size);
		jsonObj = new JSONObject();
		jsonObj.put("name", DocumentID);
		jsonObj.put("id", cel.size());
		jsonArray.add(jsonObj);
		
		}
		
		
		PrintWriter responseWriter =  response.getWriter();
		responseWriter.write("{\"result\":"+jsonArray+"}");
	
		responseWriter.flush();
		
		
		
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Connection error ocurred" + e);
		}
		
	}
}
