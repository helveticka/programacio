package ParaulaLinia;

import Fitxers.FitxerParaulaLectura;

/**
 * Classe que gestiona l'ús de paraules.
 * @author Harpo Joan Alberola
 */
public class Paraula {
    private static final int TAMANY = 20;
    private static final char ESPAI = ' ';
    private static final char FI = '.';
    private char[] paraula;
    private char caracter;
    private FitxerParaulaLectura diccionari;
    private int nombreCaracters;
    private final String[] nomFitxersDiccionaris = {"P_I/resources/dict/wordle_catala_diccionari.txt","P_I/resources/dict/wordle_castellano_diccionari.txt","P_I/resources/dict/wordle_english_diccionari.txt"};

    /**
     * Mètode constructor d'un objecte Paraula.
     */
    public Paraula() {
        paraula = new char[TAMANY];
        nombreCaracters = 0;
        caracter = ESPAI;
    }
    
    /**
     * Mètode per dir si en una seqüència hi ha paraules per llegir.
     * @return True si hi ha paraules, False en cas contrari
     * @throws Exception 
     */
    public boolean hiHaParaula() throws Exception {
        cercarParaula();
        return (caracter != FI);
    }
    
    /**
     * Mètode per llegir una seqüència caràcter a caràcter mentre el
     * caràcter llegit sigui vàlid.
     * @throws Exception 
     */
    public void cercarParaula() throws Exception {
        while ((!((caracter >= 'a') && (caracter <= 'z')) || ((caracter >= 'A') && (caracter <= 'Z'))) && (caracter != FI)) {
            caracter = LT.readChar();
        }
    }
    
    /**
     * Mètode per llegir una Paraula instanciada i assignar els caràcters de
     * la Paraula al seu corresponent índex.
     * @throws Exception 
     */
    public void llegirParaula() throws Exception {
        nombreCaracters = 0;
        caracter = LT.readChar();
        while ((caracter != ESPAI) && (caracter != (char) 10)) {
            paraula[nombreCaracters] = caracter;
            nombreCaracters++;
            caracter = LT.readChar();
        }
    }
    
    /**
     * Mètode para retornar una Paraula en format String.
     * @return Palabra com String
     */
    @Override
    public String toString() {
        String res = "";
        
        for (int i = 0; i < nombreCaracters; i++) {
            res += paraula[i];
        }
        return res;
    }
    
    /**
     * Mètode para retornar una Paraula en format String amb cada lletra
     * escrita en majúscula.
     * @return Paraula com a String amb components majúscules
     * @throws Exception 
     */
    public String toStringMajuscula() throws Exception {
        String res = "";
        
        for (int i = 0; i < nombreCaracters; i++) {
            res += (char) (paraula[i] - 32);
        }
        return res;
    }
    
    /**
     * Mètode para agregar un caràcter a la Paraula.
     * @param c caràcter a afegir
     * @throws Exception 
     */
    public void afegirCaracter(char c) throws Exception {
        paraula[nombreCaracters] = c;
        nombreCaracters++;
    } 
    
    /**
     * Mètode per a saber la longitud d'una Paraula.
     * @return longitud de la Paraula
     * @throws Exception 
     */
    public int getNombreCaracters() throws Exception {
        return nombreCaracters;
    }
    
    /**
     * Mètode per a saber el caràcter d'una posició de la Paraula.
     * @param auxiliar posició del caràcter
     * @return caràcter que es vol saber
     * @throws Exception 
     */
    public char getCaracter(int auxiliar) throws Exception {
        return (paraula[auxiliar]);
    }
    
    /**
     * Mètode per comparar si un caràcter de determinada posició d'una Paraula
     * és igual a un caràcter de la mateixa posició d'una altra Paraula.
     * @param aux Paraula a comparar
     * @param posicio posició del caràcter
     * @return True si els caràcters són iguals, False en cas contrari
     * @throws Exception 
     */
    public boolean caracterIgual(Paraula aux, int posicio) throws Exception {
        return (paraula[posicio] == aux.getCaracter(posicio));
    }
    
    /**
     * Mètode que indica si la resposta de l'usuari és vàlida (es considera
     * vàlida una resposta si conté cinc lletres i existeix dins el diccionari).
     * @param idioma
     * @return True si la resposta no és vàlida, False si és vàlida.
     * @throws Exception 
     */
    public boolean noValida(int idioma) throws Exception {
        return ((nombreCaracters != 5)) || noExisteix(idioma, paraula);
    }
    
    /**
     * Mètode que comprova si una Paraula està continguda dins el fitxer 
     * diccionari.
     * @param id idioma dels fitxers.
     * @param p resposta a cercar dins el fitxer.
     * @return True si la resposta no està dins el diccionari, False si hi és. 
     * @throws Exception 
     */
    private boolean noExisteix(int id, char[] p) throws Exception {
        Paraula auxiliar;
        diccionari = new FitxerParaulaLectura(nomFitxersDiccionaris[id]);
        
        while (diccionari.hiHaParaules()) {
            auxiliar = diccionari.lectura();      
            if (iguals(p, auxiliar)) {
                diccionari.tancarFitxer();
                return false;
            }
        }
        diccionari.tancarFitxer();
        return true;
    }
    
    /**
     * Mètode per comparar si dos objectes Paraula són iguals caràcter a 
     * caràcter.
     * @param p1 Paraula 1 a comparar 
     * @param p2 Paraula 2 a comparar
     * @return True si les paraules són iguals, False en cas contrari
     * @throws Exception 
     */
    private boolean iguals(char[] p1, Paraula p2) throws Exception {
        if (nombreCaracters == p2.nombreCaracters) {
            for (int i = 0; i < nombreCaracters; i++) {
                if (p1[i] != p2.getCaracter(i)) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Mètode que comprova que una resposta ja validada és la solució del joc;
     * primer es comprova amb el mètode assignacioPistes() quines lletres de la
     * resposta són correctes i posteriorment a aquest mètode es comprova si
     * totes les lletres són correctes o no.
     * @param s solució del joc
     * @return True si la resposta és la solució, False en cas contrari
     * @throws Exception 
     */
    public boolean comprovacioResposta(Paraula s) throws Exception {
        int index = 0;
        boolean iguals = true;
        
        int [] pistes = assignacioPistes(s); 
        visualitzacioPistes(pistes); 
        while (index < s.getNombreCaracters()) {
            if (pistes[index] != 1) {
                return !iguals;
            }
            index++;
        }
        return iguals;
    }
    
    /**
     * Mètode que comprova caràcter a caràcter la resposta de l'usuari amb la
     * solució del joc mitjançant un bucle anidat i assigna un valor numèric
     * a un array d'enters depenent de si les lletres de la resposta i la 
     * solució coincideixen.
     * @param s solució del joc
     * @return array de nombres enters que representen si una lletra de la
     * resposta està ben col·locada (1), mal col·locada (2) o és incorrecta (6)
     * @throws Exception 
     */
    private int[] assignacioPistes(Paraula s) throws Exception {
        int[] p = {6, 6, 6, 6, 6};
        boolean[] usada = {false, false, false, false, false}; 

        for (int i = 0; i < nombreCaracters; i++) {
            for (int j = 0; j < nombreCaracters; j++) {
                if ((paraula[i] == s.getCaracter(i)) && (usada[i] == false)) {
                    p[i] = 1;
                    usada[i] = true;
                    j = 5;
                }
                if (paraula[i] == s.getCaracter(j) && (usada[j] == false)) {
                    p[i] = 2;
                    usada[j] = true;
                    j = 5;
                } 
            }
        }
        return p;
    }
    
    /**
     * Mètode que imprimeix les pistes del joc gràcies a la classe LT.
     * @param p array d'enters retornat per assignacioPistes()
     * @param r resposta de l'usuari
     * @throws Exception 
     */
    private void visualitzacioPistes(int[] p) throws Exception {
        LT.visualizadorColorFondo(toStringMajuscula(), p);
        System.out.println("\n");
    }
}
