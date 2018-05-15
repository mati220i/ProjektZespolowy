package pl.ProjektZespolowy.jPhysic;

public enum Groty {
    NORMALNA(0.0324 , 0.0133, 0.0195, 1),
    SZYBKA(0.05, 0.01, 0.01, 0.4),
    WOLNA(0.09, 0.1, 0.17, 1.8);

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
