package edu.avada.course.question_03._02;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

/*
* Предложить пользователю ввести список имен из консоли, разделенных только одним пробелом.
* Записать полученные данные в файл. Если файл уже имел данные ранее, перезаписать.
*/
public class Main {
    public static void main(String[] args) {
        System.out.print("Введіть імена: ");
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String[] names = line.split("\\s");

        OutputStreamWriter outputStreamWriter = null;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("names.txt");
            outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            for (String name: names) {
                outputStreamWriter.write(name + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (outputStreamWriter != null) {
                try {
                    outputStreamWriter.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
