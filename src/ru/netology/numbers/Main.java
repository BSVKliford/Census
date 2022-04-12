package ru.netology.numbers;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        int count = (int) persons.stream()
                .filter(age -> age.getAge() < 18)
                .count();
        System.out.println("Количество несовершеннолетних: " + count);
        List<String> list = persons.stream()
                .filter(sex -> sex.getSex() == Sex.MAN)
                .filter(age -> age.getAge() >= 18 && age.getAge() <= 27)
                .map((Person t) -> t.getFamily())
                .collect(Collectors.toList());
        System.out.println("Список фамилий призывников: ");
        System.out.println(list);
        List<Person> listSort = persons.stream()
                .filter(education -> education.getEducation() == Education.HIGHER)
                .filter(person -> (person.getSex() == Sex.MAN && (person.getAge() >= 18 && person.getAge() < 65))
                        || (person.getSex() == Sex.WOMAN && (person.getAge() >= 18 && person.getAge() < 60)))
                .sorted(Comparator.comparing(person -> person.getFamily()))
                .collect(Collectors.toList());
        System.out.println("Список работоспособных людей с высшим образованием: ");
        System.out.println(listSort);
    }
}