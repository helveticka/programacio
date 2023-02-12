package ParaulaLinia;

/**
 * Classe que gestiona l'ús de línies.
 * @author Harpo Joan Alberola
 */
public class Linia {
    private static final int MAXIM_NOMBRE_CARACTERS = 255;
    private final char[] linia = new char[MAXIM_NOMBRE_CARACTERS];
    private int nombreCaracters;
    
    /**
     * Mètode constructor d'un objecte Linia.
     */
    public Linia() {
        nombreCaracters = 0;
    }
    
    /**
     * Mètode que afegeix un caràcter a una Linia.
     * @param c caràcter a afegir
     * @throws Exception 
     */
    public void afegir(char c) throws Exception {
        linia[nombreCaracters++] = c;
    }
    
    /**
     * Mètode per a saber la longitud d'una Linia.
     * @return longitud de la Linia
     * @throws Exception 
     */
    public int longitud() throws Exception {
        return nombreCaracters;
    }
    
    /**
     * Mètode per a saber el caràcter d'una posició de la Linia.
     * @param posicio posició del caràcter
     * @return caràcter que es vol saber
     * @throws Exception 
     */
    public char getCaracter(int posicio) throws Exception {
        return linia[posicio];
    }   
    
    /**
     * Mètode que afegeix un String a una Linia.
     * @param s seqüència a afegir
     * @throws Exception 
     */
    public void afegir(String s) throws Exception {
        for (int i = 0; i < s.length(); i++) {
            linia[nombreCaracters++] = s.charAt(i);
        }
    }
    
    /**
     * Mètode para retornar una Linia en format String.
     * @return Linia com String
     */ 
    @Override
    public String toString(){      
        String s = "";
        
        for (int i = 0; i < nombreCaracters ; i++) {
            s += linia[i];
        }
        return s;
    }
}
