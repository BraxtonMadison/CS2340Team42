package cs.gtstudent.zwaste;

public class User {

    private String name;
    private String emailID;
    private String id;
    private String pw;
    private UserType userType;

    public User (String name, String emailID, String password, UserType userType) {
        this.name = name;
        this.emailID = emailID;
        this.id = parseID(emailID);
        this.pw = password;
        this.userType = userType;
    }

    private String parseID(String emailID) {
        int index = emailID.indexOf("@");
        return emailID.substring(0, index);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailID() { return emailID; }

    public String getId () { return id; }

    public String getPassword() { return pw; }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

}
