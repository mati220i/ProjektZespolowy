package pl.ProjektZespolowy.jPhysics;

import static java.lang.Math.*;

/**
 * Klasa Strzaly - dane fizyczne strzaly
 * 
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
	private double predkoscX;
	private double predkoscY;
	/**
	 * nachylenie strzay względem poziomu w prawa strone 180-(-179) stopni
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
	private double pozycjaY;

	/**
	 * Konstruktor podstawowy dla strzały normalnej strzaly z jej wszystkimi
	 * danymi
	 * 
	 * @param predkosc
	 *            - prędkość strzały
	 * @param nachylenie
	 *            - nachylenie względem poziomu
	 * @param x
	 *            - współrzędna X w układzie
	 * @param y
	 *            - współrzędna Y w układzie
	 */
	public Strzala(double predkosc, double nachylenie, double x, double y, Groty groty) {

		if (nachylenie > 180 && nachylenie < -180) {
			nachylenie = nachylenie * (-1) - 180;
		}

		this.masa = groty.getWaga();
		this.predkoscX = predkosc * Math.cos(toRadians(nachylenie));
		this.predkoscY = predkosc * Math.sin(toRadians(nachylenie));
		this.nachylenie = nachylenie;
		this.powierzchniaCzolowa = groty.getPowCzolowa();
		this.powierzchniaBoczna = groty.getPowBoczna();
		this.oporStrzaly = groty.getOporOgolny();
		this.pozycjaX = x;
		this.pozycjaY = y;
	}

	public double getPredkoscX() {

		return predkoscX;
	}

	public double getPredkoscY() {
		return predkoscY;
	}

	public void setPredkoscX(double predkoscX) {
		this.predkoscX = predkoscX;
	}

	public void setPredkoscY(double predkoscY) {
		this.predkoscY = predkoscY;
	}

	public double getMasa() {
		return masa;
	}

	/**
	 * @return - zwraca nachylenie w radianach
	 */
	public double getNachylenie() {
		return nachylenie;
	}

	public void setNachylenie(double nachylenie) {
		this.nachylenie = nachylenie;
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

	public double getPozycjaY() {
		return pozycjaY;
	}

	public void setPozycjaY(double pozycjaY) {
		this.pozycjaY = pozycjaY;
	}

	@Override
	public String toString() {
		return "Strzala [pozycjaX=" + pozycjaX + ", pozycjaY=" + pozycjaY + "]";
	}

}
