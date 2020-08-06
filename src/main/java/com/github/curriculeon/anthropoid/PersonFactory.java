package com.github.curriculeon.anthropoid;

import com.github.curriculeon.tools.RandomUtils;
import com.github.curriculeon.tools.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by leon on 5/1/17.
 *
 * @ATTENTION_TO_STUDENTS You are FORBIDDEN from using loops of any sort within the definition of this class.
 */
public final class PersonFactory {
    public PersonFactory() {
        /** this class is not to be instantiated */
    }

    /**
     * @return a new instance of a person with fields of random values
     */
    public Person createRandomPerson() {
        String name = StringUtils.capitalizeFirstChar(RandomUtils.createString('a', 'e', 3));
        String[] aliases = RandomUtils.createStrings('a', 'z', 3, 5);
        boolean isMale = RandomUtils.createBoolean(50);
        long personalId = System.nanoTime();
        Date birthDate = RandomUtils.createDate(1950, 2010);

        Person randomPerson = new Person(name, isMale, personalId, birthDate, aliases);
        return randomPerson;
    }

    /**
     * Section 8.8
     *
     * @param listSize - number of Person objects to create
     * @return - ArrayList of Person objects
     */ // TODO
    public List<Person> createPersonList(int listSize) {
        Stream stream=createPersonStream(listSize);
        // Convert the Stream to List
        List<Person>
                list = (List<Person>) stream.collect(Collectors.toList());

        // Create an ArrayList of the List
        ArrayList<Person>
                arrayList = new ArrayList<Person>(list);

        return arrayList;  //I had to look up geeks for geeks on this one

    }


    /**
     * @param arrayLength - number of Person objects to create
     * @return - Array of Person objects
     */ // TODO
    public Person[] createPersonArray(int arrayLength)
    {
        Person[] array = createPersonStream(arrayLength).toArray(Person[]::new);
        return array;  //Called Stream then casted into person array
    }
    /**
     * Section 8.2
     *
     * @param streamCount - number of Person objects to create
     * @return - Stream representation of collection of Person objects
     */ // TODO
    public Stream<Person> createPersonStream(int streamCount) {
        Stream<Person> people=Stream.generate(() -> (createRandomPerson())).limit((streamCount));
        return people;
    }
}
//Stream<Integer> randomNumbers = Stream.generate( () -> (new Random()).nextInt(100) );

//randomNumbers.limit(20).forEach( System.out::println ); //print first 20 numbers
