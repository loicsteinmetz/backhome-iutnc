package models;

public class Decision extends Evenement implements Configurable {

    private Evenement issueA;
    private Evenement issueB;

    public Decision(int id){
        super(id);
        initConfiguration();
    }

    public Evenement getIssueA() {
        return issueA;
    }

    public Evenement getIssueB() {
        return issueB;
    }

    @Override
    public void initConfiguration() {

    }
}
