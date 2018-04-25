package pl.ProjektZespolowy.jPhysic;

import static java.lang.Math.*;
/**
 * Klasa Wzory - znajduj¹ siê tu wzory fizyczne odpowiedzialne za lot strzaly
 */
public class Wzory {
	
	/**
	 * Funkcja do otrzymywania przeciwleglego kata
	 * @param original - kat dla ktorego mamy otrzymac kat odwrotny
	 * @return - zwraca kat (double)
	 */
	public double getPrzeciwleglyKat(double original) {
		if( original < 180.0)
			return original + 180.0;
		if( original >= 180.0)
			return original - 180.0;
		else 
			return 0.0;
	}
	
	/**
	 * Funkcja do otrzymywania sily oporu powietrza 
	 * @param strzala - obiekt strzaly
	 * @param powietrze - obiekt powietrza
	 * @return zwraca wartosc sily powietrza w double
	 */
    private double silaOporuPowietrza(Strzala strzala,Powietrze powietrze) {
    	
        return ( 0.5 * powietrze.getGestoscPowietrza() * 
        		strzala.getOporStrzaly() * 
        		strzala.getPowierzchniaCzolowa() * 
        		pow(strzala.getPredkoscStrzaly(),2) );
    }

    /**
     * Funkcja do otrzymywania si³y oddzia³ywania wiatru na strzale
     * @param strzala - obiekt strzaly
     * @param powietrze - obiekt powietrza
     * @param wiatr - obiekt wiatru
     * @return zwraca wartosc sily oddzialywania wiatru w double w double
     */
    private double silaWiatru(Strzala strzala,Powietrze powietrze, Wiatr wiatr) {
    	
        return ( 0.5 * powietrze.getGestoscPowietrza() *
        		( strzala.getPowierzchniaCzolowa() + sin(strzala.getNachylenie()) *
        		strzala.getPowierzchniaBoczna() ) *
        		pow( (strzala.getPredkoscX() - wiatr.getSilaWiatru()),2) );
    }

    /**
     * Funkcja do wylicznia sily wypadkowej sily oporu powietrza i sily odzialywania wiatru
     * @param strzala - obiekt strzaly
     * @param powietrze - obiekt powietrza
     * @param wiatr - obiekt wiatru
     * @return zwraca wartosc sily wypadkowej sily oporu powietrza i sily odzialywania wiatru w double
     */
    private double silaWypadkowa(Strzala strzala,Powietrze powietrze, Wiatr wiatr) {
    	double f1 = silaOporuPowietrza(strzala, powietrze);
    	double f2 = silaWiatru(strzala, powietrze, wiatr);
    	int kierunekWiatru = 0;
    	double katMiedzySilami = 0.0;
    	
    	if ( wiatr.getSilaWiatru() < 0 ) {
    		kierunekWiatru = 0;
    	}
    	if ( wiatr.getSilaWiatru() > 0 ) {
    		kierunekWiatru = 180;
    	}
    	
    	katMiedzySilami = abs( getPrzeciwleglyKat(strzala.getNachylenie()) - kierunekWiatru );
    	
    	return sqrt( pow( ( f1 + f2 * cos( katMiedzySilami ) ) , 2) + pow( ( f2 * sin( katMiedzySilami ) ) , 2) );
    }
    
    /**
     * Funkcja do wyliczania nachylenia strzaly podczas lotu
     * @param strzala - obiekt strzaly
     * @param powietrze - obiekt powietrza
     * @param wiatr - obiekt wiatru
     * @return zwraca kat w double 
     */
    private double otrzymajNachylenie(Strzala strzala,Powietrze powietrze, Wiatr wiatr) {
    	double a = silaWiatru(strzala, powietrze, wiatr);
    	double c = silaWypadkowa(strzala, powietrze, wiatr);
    	double b = silaOporuPowietrza(strzala, powietrze);
    	
        return acos( ( pow( c , 2 ) - ( pow( a , 2 ) + ( pow( b , 2 ) )) )/( -2 * a * b)  );
    }
    
    /**
     * Funkcja wyliczajaca przyspieszenie poziome strzaly
     * @param strzala - obiekt strzaly
     * @param powietrze - obiekt powietrza
     * @param wiatr - obiekt wiatru
     * @return zwraca wartosc przyspieszenia w double 
     */
    private double otrzymajPrzyspieszeniePoziome(Strzala strzala,Powietrze powietrze, Wiatr wiatr) {
    	double silaWyp = silaWypadkowa(strzala, powietrze, wiatr);
    	double nachylenieWyp = otrzymajNachylenie(strzala, powietrze, wiatr); 	
    	double silaX = silaWyp * sin( Math.toRadians( nachylenieWyp ) );
    	
    	return silaX / strzala.getMasa();
    }
    
    /**
     * Funkcja wyliczajaca przyspieszenie pionowe strzaly
     * @param strzala - obiekt strzaly
     * @param powietrze - obiekt powietrza
     * @param wiatr - obiekt wiatru
     * @return zwraca wartosc przyspieszenia w double
     */
    private double otrzymajPrzyspieszeniePionowe(Strzala strzala,Powietrze powietrze, Wiatr wiatr) {
    	double silaWyp = silaWypadkowa(strzala, powietrze, wiatr);
    	double nachylenieWyp = otrzymajNachylenie(strzala, powietrze, wiatr);
    	double silaY= silaWyp * cos( Math.toRadians( nachylenieWyp ) );  	
    	
    	return silaY / strzala.getMasa();
    }
    
    /**
     * Funkcja do otrzymywania predkosci horyzontalnej strzaly
     * @param strzala - obiekt strzaly
     * @param powietrze - obiekt powietrza
     * @param wiatr - obiekt wiatru
     * @param czas - czas probkowania w sekundach double 
     * @return zwraca predkosc horyzontalna strzaly
     */
    private double otrzymajPredkoscX(Strzala strzala,Powietrze powietrze, Wiatr wiatr, double czas) {
    	return strzala.getPredkoscX() - otrzymajPrzyspieszeniePoziome(strzala, powietrze, wiatr) * czas;
    }

    /**
     * Funkcja do otrzymywania predkosci wertykalnej strzaly
     * @param strzala - obiekt strzaly
     * @param powietrze - obiekt powietrza
     * @param wiatr - obiekt wiatru
     * @param czas - czas probkowania w sekundach double 
     * @return zwraca predkosc wertykalnej strzaly
     */
    private double otrzymajPredkoscY(Strzala strzala,Powietrze powietrze, Wiatr wiatr, double czas) {
    	return strzala.getPredkoscY() + otrzymajPrzyspieszeniePionowe(strzala, powietrze, wiatr) * czas - 9.81 * czas ;
    }

    /**
     * wylicza pokonanï¿½ droge w poziomie w podanym czasie 
     * @param strzala - obiekt strzaly
     * @param powietrze - obiekt powietrza
     * @param wiatr - obiekt wiatru
     * @param czas - czas dla ktï¿½rego ma byï¿½ wyznaczona droga (double)
     * @return - zwraca droge w metrach (double)
     */
    public double otrzymajDrogeX(Strzala strzala,Powietrze powietrze, Wiatr wiatr, double czas) {
    	return otrzymajPredkoscX(strzala, powietrze, wiatr, czas) * czas;
    }

    /**
     * wylicza pokonanï¿½ droge w pionie w podanym czasie 
     * @param strzala - obiekt strzaly
     * @param powietrze - obiekt powietrza
     * @param wiatr - obiekt wiatru
     * @param czas - czas dla ktï¿½rego ma byï¿½ wyznaczona droga (double)
     * @return - zwraca droge w metrach (double)
     */
    public double otrzymajDrogeY(Strzala strzala,Powietrze powietrze, Wiatr wiatr, double czas) {
    	return otrzymajPredkoscY(strzala, powietrze, wiatr, czas) * czas;
    }
    
}