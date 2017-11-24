package ibm.com.pojo;

import com.ibm.json.java.JSONArray;

public class DocValues {

	

	JSONArray documentsidarraylist;
	JSONArray documentsarrayvalues;
	JSONArray startpagevaluesarray;
	JSONArray mimetypearraylist;
	JSONArray lastpagevaluesarray ;
	
	String libraryname;
	public String getLibraryname() {
		return libraryname;
	}

	public void setLibraryname(String libraryname) {
		this.libraryname = libraryname;
	}



	
	
	public JSONArray getDocumentsarrayvalues() {
		return documentsarrayvalues;
	}

	public void setDocumentsarrayvalues(JSONArray documentsarrayvalues) {
		this.documentsarrayvalues = documentsarrayvalues;
	}

	public JSONArray getStartpagevaluesarray() {
		return startpagevaluesarray;
	}

	public void setStartpagevaluesarray(JSONArray startpagevaluesarray) {
		this.startpagevaluesarray = startpagevaluesarray;
	}

	public JSONArray getMimetypearraylist() {
		return mimetypearraylist;
	}

	public void setMimetypearraylist(JSONArray mimetypearraylist) {
		this.mimetypearraylist = mimetypearraylist;
	}

	public JSONArray getLastpagevaluesarray() {
		return lastpagevaluesarray;
	}

	public void setLastpagevaluesarray(JSONArray lastpagevaluesarray) {
		this.lastpagevaluesarray = lastpagevaluesarray;
	}

	

	public JSONArray getDocumentsidarraylist() {
		return documentsidarraylist;
	}

	public void setDocumentsidarraylist(JSONArray documentsidarraylist) {
		this.documentsidarraylist = documentsidarraylist;
	}
	
	@Override
	public String toString() {
		return "DocValues [documentsidarraylist=" + documentsidarraylist + ", documentsarrayvalues="
				+ documentsarrayvalues + ", startpagevaluesarray=" + startpagevaluesarray + ", mimetypearraylist="
				+ mimetypearraylist + ", lastpagevaluesarray=" + lastpagevaluesarray + ", libraryname=" + libraryname
				+ "]";
	}
	}
