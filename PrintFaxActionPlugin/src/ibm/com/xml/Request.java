package ibm.com.xml;

import java.util.List;



public class Request {

	String action;
	String owner;
	String System;
	FAX fax;
	Print print;

	List<DocNode> docList;
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getSystem() {
		return System;
	}
	public void setSystem(String system) {
		System = system;
	}
	public FAX getFax() {
		return fax;
	}
	public void setFax(FAX fax) {
		this.fax = fax;
	}
	public Print getPrint() {
		return print;
	}
	public void setPrint(Print print) {
		this.print = print;
	}
	public List<DocNode> getDocList() {
		return docList;
	}
	public void setDocList(List<DocNode> docList) {
		this.docList = docList;
	}


}
