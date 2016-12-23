package daowebapi.controllers.Admin;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import daowebapi.controllers.databaseController;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Formatter;
import java.util.UUID;


@RestController
public class AdminController {

    private Connection conn = null;
    private Statement stmt = null;
    public static AmazonS3 amazonS3;
    public static String s3Bucket;


    @RequestMapping(value="/authenticate", method= RequestMethod.POST)
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


    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public @ResponseBody
    Status handleFileUpload(@RequestParam("location") String location,
                            @RequestParam("description") String description,
                            @RequestParam("file") MultipartFile file) {
        Status status = new Status("FAIL");
        if (!file.isEmpty()) {
            try {
                ObjectMetadata objectMetadata = new ObjectMetadata();
                objectMetadata.setContentType(file.getContentType());

                // Upload the file for public read
                String accessKey = System.getenv("AWS_ACCESS_KEY");
                String secretKey = System.getenv("AWS_SECRET_KEY");
                s3Bucket = System.getenv("AWS_S3_BUCKET");

                if ((accessKey != null) && (secretKey != null)) {
                    AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
                    String uuid = UUID.randomUUID().toString();
                    amazonS3 = new AmazonS3Client(awsCredentials);
                    amazonS3.putObject(new PutObjectRequest(s3Bucket, uuid + ".png",
                            file.getInputStream(), objectMetadata)
                            .withCannedAcl(CannedAccessControlList.PublicRead));
                    String url = "https://s3-us-west-2.amazonaws.com/" + s3Bucket + "/" + uuid + ".png";
                    try {
                        databaseController db = new databaseController();

                        conn = db.getConnection();
                        stmt = conn.createStatement();
                        stmt.executeUpdate("INSERT INTO images (url, location, description) VALUES ('" + url + "','" + location + "','" + description + "')");
                        status.setMessage("PASS");
                    } catch (Exception e) {
                        status.setMessage(e.getMessage());
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
                }

            } catch (Exception e) {
                status.setMessage(e.getMessage());
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


