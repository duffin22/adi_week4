package com.genassembly.dotdashdot.animaljunitapp;

import org.junit.Test;

import static org.junit.Assert.*;

public class CatTest {
    @Test
    public void makeNoise_isCorrect() throws Exception {

        Cat cat = new Cat();
        String noise = "Meow!!!";

        assertEquals(noise,cat.makeNoise());
    }

    @Test
    public void topSpeed_isCorrect() throws Exception {

        Cat cat = new Cat();
        int speed = 15;

        assertEquals(speed,cat.getTopSpeed());
    }


}