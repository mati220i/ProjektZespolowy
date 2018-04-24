package pl.ProjektZespolowy.physic;

import java.lang.Math;
/**
 * Klasa Strzaly - dane fizyczne strzaly
 * @author Tomasz
 *
 */
//dane strza�y
public class Strzala {
	/**
	 * masa strza�y w kg (0.0324 waga strza�y testowej 500 gr )
	 */
    private final double masa;
    /**
     * predkosc strza�y w m/s (maks 100m/s)
     */
    private double predkoscStrzaly;
    /**
     * nachylenie strza�y wzgl�dem poziomu w prawa strone 0-359 stopni
     */
    private double nachylenie;
    /**
     * powierzchnia czolowa strza�y
     */
    private final double powierzchniaCzolowa;
    /**
     * powierzchnia boczna strzaly
     */
    private final double powierzchniaBoczna;
    /**
     * opor strza�y
     */
    private final double oporStrzaly;
    /**
     * pozycja czubka strza�y X
     */
    private double pozycjaX;
    /**
     * pozycja czubka strza�y Y
     */
    private double pozychaY;

    /**
     * Konstruktor strzaly z jej wszystkimi danymi
     * @param predkosc -  pr�dko�� strza�y
     * @param nachylenie - nachylenie wzgl�dem poziomu
     * @param x - wsp�rz�dna X w uk�adzie
     * @param y - wsp�rz�dna Y w uk�adzie
     */
    public Strzala(double predkosc, double nachylenie, double x, double y) {
    	this.masa = 0.0324;
        this.predkoscStrzaly = predkosc;
        this.nachylenie = nachylenie;
        this.powierzchniaCzolowa = 0.0133;
        this.powierzchniaBoczna = 0.0195;
        this.oporStrzaly = 0.1;
        this.pozycjaX = x;
        this.pozychaY = y;
    }
    public double getPredkoscX(){
        return getPredkoscStrzaly() * Math.cos(Math.toRadians(getNachylenie()));
    }

    public double getPredkoscY(){
        return getPredkoscStrzaly() * Math.sin(Math.toRadians(getNachylenie()));
    }


    public double getMasa() {
        return masa;
    }

    public double getPredkoscStrzaly() {
        return predkoscStrzaly;
    }

    public void setPredkoscStrzaly(double predkoscStrzaly) {
        this.predkoscStrzaly = predkoscStrzaly;
    }
    
    /**
     * 
     * @return - zwraca nachylenie w radianach
     */
    public double getNachylenie() {
        return Math.toRadians(nachylenie); 
    }

    public void setNachylenie(double nachylenie) {
        this.nachylenie = Math.toDegrees(nachylenie);
    }

    public double getPowierzchniaCzolowa() {
        return powierzchniaCzolowa;
    }

    public double getPowierzchniaBoczna() {
        return powierzchniaBoczna;
    }

    public double getOporStrzaly() {
        return oporStrzaly;
    }

    public double getPozycjaX() {
        return pozycjaX;
    }

    public void setPozycjaX(double pozycjaX) {
        this.pozycjaX = pozycjaX;
    }

    public double getPozychaY() {
        return pozychaY;
    }

    public void setPozychaY(double pozychaY) {
        this.pozychaY = pozychaY;
    }
}
