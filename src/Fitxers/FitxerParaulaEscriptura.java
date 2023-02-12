package Fitxers;

import ParaulaLinia.Paraula;
import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 * Classe que tracta l'escriptura de fitxers a nivell de Paraula.
 * @author Harpo Joan Alberola
 */
public class FitxerParaulaEscriptura {
    private static final int CODI_ESPAI = (int) ' ';
    private static final int CODI_RETURN = (int) '\r';
    private static final int CODI_SALT_LINIA = (int) '\n';
    private BufferedWriter fitxer = null;
    
    /**
     * Mètode constructor d'un objecte FitxerParaulaEscriptura.
     * @param nomFitxer String del fitxer
     * @throws Exception 
     */
    public FitxerParaulaEscriptura(String nomFitxer) throws Exception {
        fitxer = new BufferedWriter(new FileWriter(nomFitxer));
    }
    
    /**
     * Mètode constructor d'un objecte FitxerParaulaEscriptura.
     * @param nomFitxer String del fitxer
     * @param addicio Booleà si es vol escriure o sobreescriure el fitxer 
     * @throws Exception 
     */
    public FitxerParaulaEscriptura(String nomFitxer, boolean addicio) throws Exception {
        fitxer = new BufferedWriter(new FileWriter(nomFitxer,addicio));
    }  
    
    /**
     * Mètode per a escriure una Paraula al fitxer.
     * @param paraula Paraula a escriure
     * @throws Exception 
     */
    public void escriptura(Paraula paraula) throws Exception {
        for (int i = 0; i < paraula.getNombreCaracters(); i++) {
            fitxer.write(paraula.getCaracter(i));
        }
    }
    
    /**
     * Mètode per escriure un caràcter espai al fitxer.
     * @throws Exception 
     */
    public void escripturaEspai() throws Exception {
        fitxer.write(CODI_ESPAI);
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
     * Mètode per a tancar el fitxer.
     * @throws Exception 
     */
    public void tancarFitxer() throws Exception {
        fitxer.close();  
    }       
}
