package com.github.curriculeon.anthropoid;

import com.github.curriculeon.tools.logging.LoggerHandler;
import com.github.curriculeon.tools.logging.LoggerWarehouse;
import com.github.curriculeon.tools.ReflectionUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by leon on 5/29/17.
 * The warehouse is responsible for storing, retrieving, and filtering personSequence
 *
 * @ATTENTION_TO_STUDENTS You are FORBIDDEN from using loops of any sort within the definition of this class.
 */
public final class PersonWarehouse implements Iterable<Person> {
    private final LoggerHandler loggerHandler = LoggerWarehouse.getLogger(PersonWarehouse.class);
    private final List<Person> people = new ArrayList<>();

    /**
     * @param person the Person object to add to the warehouse
     * @ATTENTION_TO_STUDENTS You are FORBIDDEN from modifying this method
     */
    public void addPerson(Person person) {
        loggerHandler.disbalePrinting();
        loggerHandler.info("Registering a new person object to the person warehouse...");
        loggerHandler.info(ReflectionUtils.getFieldMap(person).toString());
        people.add(person);
    }

    /**
     * @return list of names of Person objects
     */ // TODO
    public List<String> getNames() {
        Stream<Person> peopleStream = people.stream();

        return peopleStream.map(Person::getName).collect(Collectors.toList());
    }


    /**
     * @return list of uniquely named Person objects
     */ //TODO
    public Stream<Person> getUniquelyNamedPeople() {
        return null;
    }


    /**
     * @param character starting character of Person objects' name
     * @return a Stream of respective
     */ //TODO
    public Stream<Person> getUniquelyNamedPeopleStartingWith(Character character) {
        Stream<Person> peopleStream = people.stream();
        return
      people.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))//Not sure what this does.
                .entrySet()
                .stream()
                .filter(e -> e.getValue() == 1)
                .map(e -> e.getKey());
        //got from stack over flow.....
    }

    /**
     * @param n first `n` Person objects
     * @return a Stream of respective
     */ //TODO
    public Stream<Person> getFirstNUniquelyNamedPeople(int n) {

        return      people.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))//Not sure what this does.
                .entrySet()
                .stream()
                .filter(e -> e.getValue() == 1)
                .map(e -> e.getKey()).limit(n);//Same as the method above, but limited to however many specified
    }

    /**
     * @return a mapping of Person Id to the respective Person name
     */ // TODO
     Map<Long, String> getIdToNameMap() {
        Stream<Person> peopleStream = people.stream();
        Map<Long,String> map =peopleStream.collect(Collectors.toMap(Person::getPersonalId, Person::getName));

        return map;
    }

    /**
     * @return Stream of Stream of Aliases
     */ // TODO
    public Stream<Stream<String>> getNestedAliases() {

        List<Stream<String>> outerList = new ArrayList<>();
        people.forEach(person -> outerList.add(Arrays.stream(person.getAliases()).sequential()));

        return outerList.stream();//got frustrated and copied from Chris Fulton. ...could not access arrays after putting into stream
        //Only choice is to make a list of streams and then make a stream of the lists of streams

    }


    /**
     * @return Stream of all Aliases
     */ // TODO
    public Stream<String> getAllAliases() {
        List<String> aliases = new ArrayList<>();
        getNestedAliases().forEach(x -> x.forEach(aliases::add));//I have a problem with this. Are we calling forEachtwice?
        return aliases.stream();

    }

    // DO NOT MODIFY
    public Boolean contains(Person p) {
        return people.contains(p);
    }

    // DO NOT MODIFY
    public void clear() {
        people.clear();
    }

    // DO NOT MODIFY
    public int size() {
        return people.size();
    }

    @Override // DO NOT MODIFY
    public Iterator<Person> iterator() {
        return people.iterator();
    }
}
