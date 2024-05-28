package edu.avada.course.question_03._04;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        File fileForCopy = new File("fileForCopy.txt");

        Optional<File> copyFile = makeCopy(fileForCopy);
        System.out.println(copyFile.isPresent() ? "Successful" : "Unsuccessful");
    }

    private static Optional<File> makeCopy(File from) {
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        File to = null;
        try {
            fileInputStream = new FileInputStream(from);

            to = new File("copyFile.txt");
            fileOutputStream = new FileOutputStream(to);

            byte[] buff = new byte[1024];
            int n = fileInputStream.read(buff);
            while (n > 0) {
                fileOutputStream.write(buff, 0, n);
                n = fileInputStream.read(buff);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                fileInputStream.close();
                fileOutputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return to.exists() &&
                from.getTotalSpace() == to.getTotalSpace() &&
                from.length() == to.length()
                ? Optional.of(to) : Optional.empty();
    }
}
