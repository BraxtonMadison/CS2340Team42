package cs.gtstudent.zwaste;

/**
 * Model class that holds information about the user.
 */
public class User {

    public enum UserType {
        REG_USER("reg_user"),
        LOC_EMPL("loc_empl"),
        MANAGER("manager");

        UserType(String userType) {
            this.userType = userType;
        }

        private final String userType;

        /**
         * Getter method for user type. ADMIN is not included for security purposes;
         * ADMIN can only be assigned directly from the database.
         * @return string describing the user type.
         */
        public String getUserType() { return userType; }
        @Override
        public String toString() {
            return userType;
        }
    }

    private String name;
    private final String emailID;
    private final String id;
    private final String pw;
    private final UserType userType;

    /**
     * Constructor for User class.
     * @param name Name of the user
     * @param emailID Email address of the user. This is used for the user to log in.
     * @param password Password of the user. This is used for the user to log in.
     * @param userType User type. This defines scope of the permission this user holds.
     */
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

    /**
     * Getter method for user name.
     * @return User's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for user name.
     * @param name User's name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter method for user email address.
     * @return User's email address.
     */
    public String getEmailID() { return emailID; }

    /**
     * Getter method for user ID. This is parsed from user's email address, every part
     * before '@' character.
     * @return User's id
     */
    public String getId () { return id; }

    /**
     * Getter method for user password. This method exists for debugging purposes, and will be
     * removed later for the security matter.
     * @return User's password
     */
    public String getPassword() { return pw; }

    /**
     * Getter method for user type. Compared to the getUserType() method within the
     * inner class UserType, this method returns the instance of UserType instead of
     * a String instance.
     * @return Instance of UserType, describing the type of the user.
     */
    public UserType getUserType() { return userType; }

}
