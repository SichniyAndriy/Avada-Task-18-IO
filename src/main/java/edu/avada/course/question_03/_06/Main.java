package edu.avada.course.question_03._06;

import java.io.File;
import java.util.Scanner;

/*
* Выведите список файлов в консоль с заданным расширением в указанной директории или ее поддиректориях.
* */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть директорію пошуку: ");
        String dir = scanner.nextLine();
        System.out.print("Введіть розширення для пошуку: ");
        String ext = scanner.nextLine();

        File file = new File(dir);
        if (file.isDirectory()) {
            treeWalk(file, ext);
        } else {
            System.out.println("Ви не вказали папку...");
        }
    }

    private static void treeWalk(File dir, String ext) {
        File[] files = dir.listFiles();
        for (File file: files) {
            if (file.isDirectory()) {
                treeWalk(file, ext);
            } else {
                String[] arr = file.getName().split("\\.");
                if (arr[arr.length - 1].equals(ext)) {
                    System.out.println(file.getName());
                }
            }
        }
    }
}
