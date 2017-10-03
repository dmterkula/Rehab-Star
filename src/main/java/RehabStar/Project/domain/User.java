package RehabStar.Project.domain;

import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;

/**
 * Created by David Terkula on 10/3/2017.
 */
public class User {
    @Id
    @GeneratedValue
    private int id;
    private String userName;
    private String email;
    private String password;

    public User(int id, String userName, String email, String password){
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
