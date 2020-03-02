package models;

/**
 * Implémentée par les modèles configurables via des fichiers externes
 */
public interface Configurable {
    /**
     * Récupère les données nécessaires aux modèles configurés via un fichier externe
     * @return données
     */
    void recupereDonnees();
}
