package uet.oop.bomberman.input;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;

public class FileDataManager {
    public int loadHighscore() {
        String path = "/data/highscore.txt";
        int highscore = 0;
        try {
            InputStream in = getClass().getResourceAsStream(path);
            Scanner scanner = new Scanner(in, "UTF-8");

            String[] info = scanner.nextLine().split(" ");

            highscore = Integer.parseInt(info[0]);

        } catch (NullPointerException e) {
            System.err.println(e);
        }

        return highscore;
    }

    public void exportHighscore(int highscore) {
        String path = "/data/highscore.txt";
        try {
            URL resourceUrl = getClass().getResource(path);
            File file = new File(resourceUrl.toURI());
            OutputStream output = new FileOutputStream(file);
            String s = Integer.toString(highscore);

            byte[] b = s.getBytes();
            output.write(b);

            output.close();
        } catch (URISyntaxException e) {
            System.err.println(e);
        } catch (FileNotFoundException e) {
            System.err.println(e);
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
