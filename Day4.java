import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class Day4 {
    public int starOne() {
        ArrayList<String> fileData = getFileData("src/input.txt");
        int xmasCount = 0;
        int[][] directions = {
                {0, 1},
                {0, -1},
                {1, 0},
                {-1, 0},
                {1, 1},
                {1, -1},
                {-1, 1},
                {-1, -1}
        };
        for (int i = 0; i < fileData.size(); i++) {
            for (int j = 0; j < fileData.get(i).length(); j++) {
                if (fileData.get(i).charAt(j) == 'X') {
                    String words = "";
                    for (int[] direction : directions) {
                        String word = "";
                        int xCoord = 0;
                        int yCoord = 0;
                        for (int k = 0; k < 4; k++) {
                            try {
                                word += fileData.get(i + xCoord).charAt(j + yCoord);
                            } catch (IndexOutOfBoundsException e) {
                                break;
                            }
                            xCoord += direction[0];
                            yCoord += direction[1];
                        }
                        if (word.length() == 4) {
                            words += word + " ";
                        }
                    }
                    xmasCount += Arrays.stream(words.split(" ")).filter(s -> s.equals("XMAS")).toList().size();
                }
            }
        }
        return xmasCount;
    }


    public int starTwo() {
        ArrayList<String> fileData = getFileData("src/input.txt");
        int xmasCount = 0;
        for (int i = 0; i < fileData.size(); i++) {
            for (int j = 0; j < fileData.get(i).length(); j++) {
                if (fileData.get(i).charAt(j) == 'A') {
                    String words = "";
                    try {
                        words += " " + fileData.get(i - 1).charAt(j - 1) + fileData.get(i).charAt(j) + fileData.get(i + 1).charAt(j + 1);
                        words += " " + fileData.get(i - 1).charAt(j + 1) + fileData.get(i).charAt(j) + fileData.get(i + 1).charAt(j - 1);
                    } catch (IndexOutOfBoundsException _) {}
                    xmasCount += Arrays.stream(words.split(" ")).filter(s -> s.equals("MAS") || s.equals("SAM")).toList().size() == 2 ? 1 : 0;
                }
            }
        }
        return xmasCount;
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



