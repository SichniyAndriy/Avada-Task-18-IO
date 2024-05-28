package edu.avada.course.question_03._03;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

/*
* Прочитать из файла ранее записанный список имен. Собрать их в одну строку, используя Reader.
* Полученную строку преобразовать в коллекцию строк и вывести в консоль.
*/
public class Main {
    public static void main(String[] args) {
        InputStreamReader inputStreamReader = null;
        String names = null;
        try {
            FileInputStream fileInputStream = new FileInputStream("names.txt");
            inputStreamReader = new InputStreamReader(fileInputStream);
            int available = fileInputStream.available();
            char[] buff = new char[available];
            inputStreamReader.read(buff);
            names = String.valueOf(buff);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        Optional.ofNullable(names)
                .ifPresentOrElse(
                        System.out::println,
                        () -> System.out.println("Empty file"));
    }
}
