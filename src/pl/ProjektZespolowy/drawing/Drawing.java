package pl.ProjektZespolowy.drawing;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Drawing {

	private Stage primaryStage;
	private Scene primaryScene;
	private AnchorPane pane;
	
	//private Archer archer;
	private ImageView archer = new ImageView("/pl/ProjektZespolowy/resources/archer.png");
	
	private Arc arc;
	
	public Drawing() {
		
	}
	
	public void buildScene() {
		Rectangle frame = new Rectangle(10, 10, 870, 580);
		frame.setFill(Color.WHITE);
		frame.setStroke(Color.BLACK);
		
		Rectangle ground = new Rectangle(11, 450, 869, 140);
		ground.setFill(Color.DARKGREEN);
		
		Rectangle sky = new Rectangle(11, 11, 869, 459);
		sky.setFill(Color.LIGHTBLUE);
		
		pane.getChildren().addAll(frame, ground, sky);
		buildArcher();
		buildArc();
	}
	
	private void buildArcher() {
		archer.setTranslateY(-180);
		archer.setTranslateX(-400);
		
		archer.setScaleX(0.12);
		archer.setScaleY(0.12);
		
		
		pane.getChildren().add(archer);
		
		
		//this.archer = new Archer();
		//pane.getChildren().addAll(archer.getHead(), archer.getSpine(), archer.getHand1(), archer.getHand2(), archer.getLeg1(), archer.getLeg2());
		//archer.setArcherPosition(50, 425);
		//pane.getChildren().addAll(archer.)
	}
	
	private void buildArc() {
		this.arc = new Arc();
		pane.getChildren().addAll( arc.getArm(), arc.getString(), arc.getArrow());
		arc.setArcPosition(90, 370);
	}
	
	public void resetArcherPosition() {
		archer.setLayoutY(-530);
		archer.setLayoutX(0);
	}
	
	public void changeArcherPosition(int x, int y) {
		//archer.setArcherPosition(x, y);
		
		archer.setLayoutY(y - 530);
		//archer.setLayoutX(-400);
		
		arc.setArcPosition(x+40, y-160);
		
	}
	
	public void changeArcPosition(int x, int y) {
		arc.setArcPosition(x, y);
	}
	
	public void changeArrowPosition(int x, int y) {
		this.arc.getArrow().setTranslateX(x);
		this.arc.getArrow().setTranslateY(y);
		//this.arc.getArrow().changeArrowPosition(x, y);
	}
	
	public void changeArcIncline(double angle, int pow) {
		this.arc.setArcIncline(angle, pow);
	}

	public void changeArrowIncline(double angle, int pow) {
		arc.changeArrowIncline(angle, pow);
	}

	public void changeArrowIncline(double angle, int pow, int x, int y) {
		arc.changeArrowIncline(angle, pow, x, y);
	}
	
	public void drawLine() {
		int startX = 10;
        int startY = 500;
        int koniecX = 880;
        int koniecY = 500;
        
        
        
        Line line1 = new Line(startX, startY, koniecX, koniecY);
        line1.setStroke(Color.GREEN);
        
        pane.getChildren().addAll(line1);

        primaryStage.setScene(primaryScene);
        primaryStage.show();
	}
	    
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public void setPrimaryScene(Scene primaryScene) {
		this.primaryScene = primaryScene;
	}
	
	public void setPane(AnchorPane pane) {
		this.pane = pane;
	}

	public void setArcher(ImageView archer) {
		this.archer = archer;
	}

	public ImageView getArrow() {
		return arc.getArrow();
	}

	public ImageView getArcher() {
		return archer;
	}
	
	public Arc getArc() {
		return arc;
	}
	
	public void setArrow(Image arrow) {
		arc.setArrow(arrow);
	}

}
