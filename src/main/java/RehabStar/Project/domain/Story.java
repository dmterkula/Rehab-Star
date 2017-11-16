package RehabStar.Project.domain;

import org.mockito.internal.verification.Times;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.File;
import java.sql.Time;
import java.util.Arrays;
import java.sql.Timestamp;

/**
 * Created by dmter on 10/24/2017.
 */
public class Story {

    private int userId;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String fileName;
    private String title;
    private byte[] text;
    private Timestamp dateCreated;
    private String keyword1;
    private String keyword2;
    private String keyword3;

    public Story(){

    }

    public Story(int userId, String fileName, String title, Timestamp dateCreated){
        this.userId = userId;
        this.fileName = fileName;
        this.title = title;
        text = null;
        this.dateCreated = dateCreated;
    }

    public Story(int userId, String fileName, String title, byte[] text, Timestamp dateCreated){
        this.userId = userId;
        this.fileName = fileName;
        this.title = title;
        this.text = text;
        this.dateCreated = dateCreated;
    }

    public Story(int userId, String fileName, String title, byte[] text, Timestamp dateCreated, String keyword1,
                 String keyword2, String keyword3){
        this.userId = userId;
        this.fileName = fileName;
        this.title = title;
        this.text = text;
        this.dateCreated = dateCreated;
        this.keyword1 = keyword1;
        this.keyword2 = keyword2;
        this.keyword3 = keyword3;
    }

    public Story(int id, int userId, String fileName, String title, byte[] text, Timestamp dateCreated, String keyword1,
                 String keyword2, String keyword3){
        this.id = id;
        this.userId = userId;
        this.fileName = fileName;
        this.title = title;
        this.text = text;
        this.dateCreated = dateCreated;
        this.keyword1 = keyword1;
        this.keyword2 = keyword2;
        this.keyword3 = keyword3;
    }

    public Story(int id, int userId, String fileName, String title, Timestamp dateCreated){
        this.id = id;
        this.userId = userId;
        this.fileName = fileName;
        this.title = title;
        this.text = null;
        this.dateCreated = dateCreated;
    }

    public Story(int id, int userId, String fileName,String title, byte[] text, Timestamp dateCreated){
        this.id = id;
        this.userId = userId;
        this.fileName = fileName;
        this.title = title;
        this.text = text;
        this.dateCreated = dateCreated;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getKeyword1() {
        return keyword1;
    }

    public void setKeyword1(String keyword1) {
        this.keyword1 = keyword1;
    }

    public String getKeyword2() {
        return keyword2;
    }

    public void setKeyword2(String keyword2) {
        this.keyword2 = keyword2;
    }

    public String getKeyword3() {
        return keyword3;
    }

    public void setKeyword3(String keyword3) {
        this.keyword3 = keyword3;
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
