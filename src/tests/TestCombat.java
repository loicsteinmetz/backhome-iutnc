package tests;

import models.*;
import org.junit.jupiter.api.Test;

import static models.Heros.getHeros;
import static models.Inventaire.getInventaire;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestCombat {

    @Test // indirectement, test aussi méthode subirAttaque() et attaque()
    void testFaiblessesTireur(){
        int deg = 10;
        int pv = 100;
        //test faiblesse
        Tireur p = new Tireur(pv, new Arme(0.0));
        getHeros().attaquer(p, new ArmeCac(0,deg));
        assertTrue(p.getPv() != pv);
        assertTrue(p.getPv() >= pv - deg*1.5 - deg*0.5);
        assertTrue(p.getPv() <= pv - deg*1.5 + deg*0.5);
        //test résistance
        p = new Tireur(pv, new Arme(0.0));
        getHeros().attaquer(p, new ArmeDistance(0,deg));
        assertTrue(p.getPv() != pv);
        assertTrue(p.getPv() >= pv - deg*0.75 - deg*0.5);
        assertTrue(p.getPv() <= pv - deg*0.75 + deg*0.5);
    }

    @Test // indirectement, test aussi méthode subirAttaque() et attaque()
    void testFaiblessesBrute(){
        int deg = 10;
        int pv = 100;
        //test faiblesse
        Brute p = new Brute(pv, new Arme(0.0));
        getHeros().attaquer(p, new ArmeDistance(0,deg));
        assertTrue(p.getPv() != pv);
        assertTrue(p.getPv() >= pv - deg*1.5 - deg*0.5);
        assertTrue(p.getPv() <= pv - deg*1.5 + deg*0.5);
        //test résistance
        p = new Brute(pv, new Arme(0.0));
        getHeros().attaquer(p, new ArmeCac(0,deg));
        assertTrue(p.getPv() != pv);
        assertTrue(p.getPv() >= pv - deg*0.75 - deg*0.5);
        assertTrue(p.getPv() <= pv - deg*0.75 + deg*0.5);
    }

    @Test
    void testFaiblessesBoss(){
        int deg = 10;
        int pv = 100;
        //test neutralité
        Brute p = new Brute(pv, new Arme(0.0));
        getHeros().attaquer(p, new ArmeDistance(0,deg));
        assertTrue(p.getPv() != pv);
        assertTrue(p.getPv() >= pv - deg - deg*0.5);
        assertTrue(p.getPv() <= pv - deg + deg*0.5);

    }

    @Test // indirectement, test aussi méthode subirAttaque() et attaque()
    void testResArmureHeros(){
        double deg = 20;
        int pv = 100;
        int res = 10;
        Brute p = new Brute(1, new Arme(deg));
        getInventaire().setArmure(new Armure(0,res));
        p.attaque();
        assertTrue(getHeros().getPv() != pv);
        assertTrue(getHeros().getPv() >= pv - deg - deg*0.5 + res);
        assertTrue(getHeros().getPv() <= pv - deg + deg*0.5 + res);
    }

}

