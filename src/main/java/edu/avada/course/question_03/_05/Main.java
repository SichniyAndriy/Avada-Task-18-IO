package edu.avada.course.question_03._05;

import java.io.File;
import java.io.IOException;
import net.datafaker.Faker;

/*
* Удалите все файлы в указанной директории.
* Дополнительно выведите в консоль информацию какой файл был удален.
*/
public class Main {
    private final static String DIR_NAME = "tmp" + File.separator;
    private final static Faker FAKER = new Faker();

    public static void main(String[] args) {
        File dir = new File(DIR_NAME);
        dir.mkdir();

        int len = FAKER.random().nextInt(5, 10);
        for (int i = 0; i < len; ++i) {
            String[] arr = FAKER.file().fileName().split("\\W");
            String fileName = arr[1] + "." + arr[2];
            try {
                new File(DIR_NAME + fileName).createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        File[] files = dir.listFiles();
        for (File file: files) {
            String name = file.getName();
            file.delete();
            System.out.println("Видалено файл: " + name);
        }
        String name = dir.getName();
        dir.delete();
        System.out.println("Видалено папку: " + name);
    }
}
