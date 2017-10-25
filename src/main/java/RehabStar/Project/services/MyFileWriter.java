package RehabStar.Project.services;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by dmter on 10/24/2017.
 */
public class MyFileWriter {
    private String fileName;
    private final String pathBegin = "src/main/resources/storiesFiles/";
    public MyFileWriter(String fileName){
        this.fileName = pathBegin + fileName;
    }

    public void writeToFile(String s) throws IOException {
            FileOutputStream outputStream = new FileOutputStream(fileName);
            byte[] strToBytes = s.getBytes();
            outputStream.write(strToBytes);
            outputStream.close();
    }
}
