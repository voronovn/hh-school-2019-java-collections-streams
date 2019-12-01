package tasks;

import common.Person;
import common.Task;
import org.w3c.dom.ls.LSOutput;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

/*
А теперь о горьком
Всем придется читать код
А некоторым придется читать код, написанный мною
Сочувствую им
Спасите будущих жертв, и исправьте здесь все, что вам не по душе!
P.S. функции тут разные и рабочие (наверное), но вот их понятность и эффективность страдает (аж пришлось писать комменты)
P.P.S Здесь ваши правки желательно прокомментировать (можно на гитхабе в пулл реквесте)
 */
public class Task8 implements Task {
    //count больше не используется
//  private long count;

    //Не хотим выдывать апи нашу фальшивую персону, поэтому конвертим начиная со второй
    public List<String> getNames(List<Person> persons) {
//    if (persons.size() == 0) {
//      return Collections.emptyList();
//    }
//    persons.remove(0);

        // Если нужно скипнуть первый элемент - достаточно скипнуть его в стриме, а не выпиливать из листа.
        // Пустой лист обработается корректно стримом

        return persons.stream().skip(1).map(Person::getFirstName).collect(Collectors.toList());
    }

    //ну и различные имена тоже хочется
    public Set<String> getDifferentNames(List<Person> persons) {
//  return getNames(persons).stream().distinct().collect(Collectors.toSet());

        //Множественные ненужные преобразования
        return new HashSet<>(getNames(persons));
    }

    //Для фронтов выдадим полное имя, а то сами не могут
    public String convertPersonToString(Person person) {
//  String result = "";
//
//    if (person.getSecondName() != null) {
//    result += person.getSecondName();
//    }
//
//    if (person.getFirstName() != null) {
//    result += " " + person.getFirstName();
//    }
//
//    if (person.getMiddleName() != null) {
//    result += " " + person.getSecondName();
//    }
//
//    return result;

        //Зачем 2 раза собирать secondName? По-логике, нужен middleName

        return Stream.of(person.getSecondName(), person.getMiddleName(), person.getSecondName())
                .filter(Objects::nonNull)
                .collect(joining(" "));
    }

    // словарь id персоны -> ее имя
    public Map<Integer, String> getPersonNames(Collection<Person> persons) {
//    Map<Integer, String> map = new HashMap<>(1);
//    for (Person person : persons) {
//      if (!map.containsKey(person.getId())) {
//        map.put(person.getId(), convertPersonToString(person));
//      }
//    }
//    return map;

        //Стримом лакончинее
        return persons.stream()
                .collect(Collectors.toMap(Person::getId, this::convertPersonToString,(id1, id2) -> id1));
    }

    // есть ли совпадающие в двух коллекциях персоны?
    public boolean hasSamePersons(Collection<Person> persons1, Collection<Person> persons2) {
//    boolean has = false;
//    for (Person person1 : persons1) {
//      for (Person person2 : persons2) {
//        if (person1.equals(person2)) {
//          has = true;
//        }
//      }
//    }
//    return has;
      //Достаточно узнать есть ли пересечения двух сетов из переданных коллекций
        //        Set<Person> persons = new HashSet<>(persons1);
        //        persons.retainAll(new HashSet<>(persons2));
        //        return !persons.isEmpty();
        //Но можно и через стрим
        Set<Person> persons2Set = new HashSet<>(persons2);
        return persons1.stream().anyMatch(persons2Set::contains);
    }

    //Выглядит вроде неплохо...
    public long countEven(Stream<Integer> numbers) {
//    count = 0;
//    numbers.filter(num -> num % 2 == 0).forEach(num -> cogit config http.sslVerify falseunt++);
//    return count;

        //Так выглядит еще лучше
        return numbers.filter(num -> num % 2 == 0).count();
    }

    @Override
    public boolean check() {
//    System.out.println("Слабо дойти до сюда и исправить Fail этой таски?");
        System.out.println("You're awesome!");
//    boolean codeSmellsGood = false;
//    boolean reviewerDrunk = false;
//    return codeSmellsGood || reviewerDrunk;
        return true;
    }
}
