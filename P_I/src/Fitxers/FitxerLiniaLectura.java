package Fitxers;

import ParaulaLinia.Linia;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Classe que tracta la lectura de fitxers a nivell de Linia.
 * @author Harpo Joan Alberola
 */
public class FitxerLiniaLectura {
    private static final int FI_FITXER = -1;
    private static final int CODI_ESPAI = (int) ' ';
    private static final int CODI_RETURN = (int) '\r';
    private static final int CODI_SALT_LINIA = (int) '\n';
    private int codi = CODI_ESPAI;
    private BufferedReader fitxer = null;
    
    /**
     * Mètode constructor d'un objecte FitxerLiniaLectura.
     * @param nomFitxer String del fitxer
     * @throws Exception 
     */
    public FitxerLiniaLectura(String nomFitxer) throws Exception {
        fitxer = new BufferedReader(new FileReader(nomFitxer));
    }
    
    /**
     * Mètode per dir si en un fitxer hi ha línies per llegir.
     * @return True si hi ha paraules, False en cas contrari
     * @throws Exception 
     */
    public boolean hiHaLinies() throws Exception {
        cercarLinia();
        return (codi != FI_FITXER);
    }
    
    /**
     * Mètode per llegir un fitxer caràcter a caràcter mentre el
     * caràcter llegit sigui vàlid.
     * @throws Exception 
     */
    private void cercarLinia() throws Exception {
        while ((codi == CODI_RETURN) || (codi == CODI_SALT_LINIA)) {
            codi = fitxer.read();
        }
    }
    
    /**
     * Mètode per llegir un fitxer, assigna els caràcters llegits a una
     * Linia al seu corresponent índex i la retorna.
     * @return Linia llegida del fitxer
     * @throws Exception 
     */
    public Linia lectura() throws Exception {
        Linia l = new Linia();
        while ((codi != FI_FITXER) && (codi != CODI_RETURN) && (codi != CODI_SALT_LINIA)) {
            l.afegir((char) codi);
            codi = fitxer.read();
        }
        return l;
    }
    
    /**
     * Mètode per a tancar el fitxer.
     * @throws Exception 
     */
    public void tancarFitxer() throws Exception {
        fitxer.close();
    }
}
