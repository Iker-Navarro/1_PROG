package viii;

import java.util.ArrayList;

import java.util.Random;

 /**
  * Esta clase define objetos que contienen tantos enteros aleatorios entre 0 y 1000 como se le definen al crear un objeto
  * @see <a href = "https://aprendeaprogramar.com/">aprendeaprogranar.com - didáctica en programación</a>
  *
  */

public class SerieDeAleatoriosD {

    //Campos de la clase

    private ArrayList<Integer> serieAleatoria;

    /**
     * Constructor para la serie de aleatorios.
     * @param numeroItems El parámetro numeroItems define el número de elementos que va a tener la serie aleatoria
     */

    public SerieDeAleatoriosD (int numeroItems) {

        serieAleatoria = new ArrayList<Integer> ();

        for (int i=0; i<numeroItems; i++) {  serieAleatoria.add(0);  }

        System.out.println ("Serie inicializada. El número de elementos en la serie es: " + getNumeroItems() );

    } //Cierre del constructor

    /**
     * Método que devuelve el número de ítems (números aleatorios) existentes en la serie 
     * @return El número de items(números aleatorios) de que consta la serie
     */

    public int getNumeroItems() { return serieAleatoria.size(); }

    /**
     * Método que genera la serie de números aleatorios
     */

    public void generarSerieDeAleatorios () {

        Random numAleatorio;

        numAleatorio = new Random ();

        for (int i=0; i < serieAleatoria.size(); i++) { serieAleatoria.set(i, numAleatorio.nextInt(1000) ); }

        System.out.print ("Serie generada! ");

    } //Cierre del método

} //Cierre de la clase y del ejemplo aprenderaprogramar.com