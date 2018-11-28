package uet.oop.bomberman.input;

import uet.oop.bomberman.Game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;

public class FileDataManager {

    public void loadData() {
        String path = "/data/highscore.txt";
        int highscore = 0;
        int level = 1;
        try {
            InputStream in = getClass().getResourceAsStream(path);
            Scanner scanner = new Scanner(in, "UTF-8");

            String[] info = scanner.nextLine().split(" ");

            highscore = Integer.parseInt(info[0]);
            level = Integer.parseInt(info[1]);

        } catch (NullPointerException e) {
            System.err.println(e);
        }

        Game.highscore = highscore;
        Game.maxLevel = level;
    }

    public void exportData() {
        String path = "/data/highscore.txt";
        try {
            URL resourceUrl = getClass().getResource(path);
            File file = new File(resourceUrl.toURI());
            OutputStream output = new FileOutputStream(file);
            String s = Game.highscore + " " + Game.maxLevel;

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
