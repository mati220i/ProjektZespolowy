package pl.ProjektZespolowy.jPhysic;

public class Main {

	public static void main(String[] args) {
		Strzala strzala = new Strzala(10 ,0 , 0, 0, Groty.NORMALNA);
        Strzala strzala1 = new Strzala(0.1 ,0 , 0, 0, Groty.SZYBKA);
        Strzala strzala2 = new Strzala(0.1 ,0 , 0, 0, Groty.WOLNA);
		Powietrze powietrze = new Powietrze();
		Wiatr wiatr = new Wiatr();
		Wzory wzory = new Wzory();


        System.out.println(wzory.silaOporuPowietrza(strzala,powietrze));








		/*

        System.out.println("Strzała NORMALNA");
		for(int i = 1; i < 101; ){

			System.out.println("  " + wzory.otrzymajDrogeX(strzala, powietrze, wiatr, i) + ",  "
                     + wzory.otrzymajDrogeY(strzala, powietrze, wiatr, 1) + ",  "
                     + Math.toDegrees(strzala.getNachylenie())
                     );
		    i+=10;
		}

        System.out.println("Strzała SZYBKA");
        for(int i = 1; i < 101; ){

            System.out.println("  " + wzory.otrzymajDrogeX(strzala1, powietrze, wiatr, i) + ",  "
                    + wzory.otrzymajDrogeY(strzala1, powietrze, wiatr, 1) + ",  "
                    + Math.toDegrees(strzala1.getNachylenie())
                    );
            i+=10;
        }

        System.out.println("Strzała WOLNA");
        for(int i = 1; i < 101;){

            System.out.println("  " + wzory.otrzymajDrogeX(strzala2, powietrze, wiatr, i) + ",  "
                    + wzory.otrzymajDrogeY(strzala2, powietrze, wiatr, 1) + ",  "
                    + Math.toDegrees(strzala2.getNachylenie())
                    );
            i+=10;
        }
        */
	}
	
}
