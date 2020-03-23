package tests;

import models.*;
import org.junit.jupiter.api.Test;

import static models.Heros.getHeros;
import static models.Inventaire.getInventaire;
import static org.junit.jupiter.api.Assertions.*;

public class TestCombat {

    @Test // indirectement, test aussi méthode subirAttaque() et attaque()
    void testFaiblessesTireur(){
        Tireur t = new Tireur(100, new Arme(0.0));
        getHeros().attaquer(t, new ArmeCac(0,80));
    }

    @Test // indirectement, test aussi méthode subirAttaque() et attaque()
    void testFaiblessesBrute(){
        Brute b = new Brute(100, new Arme(0.0));
        getHeros().attaquer(b, new ArmeDistance(0,80));
    }

    @Test
    void testFaiblessesBoss(){
        Boss b = new Boss(100, new Arme(0.0));
        getHeros().attaquer(b, new ArmeDistance(0,20));
        assertTrue(b.getPv()>75);
        getHeros().attaquer(b, new ArmeCac(0,20));
        assertTrue(b.getPv()>55);

    }

    @Test // indirectement, test aussi méthode subirAttaque() et attaque()
    void testResArmure(){
        Brute b = new Brute(100, new Arme(20.0));
        getInventaire().setArmure(new Armure(0,10));
        b.attaque();
    }


}

