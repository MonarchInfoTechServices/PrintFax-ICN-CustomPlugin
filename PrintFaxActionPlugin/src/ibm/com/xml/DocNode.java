package ibm.com.xml;

import java.util.Map;

public class DocNode {

	String id;
	Map<String, String> nodeMap;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Map<String, String> getNodeMap() {
		return nodeMap;
	}
	public void setNodeMap(Map<String, String> nodeMap) {
		this.nodeMap = nodeMap;
	}
	
	
}
