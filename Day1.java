
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Day1 {
    public static void main(String[] args) {

        ArrayList<String> fileData = getFileData("src/InputFile");
        int[] left = fileData.stream().map(a -> a.split(" {3}")[0]).mapToInt(Integer::parseInt).sorted().toArray();
        int[] right = fileData.stream().map(a -> a.split(" {3}")[1]).mapToInt(Integer::parseInt).sorted().toArray();
        int sum = 0;
        for (int i : left) {
            int occurences = 0;
            for (int j : right) {
                occurences += j == i ? 1 : 0;
            }
            sum += i * occurences;
        }
        System.out.println(sum);
    }

    public static ArrayList<String> getFileData(String fileName) {
        ArrayList<String> fileData = new ArrayList<String>();
        try {
            File f = new File(fileName);
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                String line = s.nextLine();
                if (!line.equals(""))
                    fileData.add(line);
            }
            return fileData;
        }
        catch (FileNotFoundException e) {
            return fileData;
        }
    }
}