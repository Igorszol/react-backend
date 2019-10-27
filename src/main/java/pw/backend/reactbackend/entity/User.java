package pw.backend.reactbackend.entity;

public class User {

    String UserId;
    String UserLogin;
    String UserName;
    String UserLastName;
    String UserBirth;


    public String getUserId() {
        return UserId;
    }
    public void setUserId(String UserId) {
        this.UserId = UserId;
    }
    public String getUserLogin() {
        return UserLogin;
    }
    public void setUserLogin(String UserLogin) {
        this.UserLogin = UserLogin;
    }
    public String getUserName() {
        return UserName;
    }
    public void setUserName(String UserName) {
        this.UserName = UserName;
    }
    public String getUserLastName() {
        return UserLastName;
    }
    public void setUserLastName(String UserLastName) {
        this.UserLastName = UserLastName;
    }
    public String getUserBirth() {
        return UserBirth;
    }
    public void setUserBirth(String UserBirth) {
        this.UserBirth = UserBirth;
    }


}
