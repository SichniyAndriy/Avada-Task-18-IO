package edu.avada.course.question_03._07;

import java.util.List;

/*
* Создать класс User, у которого будут поля: имя, фамилия и возраст.
* Также создать два класса: SerializeUsers и DeserializeUsers.
* В первом классе необходимо создать коллекцию класса User и сохранить их в файл.
* Во втором же классе необходимо получить сохраненную коллекцию из первого класса и вывести ее в консоль.
*/
public class Main {
    public static void main(String[] args) {
        UserSerializator.serialize();
        List<User> users = UserDeSerializator.deserialize();
        users.forEach(System.out::println);
    }
}
