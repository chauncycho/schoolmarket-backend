package my.app.schoolwork.controller;

import my.app.schoolwork.entity.Image;
import my.app.schoolwork.service.ImageService;
import my.app.schoolwork.util.Result;
import my.app.schoolwork.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
public class ImageController {
    @Autowired
    ImageService imageService;

    @Value("${web.upload-path}")
    private String uploadPath;

    @Value("${server.port}")
    private String port;

    @Value("${web.doman}")
    private String doman;

    @PostMapping("/image/upload")
    public Result uploadImage(@RequestBody MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String randomFileName = UUIDUtil.get()+"_"+fileName;
        String pathForView = doman+":"+port+"/image/read/"+randomFileName;
        String pathForSave = uploadPath;
        Image image = new Image();
        image.setName(fileName);
        image.setUrl(pathForView);
        image.setRandomName(randomFileName);

        try {
            imageService.imageUpload(pathForSave,file.getInputStream(),image);
        } catch (IOException e) {
            e.printStackTrace();
            return Result.error();
        }

        return Result.ok(image);
    }

    @GetMapping(value = "/image/read/{imagePath}", produces = {MediaType.IMAGE_JPEG_VALUE,MediaType.IMAGE_PNG_VALUE,MediaType.IMAGE_GIF_VALUE})
    @ResponseBody
    public byte[] readImage(@PathVariable("imagePath") String imagePath){
        Image image = imageService.findByRandomName(imagePath);
        if (image != null) {
            File file = new File(image.getFilePath());
            try {
                FileInputStream inputStream = new FileInputStream(file);
                byte[] bytes = new byte[inputStream.available()];
                inputStream.read(bytes, 0, inputStream.available());
                return bytes;
            } catch (IOException e) {
                e.printStackTrace();
                return new byte[0];
            }
        } else {
            return new byte[0];
        }
    }
}

