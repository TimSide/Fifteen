import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class FileHandler {

    private static Scanner scn;

    // Recording data to file
    static void writeFile(int a) {
        try {
            FileWriter writer = new FileWriter("15records.txt");
            writer.write(Integer.toString(a));
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // Opening file and updating the record data
    static int openFile() {
        try {
            scn = new Scanner(new File("15records.txt"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return scn.nextInt();
    }
}
