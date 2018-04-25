package pl.ProjektZespolowy.jPhysic;

public class Main {

	public static void main(String[] args) {
		Strzala strzala = new Strzala(1 ,50 , 200, 200);
		Powietrze powietrze = new Powietrze();
		Wiatr wiatr = new Wiatr();
		Wzory wzory = new Wzory();
		
		for(int i = 1; i < 101; i++){
			System.out.println(" " + wzory.otrzymajDrogeX(strzala, powietrze, wiatr, 1) + ",  " +wzory.otrzymajDrogeY(strzala, powietrze, wiatr, 1));
		}
	}
	
}
