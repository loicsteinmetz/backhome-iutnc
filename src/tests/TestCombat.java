package tests;

import models.*;
import org.junit.jupiter.api.Test;

import static models.Heros.getHeros;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestCombat {

    @Test
    void testFaiblesseTireur(){
        Tireur t = new Tireur(100, new Arme(0.0));
        getHeros().attaquer(t, new ArmeCac(0,80));
        assertFalse(t.enVie());
    }

    @Test
    void testFaiblesseBrute(){
        Brute b = new Brute(100, new Arme(0.0));
        getHeros().attaquer(b, new ArmeDistance(0,80));
        assertFalse(b.enVie());
    }
}

