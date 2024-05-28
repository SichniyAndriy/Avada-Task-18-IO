package edu.avada.course.question_03._01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

/*
* Создать иерархию директорий uploaded/docs. В конечной директории создать несколько текстовых файлов.
* Следует предложить пользователю ввести из консоли наименование каждого файла.
* Начало наименования каждого файла должно начинаться при помощи генерации
* универсально-уникального идентификатора и значения, введенного пользователем ранее через разделитель ‘_’.
* Далее должно быть предложено ввести текст для конкретного файла. В конечном счете,
* необходимо вывести в консоль список файлов, которые содержать какое-либо слово по запросу.
 */
public class Main {
    private final static String MAIN_PATH = "uploaded" + File.separator + "docs" + File.separator;

    public static void main(String[] args) {
        File folder = new File(MAIN_PATH);
        folder.mkdirs();

        System.out.println("Введіть назви 3 файлів");
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 3; ++i) {
            System.out.print("Назва " + (i + 1) + " файлу: ");
            String line = scanner.nextLine();
            UUID uuid = UUID.randomUUID();
            File file1 = new File(MAIN_PATH + (uuid + "_" + line));
            try {
                file1.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        File[] files = folder.listFiles();
        for (File file: files) {
            System.out.print("Введіть текст для файла " + file.getName() + ": ");
            String text = scanner.nextLine();
            toFile(text, file);
        }

        System.out.print("\nВведіть ваш запит: ");
        String query = scanner.nextLine();
        scanner.close();

        List<String> resultLList = new ArrayList<>();
        for (File file: files) {
            if (searchInFile(query, file)) {
                resultLList.add(file.getName());
            }
        }
        if (resultLList.isEmpty()) {
            System.out.println("Не знайдено жодного співпадіння");
        } else {
            System.out.println("Запит знайдено: ");
            resultLList.forEach(System.out::println);
        }

        //FINISH APP
        for (File file: files) {
            file.delete();
        }
        String parent = folder.getParent();
        File parentFolder = new File(parent + File.separator);
        folder.delete();
        parentFolder.delete();
    }

    private static void toFile(String text, File file) {
        BufferedWriter bufferedWriter = null;
        try {
            FileWriter fileWriter = new FileWriter(file);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(text);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private static boolean searchInFile(String text, File file) {
        BufferedReader bufferedReader = null;
        try {
            FileReader fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);

            boolean flag = false;
            String line = bufferedReader.readLine();
            while (line != null && !flag) {
                flag = line.contains(text);
                line = bufferedReader.readLine();
            }
            return flag;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
