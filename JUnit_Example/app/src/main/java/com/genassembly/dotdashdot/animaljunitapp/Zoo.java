package com.genassembly.dotdashdot.animaljunitapp;

import java.util.ArrayList;

public class Zoo {
    //Define the Zoo object
    private static Zoo zoo;

    //Definition of the list to hold the animals
    private static ArrayList<Animal> animals;

    private Zoo(){
        animals = new ArrayList<>();
    }

    public static Zoo getInstance(){
        if(zoo == null)
            zoo = new Zoo();
        return zoo;
    }

    public void flushList(){
        animals.clear();
    }

    public void addAnimal(Animal animal){
        animals.add(animal);
    }

    public void removeAnimal(Animal animal){
        animals.remove(animal);
    }

    public ArrayList<Animal> getAnimals(){
        return animals;
    }
}
