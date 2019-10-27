package pw.backend.reactbackend.entity;

import javax.persistence.*;

@Entity
@Table(name="Users")
public class User {

    @Column(name="id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "login")
    private String login;
    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "birth")
    private String birth;

    @Column(name = "active")
    private Boolean active;
    protected User() {
    }

    public User(String login, String firstName, String lastName,String birth,Boolean active) {
        this.login =login;
        this.firstname = firstName;
        this.lastname = lastName;
        this.birth =birth;
        this.active=active;
    }

    public String ToString()
    {
        return "id: " +this.getId() +"\nlogin: " + this.getLogin()+"\nfirstname: " + this.getFirstname()+"\nlastname: " + this.getLastname()+"\nbirth: " + this.getBirth()+"\nactive : " + this.getActive();
    }

    public long getId() {
        return id;
    }
    public void setId(long UserId) {
        this.id = UserId;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String UserLogin) {
        this.login = UserLogin;
    }
    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String UserName) {
        this.firstname = UserName;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String UserLastName) {
        this.lastname = UserLastName;
    }
    public String getBirth() {
        return birth;
    }
    public void setBirth(String UserBirth) {
        this.birth = UserBirth;
    }
    public Boolean getActive() {
        return active;
    }
    public void setActive(Boolean UserActive) {
        this.active=UserActive;
    }


}
