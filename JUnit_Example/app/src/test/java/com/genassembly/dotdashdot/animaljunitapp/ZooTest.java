package com.genassembly.dotdashdot.animaljunitapp;

import org.junit.Test;

import static org.junit.Assert.*;

public class ZooTest {
    @Test
    public void addAnimal_isCorrect() throws Exception {

        Zoo zoo = Zoo.getInstance();
        int zooSize = zoo.getAnimals().size();

        Animal snake = new Snake(false);
        zoo.addAnimal(snake);

        boolean sizeCheck = (zooSize + 1 == zoo.getAnimals().size());
        boolean entryCheck = (zoo.getAnimals().get(zoo.getAnimals().size()-1).getName().equals("Snake"));

        zoo.removeAnimal(snake);

        assertEquals(sizeCheck && entryCheck,true);

    }

    @Test
    public void removeAnimal_isCorrect() throws Exception {

        Zoo zoo = Zoo.getInstance();
        Animal snake = new Snake(false);
        zoo.addAnimal(snake);
        int zooSize = zoo.getAnimals().size();

        zoo.removeAnimal(snake);
        boolean sizeCheck = (zooSize - 1 == zoo.getAnimals().size());

        boolean entryCheck = (!zoo.getAnimals().contains(snake));

        assertEquals(sizeCheck && entryCheck,true);

    }


}
