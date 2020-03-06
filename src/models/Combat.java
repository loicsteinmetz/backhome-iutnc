package models;

public class Combat extends Evenement implements Configurable {

    private Ennemi ennemi;
    private Evenement issue;

    public Combat(int id){
        super(id);
        initConfiguration();
    }

    @Override
    public void initConfiguration() {

    }
}
