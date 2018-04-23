package pl.ProjektZespolowy.drawing;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;

public class Archer {
	private Line spine, leg1, leg2;
	private Polyline hand1, hand2;
	private Circle head;
	
	//private Image archer;
	
	public Archer() {
		/*
		head = new Circle(50, 0, 20, Color.LIGHTPINK);
		spine = new Line(50, 20, 50, 80);
		hand1 = new Polyline(50, 20, 10, 35, 35, 40);
		hand2 = new Polyline(50, 20, 80, 40);
		leg1 = new Line(50, 80, 25, 130);
		leg2 = new Line(50, 80, 75, 130);
		*/
		//archer = new Image("/pl/ProjektZespolowy/resources/archer.jpg");
	}
	
	public void setArcherPosition(int x, int y) {
		head.setTranslateX(x);
		head.setTranslateY(y);
		spine.setTranslateX(x);
		spine.setTranslateY(y);
		hand1.setTranslateX(x);
		hand1.setTranslateY(y);
		hand2.setTranslateX(x);
		hand2.setTranslateY(y);
		leg1.setTranslateX(x);
		leg1.setTranslateY(y);
		leg2.setTranslateX(x);
		leg2.setTranslateY(y);
	}

	public Polyline getHand1() {
		return hand1;
	}

	public Polyline getHand2() {
		return hand2;
	}

	public Line getSpine() {
		return spine;
	}

	public Line getLeg1() {
		return leg1;
	}

	public Line getLeg2() {
		return leg2;
	}

	public Circle getHead() {
		return head;
	}
	
	
}
