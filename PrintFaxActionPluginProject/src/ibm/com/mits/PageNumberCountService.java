package ibm.com.mits;

import java.io.PrintWriter;
import java.util.Iterator;
import java.io.InputStream;



import javax.security.auth.Subject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.filenet.api.collection.ContentElementList;
import com.filenet.api.core.ContentTransfer;
import com.filenet.api.core.Document;
import com.filenet.api.core.Factory;
import com.filenet.api.core.ObjectStore;
import com.filenet.api.util.UserContext;
import com.ibm.ecm.extension.PluginService;
import com.ibm.ecm.extension.PluginServiceCallbacks;
import com.ibm.json.java.JSONArray;
import com.ibm.json.java.JSONObject;
import com.sun.media.jai.codec.ImageCodec;
import com.sun.media.jai.codec.ImageDecoder;
//import com.sun.media.jai.codec.ImageDecoder;



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
public class PageNumberCountService  extends PluginService {

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
		return "PageNumberCount";
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
	public void execute(PluginServiceCallbacks callbacks,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		PrintWriter out = null;
		InputStream inputStream=null;
		JSONArray jsonArray = new JSONArray();
		try{



			int numPages=0;

			Subject subject = null;
			UserContext userContext = null;
			Document document = null;

			String documentID=request.getParameter("documentid");		

			String repositoryId=request.getParameter("repositoryId");

			System.out.println("repositoryId"+repositoryId);
			System.out.println("documentID"+documentID);

			subject =	callbacks.getP8Subject(repositoryId);
			userContext = UserContext.get();
			userContext.pushSubject(subject);
		

			ObjectStore p8ObjectStore  = callbacks.getP8ObjectStore(repositoryId);
			
			//tiffcounterWayTwo(p8ObjectStore,documentID);
			
			System.out.println("p8ObjectStore"+p8ObjectStore);

			document = Factory.Document.fetchInstance(p8ObjectStore,documentID,null);
			
			System.out.println("document"+document);

			System.out.println("in service with11111111111");

			document.refresh();
			ContentElementList cel = document.get_ContentElements();
			// Iterate and get ContentTransfer Object
			Iterator iterator = cel.iterator();
			int count = 0;
			while (iterator.hasNext()) {
				count++;

				ContentTransfer ct = (ContentTransfer) iterator.next();
				String retrivalName = ct.get_RetrievalName();
				System.out.println("Retrivel Name:" + retrivalName);


				inputStream = ct.accessContentStream();

			}




			//inputStream = document.accessContentStream(0);

			System.out.println("");

			
			

			System.out.println("in service with22222222222222222");

			ImageDecoder decoder = ImageCodec.createImageDecoder("tiff", inputStream, null);

			System.out.println("in service with3333333333333333");

			out = response.getWriter();
			numPages = decoder.getNumPages();

		JSONObject returnPagecount = new JSONObject();

			returnPagecount.put("docPagecount",numPages );


			jsonArray.add(returnPagecount); 

			out.write("{\"result\":"+jsonArray+"}");

			//out.write(returnPagecount);

		}catch(Exception exe){

			out.print("Exception Response from Server"+exe.getMessage());

		}






	}
	
	//------------------------------------------------------------------------------------------------------
	
	//tiffcounterWayTwo
	/* public void tiffcounterWayTwo(ObjectStore p8ObjectStore,String documentID){
		 
		 System.out.println("p8ObjectStore"+p8ObjectStore);
		 
		 System.out.println("documentID"+documentID);
		 
	  InputStream inputStream=null;
	  Document selectedDocument = Factory.Document.fetchInstance(p8ObjectStore,documentID, null);
	  ContentElementList cel = selectedDocument.get_ContentElements();
	  // Iterate and get ContentTransfer Object
	  Iterator iterator = cel.iterator();
	  int count = 0;
	  while (iterator.hasNext()) {
	   count++;

	   ContentTransfer ct = (ContentTransfer) iterator.next();
	   String retrivalName = ct.get_RetrievalName();
	   System.out.println("Retrivel Name:" + retrivalName);


	   inputStream = ct.accessContentStream();

	  }//close of while
	  try{
	   ImageDecoder decoder = ImageCodec.createImageDecoder("tiff", inputStream, null);
	   int numPages = decoder.getNumPages();
	   System.out.println("++++++++++++++++++++++++++++++++++\t"+numPages);
	  }catch(Exception e){
	   e.printStackTrace();
	  }
	 
	 }// close tiffcounterWayTwo
*/	//-------------------------------------------------------------------------------------------------------
	
	
	
}
