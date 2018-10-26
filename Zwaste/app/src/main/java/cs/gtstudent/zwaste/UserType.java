package cs.gtstudent.zwaste;

public enum UserType {
    REG_USER("reg_user"),
    LOC_EMPL("loc_empl"),
    ADMIN("admin");

    UserType(String userType) {
        this.userType = userType;
    }

    private final String userType;

    public String toString() {
        return userType;
    }
}