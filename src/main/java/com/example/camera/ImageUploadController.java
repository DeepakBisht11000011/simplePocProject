package com.example.camera;

import com.example.camera.entity.MultipleMultipartEntity;
import com.example.camera.entity.StudentEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Map;
import java.util.Objects;

@Controller
public class ImageUploadController {

    /**
     * https://denofdevelopers.com/base64-string-to-image-example/
     */

    @GetMapping("/uploadImg")
    public String getImageUploadPage() {
        return "uploadImg";
    }

    @PostMapping("/uploadBase64Img")
    public String getBase64Image(@RequestParam(value = "imageFile", required = true) String imageFile) {
        BufferedImage image = null;
        byte[] bytes = Base64.getMimeDecoder().decode(imageFile.split(",")[1]);
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        try {
            image = ImageIO.read(bis);
            bis.close();
            String format = imageFile.split(",")[0].substring(imageFile.indexOf(':'), imageFile.indexOf(';')).split("/")[1];
            File outputFile = new File("D:\\Practice\\camera\\src\\main\\resources\\images\\image." + format);
            ImageIO.write(image, format, outputFile);
        } catch (Exception e) {
            e.printStackTrace();
            return "failure";
        }
        return "success";
    }

    @PostMapping("/uploadMultiPartFile")
    public String getMultiPartImage(@RequestParam(value = "imageFileInput", required = true) MultipartFile imageFile) {
        try {
            byte[] bytes = imageFile.getBytes();
            Path path = Paths.get("D:\\Practice\\camera\\src\\main\\resources\\images\\");
            Path path1 = Paths.get(path.toAbsolutePath() + "\\" + imageFile.getOriginalFilename());
            Files.write(path1, bytes);
            System.out.println("File now written in path: " + path.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return "failure";
        }
        return "success";
    }

    @GetMapping("/cameraCapturePage")
    public String getCapturingPage() {
        return "cameraCapture";
    }

    @PostMapping("/uploadCapturedImage")
    public String getCapturedImage(@RequestParam(value = "imageFileInput", required = true) MultipartFile imageFile) {
        return "success";
    }

    @GetMapping("/uploadMultipleFiles")
    public String getMultipleFilesUpload() {
        return "uploadMultipleFiles";
    }

    @RequestMapping(value = "/uploadMultipleFiles/v1", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> uploadMultipleFiles(
            @RequestBody MultipleMultipartEntity parameter
    ) {
//    	if(Objects.nonNull(parameter) && Objects.nonNull(parameter.getFile2()) && Objects.nonNull(parameter.getFile1())) {
//    		return ResponseEntity.ok("true, files uploaded");    		
//    	}
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/uploadMultipleFiles/v2", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> uploadMultipleFilesv2(
            @RequestParam MultipartFile[] parameter
    ) {
        if (Objects.nonNull(parameter)) {
            return ResponseEntity.ok("true, files uploaded");
        }
        return ResponseEntity.noContent().build();
    }


    @RequestMapping(value = "/uploadMultipleFiles/v3", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> uploadMultipleFilesv3(
            @RequestParam Map<String, MultipartFile> parameter
    ) {
        if (Objects.nonNull(parameter)) {
            return ResponseEntity.ok("true, files uploaded");
        }
        return ResponseEntity.noContent().build();
    }

    /* v4 will not work with Rest Clients and will give
    * org.springframework.web.HttpMediaTypeNotSupportedException:
    * Content type 'multipart/form-data;boundary=---
    */
    @RequestMapping(value = "/uploadMultipleFiles/v4", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public ResponseEntity<String> uploadMultipleFilesv4(
            @RequestBody MultipleMultipartEntity body
    ) {
        if (Objects.nonNull(body)) {
            return ResponseEntity.ok("true, files uploaded");
        }
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/uploadMultipleFiles/v5", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public ResponseEntity<String> uploadMultipleFilesv5(
            @ModelAttribute MultipleMultipartEntity body
    ) {
        if (Objects.nonNull(body)) {
            return ResponseEntity.ok("true, files uploaded");
        }
        return ResponseEntity.noContent().build();
    }


    @RequestMapping(value = "/uploadText/v1", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> uploadTextv1(
            @RequestBody StudentEntity parameter
    ) {
        if (Objects.nonNull(parameter)) {
            return ResponseEntity.ok("true, files uploaded");
        }
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/uploadText/v2", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> uploadTextv2(
            @RequestBody StudentEntity parameter
    ) {
        if (Objects.nonNull(parameter)) {
            return ResponseEntity.ok("true, files uploaded");
        }
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/uploadText/v3", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public boolean uploadTextv3(
            @RequestBody StudentEntity parameter
    ) {
        if (Objects.nonNull(parameter)) {
            return true;
        }
        return false;
    }

    @PostMapping(value = "/uploadText/v4")
    @ResponseBody
    public boolean uploadTextv4(
            @RequestBody StudentEntity parameter
    ) {
        if (Objects.nonNull(parameter)) {
            return true;
        }
        return false;
    }

    @RequestMapping(value = "/uploadText/v5", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public boolean uploadTextv5(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "profession") String profession
    ) {
        if (Objects.nonNull(name)) {
            return true;
        }
        return false;
    }

}
