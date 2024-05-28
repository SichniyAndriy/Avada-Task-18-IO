package edu.avada.course.question_03._07;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

public class UserDeSerializator {
    public static List<User> deserialize() {
        ObjectInputStream objectInputStream = null;
        List<User> users;
        try {
            FileInputStream fileInputStream = new FileInputStream("users.txt");
            objectInputStream = new ObjectInputStream(fileInputStream);
            users = (List<User>) objectInputStream.readObject();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (objectInputStream != null) {
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return users;
    }
}
