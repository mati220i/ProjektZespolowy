package pl.ProjektZespolowy.drawing;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Drawing {

	private Stage primaryStage;
	private Scene primaryScene;
	private AnchorPane pane;
	
	//private Archer archer;
	private ImageView archer = new ImageView("/pl/ProjektZespolowy/resources/archer.png");
	private ImageView cloud1 = new ImageView("/pl/ProjektZespolowy/resources/cloud.png");
	private ImageView cloud2 = new ImageView("/pl/ProjektZespolowy/resources/cloud.png");
	private ImageView cloud3 = new ImageView("/pl/ProjektZespolowy/resources/cloud.png");
	private ImageView grass1 = new ImageView("/pl/ProjektZespolowy/resources/grass.png");
	private ImageView grass2 = new ImageView("/pl/ProjektZespolowy/resources/grass.png");
	private ImageView grass3 = new ImageView("/pl/ProjektZespolowy/resources/grass.png");
	private ImageView grass4 = new ImageView("/pl/ProjektZespolowy/resources/grass.png");
	private ImageView dog = new ImageView("/pl/ProjektZespolowy/resources/akcjakupa.png");
	private ImageView tree = new ImageView("/pl/ProjektZespolowy/resources/drzewo2.png");
	private Arc arc;
	
	private Polygon marker = new Polygon(90, 30, 105, 10, 120, 30);
	
	public Drawing() {
		
	}
	
	public void buildScene() {
		Rectangle frame = new Rectangle(0, 10, 900, 580);
		frame.setFill(Color.WHITE);
		frame.setStroke(Color.BLACK);
		
		Rectangle ground = new Rectangle(0, 450, 900, 140);
		ground.setFill(Color.GREEN);
		
		Rectangle sky = new Rectangle(0, 11, 900, 459);
		sky.setFill(Color.LIGHTBLUE);
		
		pane.getChildren().addAll(frame, ground, sky, marker);
		buildArcher();
		buildArc();
		buildCloud();
		buildGrass();
		buildDog();
		buildTree();
	}
	
	public void removeScene() {
		pane.getChildren().remove(archer);
		pane.getChildren().remove(arc);
		
		pane.getChildren().remove(cloud1);
		pane.getChildren().remove(cloud2);
		pane.getChildren().remove(cloud3);
		
		pane.getChildren().remove(grass1);
		pane.getChildren().remove(grass2);
		pane.getChildren().remove(grass3);
		pane.getChildren().remove(grass4);
		
		pane.getChildren().remove(dog);
		
		pane.getChildren().remove(tree);
		
		pane.getChildren().remove(marker);
	}
	
	private void buildDog() {
		dog.setTranslateX(225);
		dog.setTranslateY(270);
		
		dog.setScaleX(0.25);
		dog.setScaleY(0.25);
		
		dog.setVisible(false);
		
		pane.getChildren().add(dog);
	}
	
	private void buildTree() {
		tree.setTranslateX(230);
		tree.setTranslateY(-60);
		
		tree.setScaleX(0.5);
		tree.setScaleY(0.5);
		
		pane.getChildren().add(tree);
	}
	
	private void buildCloud() {
		cloud1.setTranslateX(0);
		cloud1.setTranslateY(-100);
		cloud1.setScaleX(0.22);
		cloud1.setScaleY(0.22);
		
		cloud2.setTranslateX(310);
		cloud2.setTranslateY(-50);
		cloud2.setScaleX(0.32);
		cloud2.setScaleY(0.32);
		
		cloud3.setTranslateX(510);
		cloud3.setTranslateY(-90);
		cloud3.setScaleX(0.42);
		cloud3.setScaleY(0.42);
		
		pane.getChildren().addAll(cloud1, cloud2, cloud3);
	}
	
	private void buildGrass() {
		grass1.setTranslateX(70);
		grass1.setTranslateY(450);
		grass1.setScaleX(0.22);
		grass1.setScaleY(0.22);
		
		grass2.setTranslateX(310);
		grass2.setTranslateY(420);
		grass2.setScaleX(0.27);
		grass2.setScaleY(0.27);
		
		grass3.setTranslateX(510);
		grass3.setTranslateY(380);
		grass3.setScaleX(0.34);
		grass3.setScaleY(0.34);
		
		grass4.setTranslateX(600);
		grass4.setTranslateY(440);
		grass4.setScaleX(0.19);
		grass4.setScaleY(0.19);
		
		pane.getChildren().addAll(grass1, grass2, grass3, grass4);
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
	
	public void moveCamera(int x, int y) {
		archer.setTranslateX(-400 + x);
		archer.setTranslateY(-180 + y);
		
	}
	
	public void moveMarker(int x, int y) {
		marker.setTranslateX(x);
		marker.setTranslateY(y);
	}
	
	
	    
	public ImageView getCloud1() {
		return cloud1;
	}

	public ImageView getCloud2() {
		return cloud2;
	}

	public ImageView getCloud3() {
		return cloud3;
	}

	public ImageView getGrass1() {
		return grass1;
	}

	public ImageView getGrass2() {
		return grass2;
	}

	public ImageView getGrass3() {
		return grass3;
	}

	public ImageView getGrass4() {
		return grass4;
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

	public ImageView getDog() {
		return dog;
	}

	public ImageView getTree() {
		return tree;
	}

	
}
