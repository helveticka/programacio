package Fitxers;

import ParaulaLinia.Linia;
import ParaulaLinia.Paraula;
import Wordle.Menu;
import java.util.Date;

/**
 * Classe que gestiona la lectura i escriptura de les estadístiques 
 * generades pel joc Wordle.
 * @author Harpo Joan Alberola
 */
public class Estadistiques {
    private FitxerLiniaEscriptura estadistiquesLinia;
    private FitxerParaulaEscriptura estadistiquesParaula;
    private FitxerLiniaLectura estadistiquesLectura;
    private final Menu menu = new Menu();
    
    /**
     * Mètode que escriu una seqüència d'asteriscs dins el fitxer 
     * d'estadístiques per a separar les partides.
     * @throws Exception 
     */
    public void escriureInici() throws Exception {
        Linia l = new Linia();
        estadistiquesLinia = new FitxerLiniaEscriptura("resources/estadistiques.txt", true);
        
        for (int i = 0; i < 47; i++) {
            l.afegir('*');
        }
        estadistiquesLinia.escripturaLinia(l);
        estadistiquesLinia.novaLinia();
        estadistiquesLinia.tancarFitxer();
    }
    
    /**
     * Mètode que escriu dins el fitxer d'estadístiques una etiqueta informativa
     * referent a la categoria 'jugador' i el nom del jugador de la partida.
     * @param jugador Paraula que conté el nom del jugador
     * @throws Exception 
     */
    public void escriureJugador(Paraula jugador) throws Exception {
        Linia l = new Linia();
        char[] informacio = {'J', 'U', 'G', 'A', 'D', 'O', 'R', ':', ' '};
        estadistiquesLinia = new FitxerLiniaEscriptura("resources/estadistiques.txt", true);
        
        for (int i = 0; i < informacio.length; i++) {
            l.afegir(informacio[i]);
        }
        for (int i = 0; i < jugador.getNombreCaracters(); i++) {
            l.afegir(jugador.getCaracter(i));
        }
        estadistiquesLinia.escripturaLinia(l);
        estadistiquesLinia.novaLinia();
        estadistiquesLinia.tancarFitxer();
    }
    
    /**
     * Mètode que escriu dins el fitxer d'estadístiques una etiqueta informativa
     * referent a la categoria 'solució' i la solució de la partida.
     * @param solucio Paraula que conté la solució de la partida
     * @throws Exception 
     */
    public void escriureSolucio(Paraula solucio) throws Exception {
        Linia l = new Linia();
        char[] informacio = {'S', 'O', 'L', 'U', 'C', 'I', 'Ó', ':', ' '};
        estadistiquesLinia = new FitxerLiniaEscriptura("resources/estadistiques.txt", true);
        
        for (int i = 0; i < informacio.length; i++) {
            l.afegir(informacio[i]);
        }
        for (int i = 0; i < solucio.getNombreCaracters(); i++) {
            l.afegir(solucio.getCaracter(i));
        }
        estadistiquesLinia.escripturaLinia(l);
        estadistiquesLinia.novaLinia();
        estadistiquesLinia.tancarFitxer();
    }
    
    /**
     * Mètode que escriu dins el fitxer d'estadístiques una etiqueta informativa
     * referent a la categoria 'respostes'.
     * @throws Exception 
     */
    public void escriureCapcaleraResposta() throws Exception {
        Linia l = new Linia();
        char[] informacio = {'R', 'E', 'S', 'P', 'O', 'S', 'T', 'E', 'S', ':',};
        estadistiquesLinia = new FitxerLiniaEscriptura("resources/estadistiques.txt", true);
        
        for (int i = 0; i < informacio.length; i++) {
            l.afegir(informacio[i]);
        }
        estadistiquesLinia.escripturaLinia(l);
        estadistiquesLinia.novaLinia();
        estadistiquesLinia.tancarFitxer();
    }
    
    /**
     * Mètode que escriu dins el fitxer d'estadístiques la resposta
     * que ha donat el jugador.
     * @param resposta Paraula que conté la resposta del jugador
     * @throws Exception 
     */
    public void escriureResposta(Paraula resposta) throws Exception {
        estadistiquesParaula = new FitxerParaulaEscriptura("resources/estadistiques.txt", true);
        
        estadistiquesParaula.escriptura(resposta);
        estadistiquesParaula.novaLinia();
        estadistiquesParaula.tancarFitxer();
    }
    
    /**
     * Mètode que escriu dins el fitxer d'estadístiques una etiqueta informativa
     * referent a la categoria 'data' i la data en que s'ha jugat la partida.
     * @throws Exception 
     */
    public void escriureData() throws Exception {
        Date data = new Date();
        Linia l = new Linia();
        char[] informacio = {'D', 'A', 'T', 'A', ':', ' '};
        estadistiquesLinia = new FitxerLiniaEscriptura("resources/estadistiques.txt", true);
        
        for (int i = 0; i < informacio.length; i++) {
            l.afegir(informacio[i]);
        }
        l.afegir(data.toString());
        estadistiquesLinia.escripturaLinia(l);
        estadistiquesLinia.novaLinia();
        estadistiquesLinia.tancarFitxer();
    }
    
    /**
     * Mètode que visualitza Linia a Linia el contingut del fitxer 
     * d'estadístiques i les imprimeix per pantalla.
     * @throws Exception 
     */
    public void veureEstadistiques() throws Exception {
        estadistiquesLectura = new FitxerLiniaLectura("resources/estadistiques.txt");
        
        System.out.println("***********************************************");
        System.out.println("                 ESTADÍSTIQUES                 ");
        System.out.println("***********************************************");
        while (estadistiquesLectura.hiHaLinies()) {
            System.out.println(estadistiquesLectura.lectura().toString());
        }
        estadistiquesLectura.tancarFitxer();
        menu.capcaleraMenu();
        menu.opcions();
    }
}
