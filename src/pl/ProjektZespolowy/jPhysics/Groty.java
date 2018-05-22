package pl.ProjektZespolowy.jPhysics;

/**
 * Typ wyliczeniowy Groty zawierające dane fizyczne strzał: NORMALNA, SZYBKA, WOLNA
 * Dane w kolejności:
 *       waga - waga,
 *       powCzolowa - powierzchnia czołowa strzały,
 *       powBoczna - powierzchnia boczna strzały,
 *       oporOgolny - współczynnik oporu strzały.
 */
public enum Groty {
    NORMALNA(0.0324 , 0.00133, 0.0195, 1),
    SZYBKA(0.05, 0.001, 0.01, 0.4),
    WOLNA(0.09, 0.01, 0.17, 1.8);

    private double waga;
    private double powCzolowa;
    private double powBoczna;
    private double oporOgolny;

    Groty(double waga, double powCzolowa, double powBoczna, double oporOgolny) {
        this.waga = waga;
        this.powCzolowa = powCzolowa;
        this.powBoczna = powBoczna;
        this.oporOgolny = oporOgolny;
    }

    public double getWaga() {
        return waga;
    }
    public double getPowCzolowa() {
        return powCzolowa;
    }
    public double getPowBoczna() {
        return powBoczna;
    }
    public double getOporOgolny() {
        return oporOgolny;
    }

}
