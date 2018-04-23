package pl.ProjektZespolowy.drawing;

import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Arrow {
	private Line shaft;
	//private Polygon fletching, head;
	
	
	
	// Real position on map
	private int xPosition, yPosition;
	//private int startX, startY;

	public Arrow() {
		//fletching = new Polygon(15, 15, 20, 20, 15, 25);
		shaft = new Line(20,20,100,20);
		shaft.setStrokeWidth(4);
		shaft.setStroke(Color.BROWN);
		//startX = (int) shaft.getTranslateX();
		//startY = (int) shaft.getTranslateY();
		//head = new Polygon(100,18, 105, 20, 100, 22);
		
		
	}

	public void changeArrowPosition(int x, int y) {
		
		//x += startX;
		//y += startY;
		
		shaft.setRotate(0);
		
		shaft.setTranslateX(x);
		shaft.setTranslateY(y);
		/*
		fletching.setTranslateX(x);
		fletching.setTranslateY(y);
		head.setTranslateX(x);
		head.setTranslateY(y);
		*/
		xPosition = (int) shaft.getTranslateX();
		yPosition = (int) shaft.getTranslateY();
	}
	
	public void changeArrowIncline(double angle, int pow) {
		int x1 = (int) shaft.getLayoutX();
		int y1 = (int) shaft.getLayoutY();
		
		double angleInRadians = Math.toRadians(angle);

		int x0 = -45 + pow;
		int y0 = -5;
		
		int x2 = (int) calculateX2(angleInRadians, x0, x1, y0, y1);
		int y2 = (int) calculateY2(angleInRadians, x0, x1, y0, y1);
		
		shaft.setRotate(angle);
		shaft.setTranslateX(x2 + xPosition);
		shaft.setTranslateY(y2 + yPosition);
		
		/*
		x1 = (int) fletching.getLayoutX();
		y1 = (int) fletching.getLayoutY();
		
		x0 = -2;
		y0 = -5;
		
		x2 = (int) calculateX2(angleInRadians, x0, x1, y0, y1);
		y2 = (int) calculateY2(angleInRadians, x0, x1, y0, y1);
		
		fletching.setRotate(angle);
		fletching.setTranslateX(x2 + xPosition);
		fletching.setTranslateY(y2 + yPosition);
		
		x1 = (int) head.getLayoutX();
		y1 = (int) head.getLayoutY();
		
		x0 = -88;
		y0 = -5;
		
		x2 = (int) calculateX2(angleInRadians, x0, x1, y0, y1);
		y2 = (int) calculateY2(angleInRadians, x0, x1, y0, y1);
		
		head.setRotate(angle);
		head.setTranslateX(x2 + xPosition);
		head.setTranslateY(y2 + yPosition);
		*/
	}
	
	private double calculateX2(double angleInRadians, int x0, int x1, int y0, int y1) {
		return (x0 + (x1-x0) * Math.cos(angleInRadians) - (y1-y0) * Math.sin(angleInRadians));
	}
	
	private double calculateY2(double angleInRadians, int x0, int x1, int y0, int y1) {
		return (y0 + (x1-x0) * Math.sin(angleInRadians) + (y1-y0) * Math.cos(angleInRadians));
	}
	
	public Line getShaft() {
		return shaft;
	}
	
	/*
	
	public ImageView getArrow() {
		return arrow;
	}
	
	
	
	public Polygon getFletching() {
		return fletching;
	}

	public Polygon getHead() {
		return head;
	}
	*/
}
