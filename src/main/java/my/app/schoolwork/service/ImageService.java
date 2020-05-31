package my.app.schoolwork.service;

import my.app.schoolwork.entity.Image;
import my.app.schoolwork.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.Optional;

@Service
public class ImageService {
    @Autowired
    ImageRepository imageRepository;

    /**
     * @param path 文件保存目录
     * @param is 图片流
     * @param image 图片信息 name:图片名 randomName:图片随机名 url:图片url filePath:图片本地地址
     * @return
     */
    public boolean imageUpload(String path, InputStream is, Image image){
        String filePath;
        if(path.charAt(path.length()-1) == '/'){
            filePath = path + image.getRandomName();
        }else{
            filePath = path + "/" + image.getRandomName();
        }

        File dict = new File(path);//文件夹是否存在
        if (!dict.exists()){
            if (!dict.mkdirs()){
                return false;
            }
        }

        File file = new File(filePath);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            byte[] bytes = new byte[10*1024];
            int length=0;
            while((length=is.read(bytes))!=-1){
                fos.write(bytes,0,length);
            }
            image.setFilePath(filePath);
            imageRepository.save(image);
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Image findByRandomName(String randomName){
        return imageRepository.findByRandomName(randomName);
    }

    @Transactional
    public boolean deleteById(Long id){
        Optional<Image> findImageResult = imageRepository.findById(id);
        if (findImageResult.isPresent()) {
            Image image = findImageResult.get();

            //判断是否需要删除文件
            if (imageRepository.findListByUrl(image.getUrl()).size() > 1){
                //只删除数据
                imageRepository.deleteById(id);
                return true;
            } else {
                //删除数据及文件
                File file = new File(image.getFilePath());
                if (file.delete()) {
                    imageRepository.deleteById(id);
                    return true;
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
    }
}

