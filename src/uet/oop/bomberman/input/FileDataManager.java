package uet.oop.bomberman.input;

import uet.oop.bomberman.Game;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;

public class FileDataManager {

    public void loadData() {
        String path = "highscore.txt";
        int highscore = 0;
        int level = 1;
        try {

            BufferedReader in = new BufferedReader(new FileReader(path));

            String[] info = in.readLine().split(" ");

            highscore = Integer.parseInt(info[0]);
            level = Integer.parseInt(info[1]);

            in.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("User data not found");
        }
        catch (Exception e) {
            System.err.println(e);
        }

        Game.highscore = highscore;
        Game.maxLevel = level;
    }

    public void exportData() {
        String path = "highscore.txt";
        try {
            PrintWriter output = new PrintWriter(new FileWriter(path));

            String s = Game.highscore + " " + Game.maxLevel;

            output.println(s);
            output.close();
        }
        catch (IOException e) {
            System.err.println(e);
        }
    }
}
