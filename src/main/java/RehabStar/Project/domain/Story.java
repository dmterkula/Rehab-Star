package RehabStar.Project.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.File;
import java.util.Arrays;

/**
 * Created by dmter on 10/24/2017.
 */
public class Story {
    private int userId;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String fileName;
    private byte[] text;

    public Story(){

    }

    public Story(int userId, String fileName){
        this.userId = userId;

        this.fileName = fileName;
        text = null;
    }

    public Story(int userId, String fileName, byte[] text){
        this.userId = userId;
        this.fileName = fileName;
        this.text = text;
    }

    public Story(int id, int userId, String fileName){
        this.id = id;
        this.userId = userId;
        this.fileName = fileName;
    }

    public Story(int id, int userId, String fileName, byte[] text){
        this.id = id;
        this.userId = userId;
        this.fileName = fileName;
        this.text = text;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getText() {
        return text;
    }

    public void setText(byte[] text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Story story = (Story) o;

        if (getUserId() != story.getUserId()) return false;
        if (getId() != story.getId()) return false;
        if (!getFileName().equals(story.getFileName())) return false;
        return Arrays.equals(getText(), story.getText());
    }


}
