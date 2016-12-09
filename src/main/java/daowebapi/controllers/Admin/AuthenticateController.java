package daowebapi.controllers.Admin;

import daowebapi.controllers.databaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Formatter;


/**
 * Created by tmizzle2005 on 12/8/16.
 */
@RestController
public class AuthenticateController {

    private Connection conn = null;
    private Statement stmt = null;

    @RequestMapping(value="/authenticate")
    public Status authenticate(@RequestParam("username") String username, @RequestParam("password") String password) {
        Status status = new Status();
        String encrypted_password = encrypt_to_SHA1(password);
        try {
            databaseController db = new databaseController();

            conn = db.getConnection();
            stmt = conn.createStatement();
            ResultSet user = stmt.executeQuery("SELECT * FROM user WHERE username='"
                    + username + "' AND password='" + encrypted_password + "'");

            while(user.next()){
                status.setMessage("PASS");
            }
            user.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{
                if(stmt!=null) conn.close();
            }catch(Exception se){
            }
            try{
                if(conn!=null) conn.close();
            }catch(Exception se){
                se.printStackTrace();
            }
        }
        return status;
    }

    private static String encrypt_to_SHA1(String password)  {
        String sha1 = "";
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(password.getBytes("UTF-8"));
            sha1 = byteToHex(crypt.digest());
        }
        catch(NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        catch(UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return sha1;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    /**
     * Created by tmizzle2005 on 12/8/16.
     */
    static class Status {

        private String message = "FAIL";

        public Status() {}

        public Status(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}


