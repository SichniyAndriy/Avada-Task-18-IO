package edu.avada.course.question_03._07;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import net.datafaker.Faker;

public class UserSerializator {
    public static void serialize() {
        List<User> users = initCollection();
        ObjectOutputStream objectOutputStream = null;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("users.txt");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(users);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (objectOutputStream != null) {
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

    private static List<User> initCollection() {
        Faker faker = new Faker();
        List<User> users = new ArrayList<>();
        int len = faker.random().nextInt(10, 15);
        for (int i = 0; i < len; ++i) {
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            Integer age = faker.random().nextInt(15, 55);
            users.add(new User(firstName, lastName, age));
        }
        return users;
    }
}
