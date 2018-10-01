package cs.gtstudent.zwaste;

public class User {
    private String name;
    private UserType userType;

    private UserLogIn userLogIn;

    public User (String name, String id, String password, UserType userType) {
        this.name = name;
        this.userType = userType;
        this.userLogIn = new UserLogIn(id, password);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return userLogIn.getId();
    }

    public void setId(String id) {
        this.userLogIn.setId(id);
    }

    public String getPassword() {
        return userLogIn.getPassword();
    }

    public void setPassword(String password) {
        this.userLogIn.setPassword(password);
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
