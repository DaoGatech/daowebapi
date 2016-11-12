package daowebapi.controllers.Album;

/**
 * Created by tmizzle2005 on 11/1/16.
 */

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

public class UploadController {

    @RequestMapping(value="/upload", method= RequestMethod.POST)

    @ResponseBody

    public String handleFileUpload(

            @RequestParam("file") MultipartFile file) {

        String name = "test11";
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(name + "-uploaded")));
                stream.write(bytes);
                stream.close();
                return "You successfully uploaded " + name + " into " + name + "-uploaded !";
            } catch (Exception e) {
                return "You failed to upload " + name + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + name + " because the file was empty.";
        }
    }

}
