package pl.ProjektZespolowy.physic;

public class Main {

	public static void main(String[] args) {
		Strzala strzala = new Strzala(0.0001 ,0 , 200, 200);
		Powietrze powietrze = new Powietrze();
		Wiatr wiatr = new Wiatr();
		Wzory wzory = new Wzory();
		
		System.out.println(wzory.otrzymajDrogeX(strzala, powietrze, wiatr, 1));
		System.out.println(wzory.otrzymajDrogeY(strzala, powietrze, wiatr, 1));

	}
	
}
