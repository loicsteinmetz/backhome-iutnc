package models;

/**
 * Implémentée par les modèles liés à une partie du scénario
 */
public interface Scenarise {
    /**
     * Récupère et retourne le scénario lié au modèle
     * @return les différentes parties du scénario à afficher
     */
    String[] getScenario();
}
