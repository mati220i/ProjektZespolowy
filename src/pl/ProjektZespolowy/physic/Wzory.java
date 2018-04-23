package pl.ProjektZespolowy.physic;

import static java.lang.Math.*;

public class Wzory {
	
	private double getPrzeciwleglyKat(double original) {
		if( original < 180.0)
			return original + 180.0;
		if( original >= 180.0)
			return original - 180.0;
		else 
			return 0.0;
	}
	
    private double silaOporuPowietrza(Strzala strzala,Powietrze powietrze) {
    	
        return ( 0.5 * powietrze.getGestoscPowietrza() * 
        		strzala.getOporStrzaly() * 
        		strzala.getPowierzchniaCzolowa() * 
        		pow(strzala.getPredkoscStrzaly(),2) );
    }

    private double silaWiatru(Strzala strzala,Powietrze powietrze, Wiatr wiatr) {
    	
        return ( 0.5 * powietrze.getGestoscPowietrza() *
        		( strzala.getPowierzchniaCzolowa() + sin(strzala.getNachylenie()) *
        		strzala.getPowierzchniaBoczna() ) *
        		pow( (strzala.getPredkoscX() - wiatr.getSilaWiatru()),2) );
    }

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
    
    private double otrzymajNachylenie(Strzala strzala,Powietrze powietrze, Wiatr wiatr) {
    	double a = silaWiatru(strzala, powietrze, wiatr);
    	double c = silaWypadkowa(strzala, powietrze, wiatr);
    	double b = silaOporuPowietrza(strzala, powietrze);
    	
        return acos( ( pow( c , 2 ) - ( pow( a , 2 ) + ( pow( b , 2 ) )) )/( -2 * a * b)  );
    }
    
    private double otrzymajPrzyspieszeniePoziome(Strzala strzala,Powietrze powietrze, Wiatr wiatr) {
    	double silaWyp = silaWypadkowa(strzala, powietrze, wiatr);
    	double nachylenieWyp = otrzymajNachylenie(strzala, powietrze, wiatr); 	
    	double silaX = silaWyp * sin( Math.toRadians( nachylenieWyp ) );
    	
    	return silaX / strzala.getMasa();
    }
    
    private double otrzymajPrzyspieszeniePionowe(Strzala strzala,Powietrze powietrze, Wiatr wiatr) {
    	double silaWyp = silaWypadkowa(strzala, powietrze, wiatr);
    	double nachylenieWyp = otrzymajNachylenie(strzala, powietrze, wiatr);
    	double silaY= silaWyp * cos( Math.toRadians( nachylenieWyp ) );  	
    	
    	return silaY / strzala.getMasa();
    }
    
    private double otrzymajPredkoscX(Strzala strzala,Powietrze powietrze, Wiatr wiatr, double czas) {
    	return strzala.getPredkoscX() - otrzymajPrzyspieszeniePoziome(strzala, powietrze, wiatr) * czas;
    }
    
    private double otrzymajPredkoscY(Strzala strzala,Powietrze powietrze, Wiatr wiatr, double czas) {
    	return strzala.getPredkoscY() + otrzymajPrzyspieszeniePionowe(strzala, powietrze, wiatr) * czas - 9.81 * czas ;
    }
    /**
     * wylicza pokonan� droge w podanym czasie 
     * @param strzala - obiekt strzala
     * @param powietrze - obiekt powietrze
     * @param wiatr - obiekt wiatr
     * @param czas - czas dla kt�rego ma by� wyznaczona droga (double)
     * @return - zwraca droge w metrach (double)
     */
    public double otrzymajDrogeX(Strzala strzala,Powietrze powietrze, Wiatr wiatr, double czas) {
    	return otrzymajPredkoscX(strzala, powietrze, wiatr, czas) * czas;
    }
    
    public double otrzymajDrogeY(Strzala strzala,Powietrze powietrze, Wiatr wiatr, double czas) {
    	return otrzymajPredkoscY(strzala, powietrze, wiatr, czas) * czas;
    }
    
}