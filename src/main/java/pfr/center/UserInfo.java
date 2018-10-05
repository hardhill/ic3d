package pfr.center;

public class UserInfo {
    private static UserInfo ourInstance = new UserInfo();
    private String UserID = "";
    private int UserGroup = -1;

    private UserInfo() {

    }

    public static UserInfo getInstance() {
        return ourInstance;
    }

    public int getUserGroup() {
        return UserGroup;
    }

    public void setUserGroup(int userGroup) {
        UserGroup = userGroup;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public void ResetUserInfo() {
        UserGroup = -1;
        UserID = "";
    }
}
