package my.app.schoolwork.entity;

import javax.persistence.*;

@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "int")
    private Long imageId;//图片id

    @Column(columnDefinition = "text")
    private String url;//图片url

    @Column(columnDefinition = "varchar(255)")
    private String name;//图片本名

    @Column(columnDefinition = "varchar(255)")
    private String randomName;//随机名

    @Column(columnDefinition = "varchar(255)")
    private String filePath;//图片本地地址

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRandomName() {
        return randomName;
    }

    public void setRandomName(String randomName) {
        this.randomName = randomName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
