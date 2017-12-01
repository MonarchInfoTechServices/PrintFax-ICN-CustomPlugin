package ibm.com.xml;

import java.io.StringWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.ibm.json.java.JSONArray;

import ibm.com.pojo.DocValues;
import ibm.com.pojo.FaxValues;
import ibm.com.pojo.PrintValues;




public class PrintFaxRequestType {
	PFS pfs=null;
	
	
	static ResourceBundle resource=null;
	static final Logger LOGGER = Logger.getLogger(PrintFaxRequestType.class);
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public PFS printvalues(PrintValues printValues, FaxValues faxValues, DocValues docValues) throws SQLException
			 {

		 resource = ResourceBundle.getBundle("ibm.com.properties.XMLPropertyFile");
		try {
		
			pfs = new PFS();

		Request req = new Request();

		if ("".equalsIgnoreCase(printValues.getPrintervalue())) {

			req.setAction("");

		} else {
			req.setAction(printValues.getPrintervalue());
		}

		if ("".equalsIgnoreCase(printValues.getUserId())) {

			req.setOwner("");

		} else {
			req.setOwner(printValues.getUserId());
		}
		req.setSystem(resource.getString("Filenet"));

		FAX fax = new FAX();
		LOGGER.info(" faxvalues:::::::::::::" + faxValues.toString());
		
		fax.setFaxName(faxValues.getPrintfaxname());
		Map faxMap = new HashMap();

		if ("".equalsIgnoreCase(faxValues.getTovalue())) {

			faxMap.put(resource.getString("ToName"), "");
		} else {

			faxMap.put(resource.getString("ToName"), faxValues.getTovalue());
		}

		if ("".equalsIgnoreCase(faxValues.getCompanyvalue())) {

			faxMap.put(resource.getString("ToCompany"), "");
		} else {

			faxMap.put(resource.getString("ToCompany"), faxValues.getCompanyvalue());
		}

		if ("".equalsIgnoreCase(faxValues.getFaxttovalue())) {

			faxMap.put(resource.getString("ToFaxNumber"), "");
		} else {

			faxMap.put(resource.getString("ToFaxNumber"), faxValues.getFaxttovalue());
		}

		if ("".equalsIgnoreCase(faxValues.getPhonenovalue())) {

			faxMap.put(resource.getString("ToPhoneNumber"), "");
		} else {

			faxMap.put(resource.getString("ToPhoneNumber"), faxValues.getPhonenovalue());
		}

		if ("".equalsIgnoreCase(faxValues.getFaxfromvalue())) {

			faxMap.put(resource.getString("FromName"), "");
		} else {

			faxMap.put(resource.getString("FromName"), faxValues.getFaxfromvalue());
		}

		if ("".equalsIgnoreCase(faxValues.getFaxnovalue())) {

			faxMap.put(resource.getString("FromFaxNumber"), "");
		} else {

			faxMap.put(resource.getString("FromFaxNumber"), faxValues.getFaxnovalue());
		}

		faxMap.put(resource.getString("FromPhoneNumber"), "");
		if ("true".equalsIgnoreCase(faxValues.getCoverPagevalue())) {
			faxMap.put(resource.getString("CoverSheet"), "YES");
		} else {
			faxMap.put(resource.getString("CoverSheet"), "NO");
		}
		if ("true".equalsIgnoreCase(faxValues.getAnnotation())) {
			faxMap.put(resource.getString("Annotation"), "YES");
		} else {
			faxMap.put(resource.getString("Annotation"), "NO");
		}

		if ("".equalsIgnoreCase(faxValues.getNotesvalue())) {

			faxMap.put(resource.getString("CoverSheetNotes"), "");
		} else {

			faxMap.put(resource.getString("CoverSheetNotes"), faxValues.getNotesvalue());
		}

		faxMap.put(resource.getString("Priority"), "2");

		if ("".equalsIgnoreCase(faxValues.getQualityvalue())) {

			faxMap.put(resource.getString("Quality"), "");
		} else {

			faxMap.put(resource.getString("Quality"), faxValues.getQualityvalue());
		}

		fax.setNodeMap(faxMap);
		req.setFax(fax);

		Print print = new Print();
		
		LOGGER.info(" printValues:::::::::::::" + printValues.toString());
		
		

		print.setName(printValues.getPrintfaxname());
		Map printMap = new HashMap();

		printMap.put(resource.getString("Orientation"), printValues.getOrienatationvalue());
		
		if (printValues.getAnnotation().equalsIgnoreCase("true")) {

			printMap.put(resource.getString("Annotation"), "YES");
		} else {
			printMap.put(resource.getString("Annotation"), "NO");

		}

		printMap.put(resource.getString("PageSize"), printValues.getPapersize());
		printMap.put(resource.getString("PaperTray"), printValues.getEjecttrayvalue());
		printMap.put(resource.getString("NumberOfCopies"), printValues.getCopiesvalue());

		if ("true".equalsIgnoreCase(printValues.getCollatevalue())) {
			printMap.put(resource.getString("Collate"), "YES");
		} else {
			printMap.put(resource.getString("Collate"), "NO");
		}

		if ("low".equalsIgnoreCase(printValues.getPriorityvalue())) {
			printMap.put(resource.getString("Priority"), "3");
		}
		if ("High".equalsIgnoreCase(printValues.getPriorityvalue())) {
			printMap.put(resource.getString("Priority"), "1");
		} else {

			printMap.put(resource.getString("Priority"), "2");

		}

		printMap.put(resource.getString("CoverSheet"), "YES");
		printMap.put(resource.getString("Scaling"), printValues.getScalingvalue());

		if ("".equalsIgnoreCase(printValues.getPrinttimevalue())) {

			printMap.put(resource.getString("PrintTime"), "");
		} else {

			printMap.put(resource.getString("PrintTime"), printValues.getPrinttimevalue());

		}

		print.setNodeMap(printMap);
		req.setPrint(print);

		JSONArray documentsidarraylist = docValues.getDocumentsidarraylist();

		JSONArray lastpagevaluesArray = docValues.getLastpagevaluesarray();

		JSONArray startpagevalueAarray = docValues.getStartpagevaluesarray();

		LOGGER.info(" docValues:::::::::::::" + docValues.toString());
		List docList = new ArrayList();
	
		for (int i = 0; i < documentsidarraylist.size(); i++) {

			

			DocNode doc = new DocNode();

			doc.setId(documentsidarraylist.get(i).toString());
			Map docNodeMap = new HashMap();
			docNodeMap.put(resource.getString("Library"), docValues.getLibraryname());
			docNodeMap.put(resource.getString("MimeType"), docValues.getMimetypearraylist().get(i).toString());

			if ("1".equalsIgnoreCase(startpagevalueAarray.get(i).toString())
					&& "9999".equalsIgnoreCase(lastpagevaluesArray.get(i).toString())) {

				docNodeMap.put(resource.getString("PageRange"), "All");

			} else {
				docNodeMap.put(resource.getString("PageRange"), docValues.getStartpagevaluesarray().get(i).toString() + "-"
						+lastpagevaluesArray.get(i).toString());

			}
			doc.setNodeMap(docNodeMap);

			docList.add(doc);

		}

		req.setDocList(docList);
		pfs.setRequest(req);
	
			createXML(pfs);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.error("Exception ocurred" + e);
			
			throw new SQLException("Connection Failed"+e.getMessage());
		}
		return pfs;
		
		
	}

	
	public StringWriter createXML(PFS pfs)  {
		
		
		StringWriter writer=null;
		Transformer transformer=null;
		try {
		DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder;
	
			documentBuilder = documentFactory.newDocumentBuilder();
		
		Document document = documentBuilder.newDocument();

		Element request = writeRequest(pfs, document);

		if ("FAX".equalsIgnoreCase(pfs.getRequest().getAction()))
			writeFax(pfs, document, request);
		else if ("print".equalsIgnoreCase(pfs.getRequest().getAction()))
			writePrint(pfs, document, request);

		writeDocList(pfs, document, request);

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		
		
			transformer = transformerFactory.newTransformer();
		
		DOMSource domSource = new DOMSource(document);

		 writer = new StringWriter();
		StreamResult result = new StreamResult(writer);
		
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		transformer.transform(domSource, result);
		
		LOGGER.info("XML IN String format is: \n" + writer.toString());
	
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			LOGGER.error("ParserConfigurationException ocurred" + e);
		}
		 catch (TransformerException e) {
			// TODO Auto-generated catch block
			 LOGGER.error("TransformerException ocurred" + e);
		}
		return writer;
	}

	private static Element writeRequest(PFS pfs, Document document) {
		Element rootElement = document.createElement(resource.getString("PFS"));
		document.appendChild(rootElement);
		Element request = document.createElement(resource.getString("Request"));
		rootElement.appendChild(request);
		Attr system = document.createAttribute(resource.getString("System"));
		system.setValue(pfs.getRequest().getSystem());
		request.setAttributeNode(system);
		Attr action = document.createAttribute(resource.getString("Action"));
		action.setValue(pfs.getRequest().getAction());
		request.setAttributeNode(action);
		Attr owner = document.createAttribute(resource.getString("Owner"));
		owner.setValue(pfs.getRequest().getOwner());
		request.setAttributeNode(owner);
		return request;
	}

	private static void writeDocList(PFS pfs, Document document, Element request) {
		LOGGER.info("enter into the writeDocList");
		List<DocNode> docList = pfs.getRequest().getDocList();
		LOGGER.info("enter into the writeDocList" + pfs.getRequest().getDocList());
		for (Iterator iterator = docList.iterator(); iterator.hasNext();) {
			DocNode docNode = (DocNode) iterator.next();
			Element doc = document.createElement(resource.getString("Doc"));
			Attr docId = document.createAttribute(resource.getString("Id"));
			docId.setValue(docNode.getId());
			doc.setAttributeNode(docId);
			Map<String, String> docNodeMap = docNode.getNodeMap();
			setNodeValue(document, doc, docNodeMap);
			request.appendChild(doc);
		}

		LOGGER.info("left into the writeDocList");
	}

	private static void writeFax(PFS pfs, Document document, Element request) {
		Element fax = document.createElement(resource.getString("FAX"));
		Attr faxname = document.createAttribute(resource.getString("Name"));
		faxname.setValue(pfs.getRequest().getFax().getFaxName());
		fax.setAttributeNode(faxname);
		Map<String, String> nodeMap = pfs.getRequest().getFax().getNodeMap();
		setNodeValue(document, fax, nodeMap);
		request.appendChild(fax);
	}

	private static void writePrint(PFS pfs, Document document, Element request) {
		Element print = document.createElement(resource.getString("Print"));
		Attr printName = document.createAttribute(resource.getString("Name"));
		printName.setValue(pfs.getRequest().getPrint().getName());
		print.setAttributeNode(printName);
		Map<String, String> printNodeMap = pfs.getRequest().getPrint().getNodeMap();
		setNodeValue(document, print, printNodeMap);
		request.appendChild(print);
	}

	private static void setNodeValue(Document document, Element element, Map<String, String> nodeMap) {
		for (Map.Entry<String, String> entry : nodeMap.entrySet()) {
			Element node = document.createElement(entry.getKey());
			node.setTextContent(entry.getValue());
			element.appendChild(node);
			LOGGER.info(entry.getKey() + ":" + entry.getValue());
		}
	}

	public static void iterateUsingEntrySet(Map<String, Integer> map) {
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			LOGGER.info(entry.getKey() + ":" + entry.getValue());
		}
	}
}
