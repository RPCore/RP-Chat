package fr.rpcore.rpchat;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Methods {

    public static int toPercentage(int v1, int v2) {

        return (v1 * 100) / v2;


    }

    public static int to255(int v1, int v2) {

        return (v1 * 255) / v2;

    }

    public static String FileReader(File fileToRead) {
        try {
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(fileToRead));
            StringWriter out = new StringWriter();
            int b;
            while ((b = in.read()) != -1) {
                out.write(b);
            }
            out.flush();
            out.close();
            in.close();
            return out.toString();
        } catch (FileNotFoundException exception) {
            System.out.println("Le fichier n'a pas �t� trouv�");
            return "notexist";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String ReadFileLine(File FileToRead, String key) {
        try {

            BufferedReader br = new BufferedReader(new FileReader(FileToRead));
            String line;
            while ((line = br.readLine()) != null) {
                if(line.startsWith(key)){
                    return line;
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e.toString());

        }


        return null;
    }
}
/*

    Class By Nathanael2611

 */