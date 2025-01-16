package Fitxers;

import ParaulaLinia.Linia;
import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 * Classe que tracta l'escriptura de fitxers a nivell de Linia.
 * @author Harpo Joan Alberola
 */
public class FitxerLiniaEscriptura {
    
    private static final int CODI_RETURN = (int) '\r';
    private static final int CODI_SALT_LINIA = (int) '\n';
    private BufferedWriter fitxer = null;
    
    /**
     * Mètode constructor d'un objecte FitxerLiniaEscriptura.
     * @param nomFitxer String del fitxer
     * @throws Exception 
     */
    public FitxerLiniaEscriptura(String nomFitxer) throws Exception {
        fitxer = new BufferedWriter(new FileWriter(nomFitxer));
    }
    
    /**
     * Mètode constructor d'un objecte FitxerLiniaEscriptura.
     * @param nomFitxer String del fitxer
     * @param addicio Booleà si es vol escriure o sobreescriure el fitxer 
     * @throws Exception 
     */
    public FitxerLiniaEscriptura(String nomFitxer, boolean addicio) throws Exception {
        fitxer = new BufferedWriter(new FileWriter(nomFitxer, addicio));
    }
    
    /**
     * Mètode per escriure un salt de línia al fitxer.
     * @throws Exception 
     */
    public void novaLinia() throws Exception {
        fitxer.write(CODI_RETURN);
        fitxer.write(CODI_SALT_LINIA);
    }
    
    /**
     * Mètode per a escriure una Linia al fitxer.
     * @param linia Linia a escriure
     * @throws Exception 
     */
    public void escripturaLinia(Linia linia) throws Exception {
        for (int i = 0; i < linia.longitud(); i++) {
            fitxer.write(linia.getCaracter(i));
        }
    }
    
    /**
     * Mètode per a tancar el fitxer.
     * @throws Exception 
     */
    public void tancarFitxer() throws Exception {
        fitxer.close();
    }
}
