//ForgotPassword.java
//Taylor Farmer

package RehabStar.Project.services;
import java.io.*;
import RehabStar.Project.dal.UserDao;
import RehabStar.Project.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ForgotPasswordImpl  implements ForgotPassword{

    @Autowired
    private UserDao userDAO;

    /*
    *Sends the user an email with their password.
    */
    @Override
    public void ForgotPasswordImpl(String email, String userName) throws IOException{

        String pass = null;

        //Getting the password of the user
        User u = userDAO.findUserByUserName(userName);
        if (u != null) {
            pass =u.getPassword();
        }


        //Run CMD, call python script, then puts in the email and password variables.
        //May need to have the specific path specified...
        Runtime r = Runtime.getRuntime();
        Process p = r.exec("c:\\EmailServer.py" + " " + email + " " + pass);

        //Retrieve output from python script
        BufferedReader bfr = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line = "";
        while ((line = bfr.readLine()) != null) {
            // display each output line form python script
            System.out.println(line);

        }


    }
}