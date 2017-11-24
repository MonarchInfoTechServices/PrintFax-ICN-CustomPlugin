package ibm.com.pojo;

public class FaxValues {

	String printfaxname;

	String papersize;
	String annotation;
	String CoverPagevalue;
	String qualityvalue;
	String faxttovalue;
	String tovalue;
	String faxfromvalue;
	String phonenovalue;
	String companyvalue;
	String faxnovalue;
	String notesvalue;
	
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
	public String getCoverPagevalue() {
		return CoverPagevalue;
	}
	public void setCoverPagevalue(String coverPagevalue) {
		CoverPagevalue = coverPagevalue;
	}
	public String getQualityvalue() {
		return qualityvalue;
	}
	public void setQualityvalue(String qualityvalue) {
		this.qualityvalue = qualityvalue;
	}
	public String getFaxttovalue() {
		return faxttovalue;
	}
	public void setFaxttovalue(String faxttovalue) {
		this.faxttovalue = faxttovalue;
	}
	public String getTovalue() {
		return tovalue;
	}
	public void setTovalue(String tovalue) {
		this.tovalue = tovalue;
	}
	public String getFaxfromvalue() {
		return faxfromvalue;
	}
	public void setFaxfromvalue(String faxfromvalue) {
		this.faxfromvalue = faxfromvalue;
	}
	public String getPhonenovalue() {
		return phonenovalue;
	}
	public void setPhonenovalue(String phonenovalue) {
		this.phonenovalue = phonenovalue;
	}
	public String getCompanyvalue() {
		return companyvalue;
	}
	public void setCompanyvalue(String companyvalue) {
		this.companyvalue = companyvalue;
	}
	public String getFaxnovalue() {
		return faxnovalue;
	}
	public void setFaxnovalue(String faxnovalue) {
		this.faxnovalue = faxnovalue;
	}
	public String getNotesvalue() {
		return notesvalue;
	}
	public void setNotesvalue(String notesvalue) {
		this.notesvalue = notesvalue;
	}
	
	
	@Override
	public String toString() {
		return "FaxValues [printfaxname=" + printfaxname + ", papersize=" + papersize + ", annotation=" + annotation
				+ ", CoverPagevalue=" + CoverPagevalue + ", qualityvalue=" + qualityvalue + ", faxttovalue="
				+ faxttovalue + ", tovalue=" + tovalue + ", faxfromvalue=" + faxfromvalue + ", phonenovalue="
				+ phonenovalue + ", companyvalue=" + companyvalue + ", faxnovalue=" + faxnovalue + ", notesvalue="
				+ notesvalue + "]";
	}
	
}
