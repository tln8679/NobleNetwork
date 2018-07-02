package beans;


public class User {
	private String fullName;
	private String userName;
	private String passWord;
	private String id;
	
	public User(String fullName, String userName, String passWord, String id) {
		this.fullName = fullName;
		this.userName = userName;
		this.passWord = passWord;
		this.id = id;
	}
	
	public User(){
		
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}
	
	public String getId(){
		return id;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}



	@Override
	public String toString() {
		return "User [fullName=" + fullName + ", id=" + id + ", passWord="
				+ passWord + ", userName=" + userName + "]";
	}

	public void setId(String id) {
		this.id = id;
		
	}

	
}
