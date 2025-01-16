package Fitxers;

import ParaulaLinia.Paraula;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Classe que tracta la lectura de fitxers a nivell de Paraula.
 * @author Harpo Joan Alberola
 */
public class FitxerParaulaLectura {
    private static final int FI_FITXER = -1;
    private static final int CODI_ESPAI = (int) ' ';
    private static final int CODI_RETURN = (int) '\r';
    private static final int CODI_SALT_LINIA = (int) '\n';
    private int codi = CODI_ESPAI;
    private BufferedReader fitxer = null;
    
    /**
     * Mètode constructor d'un objecte FitxerParaulaLectura.
     * @param nomFitxer String del fitxer
     * @throws Exception 
     */
    public FitxerParaulaLectura(String nomFitxer) throws Exception {
        fitxer = new BufferedReader(new FileReader(nomFitxer));
    }
    
    /**
     * Mètode per dir si en un fitxer hi ha paraules per llegir.
     * @return True si hi ha paraules, False en cas contrari
     * @throws Exception 
     */
    public boolean hiHaParaules() throws Exception {
        cercarParaula();
        return (codi != FI_FITXER);
    }
    
    /**
     * Mètode per llegir un fitxer caràcter a caràcter mentre el
     * caràcter llegit sigui vàlid.
     * @throws Exception 
     */
    public void cercarParaula() throws Exception {
        while ((codi == CODI_ESPAI) || (codi == CODI_RETURN) || (codi == CODI_SALT_LINIA)) {
            codi = fitxer.read();
        }
    }
    
    /**
     * Mètode per llegir un fitxer, assigna els caràcters llegits a una
     * Paraula al seu corresponent índex i la retorna.
     * @return Paraula llegida del fitxer
     * @throws Exception 
     */
    public Paraula lectura() throws Exception {
        Paraula paraula = new Paraula();
        
        while ((codi != FI_FITXER) && (codi != CODI_ESPAI) && (codi != CODI_RETURN)
                && (codi != CODI_SALT_LINIA)) {
            paraula.afegirCaracter((char) codi);
            codi = fitxer.read();
        } 
        return paraula;
    }
    
    /**
     * Mètode per llegir una posició determinada d'un fitxer, assigna els
     * caràcters llegits a una Paraula al seu corresponent índex i la retorna.
     * @param posicio posicio a llegir del fitxer
     * @return Paraula llegida
     * @throws Exception 
     */
    public Paraula lecturaPosicio(int posicio) throws Exception {
        Paraula p = new Paraula();

        for (int i = 0; i < posicio; i++) {
            p = new Paraula();
            while ((codi != FI_FITXER) && (codi != CODI_ESPAI) && (codi != CODI_RETURN)
                    && (codi != CODI_SALT_LINIA)) {
                p.afegirCaracter((char) codi);
                codi = fitxer.read();
            }
            cercarParaula();
        }
        return p;
    }
    
    /**
     * Mètode que retorna el nombre de línies que conté un fitxer.
     * @return nombre de línies
     * @throws Exception 
     */
    public int longitudFitxer() throws Exception {
        int longitudFitxer = 0;
        
        while (fitxer.readLine() != null) {
            longitudFitxer++;
        }
        return longitudFitxer;
    }
    
    /**
     * Mètode per a tancar el fitxer.
     * @throws Exception 
     */
    public void tancarFitxer() throws Exception {
        fitxer.close();
    }
}
