package Wordle;

import Fitxers.Estadistiques;
import ParaulaLinia.LT;

/**
 * Classe inicial que controla el menú del joc.
 * @author Harpo Joan Alberola
 */
public class Menu {

    /**
     * Mètode main del programa.
     * @param args
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception {
        new Menu().inici();
    }
    
    /**
     * Mètode que inicïa l'execució del joc.
     * @throws Exception 
     */
    private void inici() throws Exception {
        capcaleraBenvinguda();
        capcaleraMenu();
        opcions();
    }
    
    /**
     * Mètode que imprimeix la capçalera de bevinguda.
     * @throws Exception 
     */
    private void capcaleraBenvinguda() throws Exception {
        System.out.println("***********************************************");
        System.out.println("              BENVINGUT Al WORDLE              ");
        System.out.println("***********************************************");
        System.out.print("\n");
    }
    
    /**
     * Mètode que imprimeix la capçalera del menú inicial.
     * @throws Exception 
     */
    public void capcaleraMenu() throws Exception {
        System.out.println("***********************************************");
        System.out.println("                  MENÚ INICIAL                 ");
        System.out.println("***********************************************");
        System.out.print("\n");
    }
    
    /**
     * Mètode que imprimeix les opcions del menú i en funció a l'entrada de
     * l'usuari executa el que s'ha triat.
     * @throws Exception 
     */
    public void opcions() throws Exception {
        int opcio;
        
        System.out.println("ESCULL UNA OPCIÓ:");
        System.out.println("1. Jugar una partida de Wordle");
        System.out.println("2. Veure les estadístiques");
        System.out.println("3. Sortir");
        opcio = LT.readInt();
        System.out.print("\n");
        switch(opcio) {
            case 1:
                Wordle w = new Wordle();
                w.iniciWordle();
            case 2:
                Estadistiques e = new Estadistiques();
                e.veureEstadistiques();
            case 3: 
                System.exit(0);
        }        
    }
}
