import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;




public class Day3 {
    public int starOne() {
        ArrayList<String> fileData = getFileData("src/input.txt");
        int sum = 0;
        for (String data : fileData) {
            ArrayList<String> allMatches = new ArrayList<String>();
            String regex = "mul\\([0-9]{1,3},[0-9]{1,3}\\)";
            Matcher m = Pattern.compile(regex).matcher(data);
            while (m.find()) {
                allMatches.add(m.group());
            }
            for (String n : allMatches) {
                String[] numbers = n.replaceAll("[^,0-9]", "").split(",");
                sum += Integer.parseInt(numbers[0]) * Integer.parseInt(numbers[1]);
            }
        }
        return sum;
    }


    public int starTwo() {
        ArrayList<String> fileData = getFileData("src/input.txt");
        int sum = 0;
        boolean dont = false;
        for (String data : fileData) {
            ArrayList<String> allMatches = new ArrayList<String>();
            String regex = "mul\\([0-9]{1,3},[0-9]{1,3}\\)|don't|do";
            Matcher m = Pattern.compile(regex).matcher(data);
            while (m.find()) {
                allMatches.add(m.group());
            }
            for (String n : allMatches) {
                if (n.equals("don't")) {
                    dont = true;
                }
                else if (n.equals("do")) {
                    dont = false;
                }
                else if (!dont) {
                    String[] numbers = n.replaceAll("[^,0-9]", "").split(",");
                    sum += Integer.parseInt(numbers[0]) * Integer.parseInt(numbers[1]);
                }
            }
        }
        return sum;
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



