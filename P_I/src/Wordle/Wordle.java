package Wordle;

import Fitxers.Estadistiques;
import Fitxers.FitxerParaulaLectura;
import ParaulaLinia.LT;
import ParaulaLinia.Paraula;
import java.util.Random;

/**
 * Classe que conté la lògica del joc Wordle.
 * @author Harpo Joan Alberola
 */
public class Wordle {
    private Paraula solucio = new Paraula();
    private Paraula jugador = new Paraula();
    private int idioma;
    private int torns;
    private final Menu menu = new Menu();
    private final Estadistiques estadistiques = new Estadistiques();
    private FitxerParaulaLectura solucions;
    private final String[] nomFitxersSolucions = {"P_I/resources/dict/wordle_catala_solucions.txt","P_I/resources/dict/wordle_castellano_solucions.txt","P_I/resources/dict/wordle_english_solucions.txt"};
    
    /**
     * Mètode que imprimeix la capçalera del joc, i executa els mètodes que
     * inicïen el joc.
     * @throws Exception 
     */
    public void iniciWordle() throws Exception {
        System.out.println("***********************************************");
        System.out.println("                JUGAR AL WORDLE                ");
        System.out.println("***********************************************");
        System.out.print("\n");
        configuracions();
        estadistiques.escriureInici();
        menu.capcaleraMenu();
        menu.opcions();
    }
    
    /**
     * Mètode que aglomera el conjunt de configuracions que fa l'usuari per 
     * a la seva partida.
     * @throws Exception 
     */
    private void configuracions() throws Exception {
        jugador = introduccioJugador();
        estadistiques.escriureJugador(jugador);
        idioma = introduccioIdioma();
        torns = introduccioTorns();
        jugarWordle();        
    }
    
    /**
     * Mètode que demana a l'usuari el seu nom i el guarda dins un objecte 
     * Paraula per a usar-lo posteriorment.
     * @return Paraula jugador
     * @throws Exception 
     */
    private Paraula introduccioJugador() throws Exception {    
        Paraula j = new Paraula();
        
        System.out.println("ESCRIU EL TEU NOM: ");
        j.llegirParaula();
        System.out.print("\n");
        return j;
    }
    
    /**
     * Mètode que demana a l'usuari l'idioma per jugar i el guarda com a
     * nombre enter per a usar-lo posteriorment.
     * @return nombre corresponent a l'idioma
     * @throws Exception 
     */
    private int introduccioIdioma() throws Exception {    
        System.out.println("ESCULL L'IDIOMA EN QUE VOLS JUGAR: ");
        System.out.println("1. Català");
        System.out.println("2. Castellano");
        System.out.println("3. English");
        int i = LT.readInt() - 1;
        System.out.print("\n");
        return i;
    }
    
    /**
     * Mètode que demana a l'usuari els torns a jugar i els guarda com a
     * nombre enter per a usar-lo posteriorment.
     * @return nombre de torns a jugar
     * @throws Exception 
     */
    private int introduccioTorns() throws Exception {
        System.out.println("INTRODUEIX ELS TORNS QUE VOLS JUGAR:");
        int t = LT.readInt();
        System.out.print("\n");
        return t;
    }
    
    /**
     * Mètode on es duu a terme la lògica del joc Wordle, usant les
     * configuracions anteriors que ha triat l'usuari.
     * @throws Exception 
     */
    private void jugarWordle() throws Exception {
        boolean encertada = false;
        int t1 = 1;
        int t2 = torns;
        
        System.out.println("***********************************************");
        System.out.println("                    WORDLE!                    ");
        System.out.println("***********************************************");
        System.out.print("\n");
        sorteigSolucio();  
        estadistiques.escriureSolucio(solucio);
        estadistiques.escriureCapcaleraResposta();
        while (!encertada && (torns > 0)) {            
            Paraula resposta = demanarResposta(t1, t2);
            while (resposta.noValida(idioma)) {
                System.out.print("RESPOSTA NO VÀLIDA, ");
                resposta = demanarResposta(t1, t2);
            }   
            t1++;
            encertada = resposta.comprovacioResposta(solucio);
            torns--;
            estadistiques.escriureResposta(resposta);
        }
        if (encertada) {
            System.out.println("ENHORABONA, HAS GUANYAT LA PARTIDA");
        } else {
            System.out.println("S'HA ACABAT LA PARTIDA, LA SOLUCIÓ ERA: " + solucio.toStringMajuscula());
            System.out.print("\n");
        }
        estadistiques.escriureData();
    }
    
    /**
     * Mètode que obri el fitxer de possibles solucions del joc depenent
     * de l'idioma que s'ha configurat anteriorment i el guarda dins l'objecte 
     * Paraula solucio.
     * @throws Exception 
     */
    private void sorteigSolucio() throws Exception {
        Random random = new Random();
         
        solucions = new FitxerParaulaLectura(nomFitxersSolucions[idioma]);
        solucio = solucions.lecturaPosicio(random.nextInt(nombreLiniesFitxer()));
        solucions.tancarFitxer();
    }
    
    /**
     * Mètode necessari per a obtenir el nombre de línies que conte un fitxer
     * per a saber quin és el límit a l'hora d'executar random.nextInt().
     * @return nombre de línies d'un fitxer.
     * @throws Exception 
     */
    private int nombreLiniesFitxer() throws Exception {
        solucions = new FitxerParaulaLectura(nomFitxersSolucions[idioma]);
        int l = solucions.longitudFitxer();
        solucions.tancarFitxer();
        return l;
    }
    
    /**
     * Mètode que ens indica a quin torn ens trobem i demana a l'usuari una
     * resposta.
     * @param t1 torn actual
     * @param t2 nombre màxim de torns
     * @return Paraula que conté la resposta de l'usuari
     * @throws Exception 
     */
    private Paraula demanarResposta(int t1, int t2) throws Exception {        
        Paraula r = new Paraula();
        
        System.out.println("TORN " + t1 + "/" + t2 + ", ESCRIU UNA RESPOSTA:");
        r.llegirParaula();
        System.out.print("\n");
        return r;
    }
}