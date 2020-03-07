package models;

public class Quete implements Configurable {

    private static Quete QUETE = new Quete();
    private Evenement prochainEvenement;
    private int idProchainEvenement;

    private Quete(){
        prochainEvenement = new Combat(1); // todo : test
    }

    /**
     * Getter de l'instance de Quete (singleton)
     * @return l'instance de quete
     */
    @Singleton
    public static Quete getQuete(){
        if(QUETE == null) {
            QUETE = new Quete();
        }
        return QUETE;
    }

    public void initEvenementSuivant(int id){
        idProchainEvenement = id;
        initConfiguration();
    }

    public void initEvenementSuivant(Planete planete){
        idProchainEvenement = planete.getIdPremierEvenement();
        initConfiguration();
    }

    @Override
    public void initConfiguration() {

    }

    public Evenement getProchainEvenement() {
        return prochainEvenement;
    }
}
