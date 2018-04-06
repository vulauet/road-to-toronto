package helpers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileComparor {
    private List<String> srcFile;
    private List<String> desFile;

    public FileComparor(String srcFileName, String desFileName) {
        srcFile = readFile(srcFileName);
        desFile = readFile(desFileName);
    }

    public void getDiff() {
        assert srcFile != null;
        assert desFile != null;
        int minLineCount = Math.min(srcFile.size(), desFile.size());
        for (int i = 0; i < minLineCount; i++) {
            if (!srcFile.get(i).equals(desFile.get(i))) {
                System.out.println();
                System.out.println(srcFile.get(i));
                System.out.println(desFile.get(i));
                System.out.println();
            }
        }

        for (int i = minLineCount; i < srcFile.size(); i++) {
            System.out.println("Source file line #" + i + " " + srcFile.get(i));
        }

        for (int i = minLineCount; i < desFile.size(); i++) {
            System.out.println("Des file line #" + i + " " + desFile.get(i));
        }
    }

    private List<String> readFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            List<String> fileContent = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                fileContent.add(line);
            }
            return fileContent;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        new FileComparor("", "").getDiff();
    }
}
