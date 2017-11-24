package ibm.com.pojo;

public class RequestValues {

	
	String UserId;
	

	String Action;
	
	public String getUserId() {
		return UserId;
	}

	public void setUserId(String userId) {
		UserId = userId;
	}

	public String getAction() {
		return Action;
	}

	public void setAction(String action) {
		Action = action;
	}

	@Override
	public String toString() {
		return "RequestValues [UserId=" + UserId + ", Action=" + Action + "]";
	}
	
	
	
	
}
