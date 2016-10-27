package com.genassembly.dotdashdot.animaljunitapp;

import org.junit.Test;

import static org.junit.Assert.*;

public class LionTest {
    @Test
    public void makeNoise_isCorrect() throws Exception {

        Lion lion = new Lion(false);
        String noise = "Roar!!!";

        assertEquals(noise,lion.makeNoise());
    }

    @Test
    public void topSpeed_isCorrect() throws Exception {

        Lion lion = new Lion(false);
        int speed = 50;

        assertEquals(speed,lion.getTopSpeed());
    }


}