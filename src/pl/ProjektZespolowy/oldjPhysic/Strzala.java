package pl.ProjektZespolowy.oldjPhysic;

import java.lang.Math;
/**
 * Klasa Strzaly - dane fizyczne strzaly
 * @author Tomasz
 */
public class Strzala {
	/**
	 * masa strzały w kg (0.0324 waga strzały testowej 500 gr )
	 */
    private final double masa;
    /**
     * predkosc strzały w m/s (maks 100m/s)
     */
    private double predkoscStrzaly;
    /**
     * nachylenie strzay względem poziomu w prawa strone 0-359 stopni
     */
    private double nachylenie;
    /**
     * powierzchnia czolowa strzały
     */
    private final double powierzchniaCzolowa;
    /**
     * powierzchnia boczna strzaly
     */
    private final double powierzchniaBoczna;
    /**
     * opor strzały
     */
    private final double oporStrzaly;
    /**
     * pozycja czubka strzały X
     */
    private double pozycjaX;
    /**
     * pozycja czubka strzały Y
     */
    private double pozychaY;

    /**
     * Konstruktor podstawowy dla strzały normalnej strzaly z jej wszystkimi danymi
     * @param predkosc -  prędkość strzały
     * @param nachylenie - nachylenie względem poziomu
     * @param x - współrzędna X w układzie
     * @param y - współrzędna Y w układzie
     */
    public Strzala(double predkosc, double nachylenie, double x, double y, Groty groty) {
        this.masa = groty.getWaga();
        this.predkoscStrzaly = predkosc;
        this.nachylenie = nachylenie;
        this.powierzchniaCzolowa = groty.getPowCzolowa();
        this.powierzchniaBoczna = groty.getPowBoczna();
        this.oporStrzaly = groty.getOporOgolny();
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
