package ibm.com.pojo;

public class PrintValues {
	
	String printfaxname;


	String printervalue;

	

	String papersize;
	String annotation;
	String ejecttrayvalue;
	String Orienatationvalue;
	String scalingvalue;
	String copiesvalue;
	String priorityvalue;
	String printtimevalue;
	String collatevalue;
String userId;

public String getPrintervalue() {
	return printervalue;
}
public void setPrintervalue(String printervalue) {
	this.printervalue = printervalue;
}


	public String getUserId() {
	return userId;
}
public void setUserId(String userId) {
	this.userId = userId;
}

	String libraryname;
	
	public String getLibraryname() {
		return libraryname;
	}
	public void setLibraryname(String libraryname) {
		this.libraryname = libraryname;
	}
	public String getPrintfaxname() {
		return printfaxname;
	}
	public void setPrintfaxname(String printfaxname) {
		this.printfaxname = printfaxname;
	}
	public String getPapersize() {
		return papersize;
	}
	public void setPapersize(String papersize) {
		this.papersize = papersize;
	}
	public String getAnnotation() {
		return annotation;
	}
	public void setAnnotation(String annotation) {
		this.annotation = annotation;
	}
	public String getEjecttrayvalue() {
		return ejecttrayvalue;
	}
	public void setEjecttrayvalue(String ejecttrayvalue) {
		this.ejecttrayvalue = ejecttrayvalue;
	}
	public String getOrienatationvalue() {
		return Orienatationvalue;
	}
	public void setOrienatationvalue(String orienatationvalue) {
		Orienatationvalue = orienatationvalue;
	}
	public String getScalingvalue() {
		return scalingvalue;
	}
	public void setScalingvalue(String scalingvalue) {
		this.scalingvalue = scalingvalue;
	}
	public String getCopiesvalue() {
		return copiesvalue;
	}
	public void setCopiesvalue(String copiesvalue) {
		this.copiesvalue = copiesvalue;
	}
	public String getPriorityvalue() {
		return priorityvalue;
	}
	public void setPriorityvalue(String priorityvalue) {
		this.priorityvalue = priorityvalue;
	}
	public String getPrinttimevalue() {
		return printtimevalue;
	}
	public void setPrinttimevalue(String printtimevalue) {
		this.printtimevalue = printtimevalue;
	}
	public String getCollatevalue() {
		return collatevalue;
	}
	public void setCollatevalue(String collatevalue) {
		this.collatevalue = collatevalue;
	}
	
	
	@Override
	public String toString() {
		return "PrintValues [printfaxname=" + printfaxname + ", printervalue=" + printervalue + ", papersize="
				+ papersize + ", annotation=" + annotation + ", ejecttrayvalue=" + ejecttrayvalue
				+ ", Orienatationvalue=" + Orienatationvalue + ", scalingvalue=" + scalingvalue + ", copiesvalue="
				+ copiesvalue + ", priorityvalue=" + priorityvalue + ", printtimevalue=" + printtimevalue
				+ ", collatevalue=" + collatevalue + ", userId=" + userId + ", libraryname=" + libraryname + "]";
	}
}
