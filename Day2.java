import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;




public class Main {
    public int starOne() {
        ArrayList<String> fileData = getFileData("src/input.txt");
        int safe = 0;
        for (String s : fileData) {
            int[] data = Arrays.stream(s.split(" ")).mapToInt(Integer::parseInt).toArray();
            int[] result = new int[data.length - 1];
            for (int i = 1; i < data.length; i++) {
                result[i - 1] = data[i] - data[i - 1];
            }
            safe +=
                    Arrays.stream(result).anyMatch(n -> n == 0) ||
                            result[0] > 0 && Arrays.stream(result).anyMatch(n -> n < 0) ||
                            result[0] < 0 && Arrays.stream(result).anyMatch(n -> n > 0) ||
                            Arrays.stream(result).anyMatch(n -> Math.abs(n) > 3) ? 0 : 1;
        }
        return safe;
    }

    public int starTwo() {
        ArrayList<String> fileData = getFileData("src/input.txt");
        int safe = 0;
        for (String s : fileData) {
            int[] data = Arrays.stream(s.split(" ")).mapToInt(Integer::parseInt).toArray();

            boolean isSafe = false;
            for (int i = 0; i < data.length; i++) {
                int[] newArr = new int[data.length-1];
                for(int j = 0, k = 0; j < data.length; j++){
                    if(j != i){
                        newArr[k] = data[j];
                        k++;
                    }
                }
                int[] result = new int[newArr.length - 1];
                for (int j = 1; j < newArr.length; j++) {
                    result[j - 1] = newArr[j] - newArr[j - 1];
                }
                if (!(Arrays.stream(result).anyMatch(n -> n == 0) ||
                        result[0] > 0 && Arrays.stream(result).anyMatch(n -> n < 0) ||
                        result[0] < 0 && Arrays.stream(result).anyMatch(n -> n > 0) ||
                        Arrays.stream(result).anyMatch(n -> Math.abs(n) > 3))) {
                    isSafe = true;
                }
            }

            safe += isSafe ? 1 : 0;
        }
        return safe;
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





