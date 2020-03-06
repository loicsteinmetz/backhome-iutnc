package models;

public class Quete {

    private static Quete QUETE = new Quete();

    private Quete(){

    }

    public static Quete getQuete(){
        return QUETE;
    }
}
