import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;




public class Day5 {
    public static int starOne() {
        ArrayList<String> fileData = getFileData("src/input.txt");
        String file = "";
        for (String line : fileData) {
            file += line + "\n";
        }
        int[][] rules = Arrays.stream(file.split("\n\n")[0]
                        .split("\n"))
                .map(t -> Arrays.stream(t.split("\\|")).mapToInt(Integer::parseInt).toArray())
                .toArray(int[][]::new);
        int[][] updates = Arrays.stream(file.split("\n\n")[1]
                        .split("\n"))
                .map(t -> Arrays.stream(t.split(",")).mapToInt(Integer::parseInt).toArray())
                .toArray(int[][]::new);


        int result = 0;
        for (int[] update : updates) {
            boolean correctOrder = true;
            for (int n : update) {
                for (int[] rule : rules) {
                    for (int i = 0; i < rules[0].length; i++) {
                        if (rule[i] == n) {
                            int index1 = -1;
                            int index2 = -1;
                            for (int j = 0; j < update.length; j++) {
                                if (update[j] == rule[0]) {
                                    index1 = j;
                                }
                                if (update[j] == rule[1]) {
                                    index2 = j;
                                }
                            }
                            if (index1 > index2 && index2 != -1) {
                                correctOrder = false;
                            }
                        }
                    }
                }
            }
            if (correctOrder) {
                result += update[update.length / 2];
            }
        }
        return result;
    }


    public static void main(String[] args) {
        ArrayList<String> fileData = getFileData("src/input.txt");
        String file = "";
        for (String line : fileData) {
            file += line + "\n";
        }
        int[][] rules = Arrays.stream(file.split("\n\n")[0]
                        .split("\n"))
                .map(t -> Arrays.stream(t.split("\\|")).mapToInt(Integer::parseInt).toArray())
                .toArray(int[][]::new);
        int[][] updates = Arrays.stream(file.split("\n\n")[1]
                        .split("\n"))
                .map(t -> Arrays.stream(t.split(",")).mapToInt(Integer::parseInt).toArray())
                .toArray(int[][]::new);


        int result = 0;
        for (int[] update : updates) {
            boolean correctOrder = true;
            boolean changeMade = true;
            while (changeMade) {
                changeMade = false;
                for (int i = 0; i < update.length; i++) {
                for (int[] rule : rules) {
                    for (int j = 0; j < rules[0].length; j++) {
                            if (rule[j] == update[i]) {
                                int index1 = -1;
                                int index2 = -1;
                                for (int k = 0; k < update.length; k++) {
                                    if (update[k] == rule[0]) {
                                        index1 = k;
                                    }
                                    if (update[k] == rule[1]) {
                                        index2 = k;
                                    }
                                }
                                if (index1 > index2 && index2 != -1) {
                                    int temp = update[index1];
                                    update[index1] = update[index2];
                                    update[index2] = temp;
                                    correctOrder = false;
                                    changeMade = true;
                                }
                            }
                        }
                    }
                }
            }
            if (!correctOrder) {
                result += update[update.length / 2];
            }
        }
        System.out.println(result);
    }

    public static ArrayList<String> getFileData(String fileName) {
        ArrayList<String> fileData = new ArrayList<String>();
        try {
            File f = new File(fileName);
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                String line = s.nextLine();
                fileData.add(line);
            }
            return fileData;
        } catch (FileNotFoundException e) {
            return fileData;
        }
    }
}