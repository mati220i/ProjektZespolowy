package pl.ProjektZespolowy.jPhysics;

import static java.lang.Math.*;

/**
 * Klasa Wzory - znajduje się tu wzory fizyczne odpowiedzialne za lot strzaly
 */
public class Wzory {

    /**
     * Metoda do obliczenia nowej prędkości pionowej
     * @param strzala obiekt strzaly
     * @param powietrze obiekt powietrza
     * @param czas czas próbkowania
     */
    private void nowaPredkoscPionowa(Strzala strzala, Powietrze powietrze, double czas){


        double predkoscPionowa =  strzala.getPredkoscY();
        double ciazenie = 9.8066 * czas;
        double opor = czas * oporPowietrzaPion(strzala,powietrze);
        if(predkoscPionowa<0){
            strzala.setPredkoscY(predkoscPionowa - ciazenie + opor);
        }
        else {
            strzala.setPredkoscY(predkoscPionowa - ciazenie - opor);
        }
    }

    /**
     * Metoda do obliczenia nowej prędkości poziomej
     * @param strzala obiekt strzaly
     * @param powietrze obiekt powietrza
     * @param czas czas próbkowania
     */
    private void nowaPredkoscPozioma(Strzala strzala, Powietrze powietrze, Wiatr wiatr, double czas){

        double predkoscPozioma= strzala.getPredkoscX();
        double wia = czas * oporWiatruPoziom(strzala,powietrze,wiatr);
        double opor = czas * oporPowietrzaPoziom(strzala,powietrze);

        strzala.setPredkoscX(predkoscPozioma  - opor - wia);

    }

    /**
     * Metoda do wylicznia nowej pozycji strzaly w pionie
     * @param strzala obiekt strzaly
     * @param powietrze obiekt powietrza
     * @param czas czas próbkowania
     * @return pozycja strzały y
     */
    private double pozycjaY(Strzala strzala, Powietrze powietrze, double czas) {

        double poz = strzala.getPozycjaY();
        double drogaY = strzala.getPozycjaY() + strzala.getPredkoscY()*czas;


        nowaPredkoscPionowa(strzala,powietrze,czas);
        strzala.setPozycjaY(drogaY);

        return poz;
    }

    /**
     * Metoda do wylicznia nowej pozycji strzaly w poziomie
     * @param strzala obiekt strzaly
     * @param powietrze obiekt powietrza
     * @param czas czas próbkowania
     * @return pozycja strzały x
     */
    private double pozycjaX(Strzala strzala, Powietrze powietrze, Wiatr wiatr, double czas){

        double poz = strzala.getPozycjaX();
        double drogaX = strzala.getPozycjaX() + strzala.getPredkoscX() * czas;

        nowaPredkoscPozioma(strzala,powietrze,wiatr,czas);
        strzala.setPozycjaX(drogaX);

        return poz;
    }

    /**
     * Metoda do wylicznia nowej pozycji strzaly
     * @param strzala obiekt strzaly
     * @param powietrze obiekt powietrza
     * @param czas czas próbkowania
     * @param wiatr obiekt wiatru
     * @return pozycja strzały x w tablicy
     */
    public double[] nowapozycja(Strzala strzala, Powietrze powietrze, Wiatr wiatr, double czas){
        double[] wsp = new double[2];
        nowyKat(strzala);
        wsp[0]=pozycjaX(strzala,powietrze,wiatr,czas);
        wsp[1]=pozycjaY(strzala,powietrze,czas);

        return wsp;
    }

    /**
     * Metoda wyliczająca nachylenie strzały
     * @param strzala obiekt strzały
     */
    private void nowyKat(Strzala strzala){
        double kat=0;
        double X = strzala.getPredkoscX();
        double Y = strzala.getPredkoscY();
        if(X<0){
            if(Y<0){
                kat=-180+toDegrees(atan(Y/X));
            }
            if(Y>0){
                kat=180+toDegrees(atan(Y/X));
            }
            if(Y==0){
                kat=180;
            }
        }

        if(X>0){
            if(Y!=0){
                kat=toDegrees(atan(Y/X));
            }
            if(X==0){
                kat=0;
            }
        }
        if(Y>0)
            if(X==0)
                kat=90;
        if(Y<0)
            if(X==0)
                kat=-90;

        strzala.setNachylenie(kat);
    }

    /**
     * Medota obliczające przyspieszenie wytwarzane przez opór w pionie
     * @param strzala obiekt strzały
     * @param powietrze obiekt powietrza
     * @return przyspieszenie double
     */
    private double oporPowietrzaPion(Strzala strzala, Powietrze powietrze){

         double sila = 0.5 * powietrze.getGestoscPowietrza()*strzala.getOporStrzaly()*strzala.getPowierzchniaCzolowa()*pow(strzala.getPredkoscY(),2);
         double przyspieszenie = sila/strzala.getMasa();

         return przyspieszenie;
    }

    /**
     * Medota obliczające przyspieszenie wytwarzane przez opór w poziomie
     * @param strzala obiekt strzały
     * @param powietrze obiekt powietrza
     * @return przyspieszenie double
     */
    private double oporPowietrzaPoziom(Strzala strzala, Powietrze powietrze){

        double sila = 0.5 * powietrze.getGestoscPowietrza()*strzala.getOporStrzaly()*strzala.getPowierzchniaCzolowa()*pow(strzala.getPredkoscX(),2);
        double przyspieszenie = sila/strzala.getMasa();
        return przyspieszenie;
    }

    /**
     * Medota obliczające przyspieszenie wytwarzane przez wiatr
     * @param strzala obiekt strzały
     * @param powietrze obiekt powietrza
     * @param wiatr obiekt wiatru
     * @return przyspieszenie double
     */
    private double oporWiatruPoziom(Strzala strzala, Powietrze powietrze, Wiatr wiatr){

        double sila;

        if(wiatr.getSilaWiatru()!=0)
            sila = ( 0.5 * powietrze.getGestoscPowietrza() * ( strzala.getPowierzchniaCzolowa() + sin(strzala.getNachylenie()) * strzala.getPowierzchniaBoczna() ) * pow( (strzala.getPredkoscX() - wiatr.getSilaWiatru()),2) );
        else
            sila = 0;
        sila=abs(sila);
        if( (strzala.getPredkoscX()-wiatr.getSilaWiatru()) < 0 )
            sila = sila*(-1);

        double przyspieszenie = sila/strzala.getMasa();
        return przyspieszenie;
    }
}
