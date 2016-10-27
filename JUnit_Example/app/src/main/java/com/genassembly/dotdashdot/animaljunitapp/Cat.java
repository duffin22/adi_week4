package com.genassembly.dotdashdot.animaljunitapp;

/**
 * James Davis (General Assembly NYC)
 * Created on 4/26/16.
 */
public class Cat extends Animal {
    public Cat() {
        super(15, false, "Kitty Kat");
    }

    @Override
    public String makeNoise() {
        return "Meow!!!";
    }
}
