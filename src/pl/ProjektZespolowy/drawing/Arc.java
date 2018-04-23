package pl.ProjektZespolowy.drawing;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;

public class Arc {
	private Polyline arm;
	private Line string;
	//private Arrow arrow;
	
	private ImageView arrow = new ImageView("/pl/ProjektZespolowy/resources/arrow1.png");
	
	
	
	// Real position on map
	private int xPosition, yPosition, xArrowPos, yArrowPos;
	
	public Arc() {
		arm = new Polyline(10, 10, 40, 40, 40, 90, 10, 120);
		string = new Line(12, 10, 12, 120);
		arm.setStrokeWidth(4);
		string.setStrokeWidth(2);
		//arrow = new Arrow();
		arrow.setScaleX(0.15);
		arrow.setScaleY(0.15);
		//arrow.setTranslateX(70);
		//arrow.setTranslateY(440);
	}

	public void setArcPosition(int x, int y) {
		arm.setTranslateX(x);
		arm.setTranslateY(y);
		string.setTranslateX(x);
		string.setTranslateY(y);
		//arrow.changeArrowPosition(x-10, y+35);
		arrow.setTranslateX(x-430);
		arrow.setTranslateY(y-40);
		
		xPosition = (int) arm.getTranslateX();
		yPosition = (int) arm.getTranslateY();
		
		xArrowPos = (int) arrow.getTranslateX();
		yArrowPos = (int) arrow.getTranslateY();
	}
	
	public void setArcIncline(double angle, int pow) {
		int x1 = (int) arm.getLayoutX();
		int y1 = (int) arm.getLayoutY();
		
		double angleInRadians = Math.toRadians(angle);

		int x0 = -25;
		int y0 = -5;
		
		int x2 = (int) calculateX2(angleInRadians, x0, x1, y0, y1);
		int y2 = (int) calculateY2(angleInRadians, x0, x1, y0, y1);
		
		arm.setRotate(angle);
		arm.setTranslateX(x2 + xPosition);
		arm.setTranslateY(y2 + yPosition);

		x0 = -13;
		y0 = -5;
		
		x1 = (int) string.getLayoutX();
		y1 = (int) string.getLayoutY();
		
		x2 = (int) calculateX2(angleInRadians, x0, x1, y0, y1);
		y2 = (int) calculateY2(angleInRadians, x0, x1, y0, y1);
		
		string.setRotate(angle);
		string.setTranslateX(x2 + xPosition);
		string.setTranslateY(y2 + yPosition);
		
		changeArrowIncline(angle, pow);
		
		
		
		
	}
	
	public void changeArrowIncline(double angle, double pow) {
		double angleInRadians = Math.toRadians(angle);
		
		//xArrowPos = (int) arrow.getTranslateX() + 430;
		//yArrowPos = (int) arrow.getTranslateY() + 40;
		//-339 y 328
		
		int x1 = (int) arrow.getLayoutX();
		int y1 = (int) arrow.getLayoutY();
		
		int x0 = (int) (-30 + pow/2);
		int y0 = -10;
		
		int x2 = (int) calculateX2(angleInRadians, x0, x1, y0, y1);
		int y2 = (int) calculateY2(angleInRadians, x0, x1, y0, y1);
		
		//System.out.println("pos " + xArrowPos + " " + yArrowPos);
		//System.out.println("bicz: " + (x2 + xArrowPos) + " y " + (y2 + yArrowPos));
		
		arrow.setRotate(angle);
		arrow.setTranslateX(x2 + xArrowPos);
		arrow.setTranslateY(y2 + yArrowPos);
	}
	
	public void changeArrowIncline(double angle, double pow, int x, int y) {
		double angleInRadians = Math.toRadians(angle);
		
		//xArrowPos = (int) arrow.getTranslateX() + 430;
		//yArrowPos = (int) arrow.getTranslateY() + 40;
		//-339 y 328
		
		int x1 = (int) arrow.getLayoutX();
		int y1 = (int) arrow.getLayoutY();
		
		int x0 = (int)pow-32;
		int y0 = 15;
		
		int x2 = (int) calculateX2(angleInRadians, x0, x1, y0, y1);
		int y2 = (int) calculateY2(angleInRadians, x0, x1, y0, y1);
		
		//System.out.println("pos " + xArrowPos + " " + yArrowPos);
		//System.out.println("bicz: " + (x2 + xArrowPos) + " y " + (y2 + yArrowPos));
		
		arrow.setRotate(angle);
		arrow.setTranslateX(x2 + x);
		arrow.setTranslateY(y2 + y);
	}
	
	private double calculateX2(double angleInRadians, int x0, int x1, int y0, int y1) {
		return (x0 + (x1-x0) * Math.cos(angleInRadians) - (y1-y0) * Math.sin(angleInRadians));
	}
	
	private double calculateY2(double angleInRadians, int x0, int x1, int y0, int y1) {
		return (y0 + (x1-x0) * Math.sin(angleInRadians) + (y1-y0) * Math.cos(angleInRadians));
	}
	
	public Polyline getArm() {
		return arm;
	}

	public Line getString() {
		return string;
	}

	public ImageView getArrow() {
		return arrow;
	}

	public void setArrow(Image arrow) {
		this.arrow.setImage(arrow);
		//this.arrow.setTranslateX(xArrowPos);
		//this.arrow.setTranslateY(yArrowPos);
		
	}
	
	
}
