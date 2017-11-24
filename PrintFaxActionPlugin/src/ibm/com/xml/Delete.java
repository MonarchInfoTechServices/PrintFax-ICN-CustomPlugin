package ibm.com.xml;

import java.util.ResourceBundle;

public class Delete {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		ResourceBundle resource = ResourceBundle.getBundle("ibm.com.properties.XMLPropertyFile");
		String string = resource.getString("ToName");
		System.out.println(string);

	}

}
