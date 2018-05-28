package pl.ProjektZespolowy.drawing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Drawing {

	private Stage primaryStage;
	private Scene primaryScene;
	private AnchorPane pane;
	
	private ArrayList<Integer> rankList = new ArrayList<>();
	
	private ImageView archer = new ImageView("/pl/ProjektZespolowy/resources/archer.png");
	private ImageView cloud1 = new ImageView("/pl/ProjektZespolowy/resources/cloud.png");
	private ImageView cloud2 = new ImageView("/pl/ProjektZespolowy/resources/cloud.png");
	private ImageView cloud3 = new ImageView("/pl/ProjektZespolowy/resources/cloud.png");
	private ImageView grass1 = new ImageView("/pl/ProjektZespolowy/resources/grass.png");
	private ImageView grass2 = new ImageView("/pl/ProjektZespolowy/resources/grass.png");
	private ImageView grass3 = new ImageView("/pl/ProjektZespolowy/resources/grass.png");
	private ImageView grass4 = new ImageView("/pl/ProjektZespolowy/resources/grass.png");
	private ImageView dog = new ImageView("/pl/ProjektZespolowy/resources/akcja.png");
	private ImageView tree = new ImageView("/pl/ProjektZespolowy/resources/drzewo2.png");
	private ImageView bag = new ImageView("/pl/ProjektZespolowy/resources/plus.png");
	private ImageView bleeding = new ImageView("/pl/ProjektZespolowy/resources/bleeding.png");
	private Arc arc;
	private Label distance, achievement, rank;
	
	private Polygon marker = new Polygon(90, 30, 105, 10, 120, 30);
	
	public Drawing() {
		
	}
	
	public void buildScene() {
		Rectangle frame = new Rectangle(0, 0, 900, 580);
		frame.setFill(Color.WHITE);
		frame.setStroke(Color.BLACK);
		
		Rectangle ground = new Rectangle(0, 450, 900, 140);
		ground.setFill(Color.GREEN);
		
		Rectangle sky = new Rectangle(0, 1, 900, 459);
		sky.setFill(Color.LIGHTBLUE);
		
		pane.getChildren().addAll(frame, ground, sky, marker);
		buildCloud();
		buildGrass();
		buildDog();
		buildTree();
		buildDistanceLabel();
		buildAchievement2();
		buildArcher();
		buildBleeding();
		buildArc();
	}
	
	public void removeScene() {
		pane.getChildren().remove(archer);
		pane.getChildren().remove(arc);
		pane.getChildren().remove(arc.getArrow());
		
		pane.getChildren().remove(cloud1);
		pane.getChildren().remove(cloud2);
		pane.getChildren().remove(cloud3);
		
		pane.getChildren().remove(grass1);
		pane.getChildren().remove(grass2);
		pane.getChildren().remove(grass3);
		pane.getChildren().remove(grass4);
		
		pane.getChildren().remove(dog);
		
		pane.getChildren().remove(tree);
		
		pane.getChildren().remove(distance);
		
		pane.getChildren().remove(achievement);
		
		pane.getChildren().remove(marker);
		
		pane.getChildren().remove(bag);
		
		pane.getChildren().remove(bleeding);
		
		pane.getChildren().remove(rank);
	}
	
	public void buildAchievement() {
		achievement = new Label("Odblokowano Acziwment");
		achievement.setTranslateX(150);
		achievement.setTranslateY(300);
		achievement.setFont(Font.font("System", FontWeight.BOLD, FontPosture.ITALIC, 50));
		achievement.setTextFill(Color.RED);
		
		pane.getChildren().add(achievement);
	}
	
	public void buildOverflow() {
		achievement = new Label("Przepełnienie!");
		achievement.setTranslateX(250);
		achievement.setTranslateY(300);
		achievement.setFont(Font.font("System", FontWeight.BOLD, FontPosture.ITALIC, 50));
		achievement.setTextFill(Color.RED);
		
		pane.getChildren().add(achievement);
	}
	
	public void buildDeath() {
		achievement = new Label("Umarłeś i nie żyjesz!");
		achievement.setTranslateX(250);
		achievement.setTranslateY(300);
		achievement.setFont(Font.font("System", FontWeight.BOLD, FontPosture.ITALIC, 50));
		achievement.setTextFill(Color.RED);
		
		pane.getChildren().add(achievement);
	}
	
	public void buildRank() {
		rank = new Label("Ranking:");
		rank.setTranslateX(750);
		rank.setTranslateY(10);
		rank.setFont(Font.font("System", FontWeight.BOLD, FontPosture.ITALIC, 25));
		rank.setTextFill(Color.BROWN);
		
		pane.getChildren().add(rank);
	}
	
	public void clearRank() {
		pane.getChildren().remove(rank);
		rankList.removeAll(rankList);
		buildRank();
	}
	
	public void removeAchievement() {
		pane.getChildren().remove(achievement);
	}
	
	private void buildDistanceLabel() {
		distance = new Label("Odległość: 0 m");
		distance.setTranslateX(20);
		distance.setTranslateY(30);
		distance.setFont(Font.font("System", FontWeight.BOLD, FontPosture.ITALIC, 24));
		distance.setTextFill(Color.BROWN);
		
		pane.getChildren().add(distance);
	}
	
	private void buildDog() {
		dog.setTranslateX(230);
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
		tree.setScaleY(0.6);
		
		pane.getChildren().add(tree);
	}
	
	public void buildAchievement2() {
		bag.setTranslateX(370);
		bag.setTranslateY(-40);
		
		bag.setScaleX(0.1);
		bag.setScaleY(0.1);
		
		bag.setVisible(false);
		
		pane.getChildren().add(bag);
	}
	
	public void buildBleeding() {
		bleeding.setTranslateX(-200);
		bleeding.setTranslateY(220);
		
		bleeding.setScaleX(0.2);
		bleeding.setScaleY(0.2);
		
		bleeding.setVisible(false);
		
		pane.getChildren().add(bleeding);
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
		archer.setLayoutY(y - 530);
		arc.setArcPosition(x+40, y-160);
	}
	
	public void changeArcPosition(int x, int y) {
		arc.setArcPosition(x, y);
	}
	
	public void changeArrowPosition(int x, int y) {
		this.arc.getArrow().setTranslateX(x);
		this.arc.getArrow().setTranslateY(y);
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
	
	public void moveScene(int x, int y) {
		int m = 70;
		if(x<0)
			archer.setTranslateX(archer.getTranslateX() + x/m);
		else
			archer.setTranslateX(archer.getTranslateX() + x/m);
		
		arc.moveArc(x/m);
		cloud1.setTranslateX(cloud1.getTranslateX() + x/m);
		cloud2.setTranslateX(cloud2.getTranslateX() + x/m);
		cloud3.setTranslateX(cloud3.getTranslateX() + x/m);
		
		grass1.setTranslateX(grass1.getTranslateX() + x/m);
		grass2.setTranslateX(grass2.getTranslateX() + x/m);
		grass3.setTranslateX(grass3.getTranslateX() + x/m);
		grass4.setTranslateX(grass4.getTranslateX() + x/m);
		
		tree.setTranslateX(tree.getTranslateX() + x/m);
		
		dog.setTranslateX(dog.getTranslateX() + x/m);
		
		bag.setTranslateX(bag.getTranslateX() + x/m);
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
	
	public void moveMarker(int x, int y) {
		marker.setTranslateX(x);
		marker.setTranslateY(y);
	}
	
	public void addToRankList(Integer value) {
		rankList.add(value);
		int i = 0;
		String ranking = "";
		java.util.Collections.sort(rankList);
		java.util.Collections.reverse(rankList);
		Iterator<Integer> it = rankList.iterator();
		
		while (i < 3 && it.hasNext()) 
			ranking += "\n" + ++i + ": " + it.next() + " m";
		
		rank.setText("Ranking:" + ranking);
	}
	
	public void refreshRank() {
		int i = 0;
		String ranking = "";
		java.util.Collections.sort(rankList);
		java.util.Collections.reverse(rankList);
		Iterator<Integer> it = rankList.iterator();
		
		while (i < 3 && it.hasNext()) 
			ranking += "\n" + ++i + ": " + it.next() + " m";
		
		rank.setText("Ranking:" + ranking);
		pane.getChildren().add(rank);
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

	public Label getDistance() {
		return distance;
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

	public ImageView getBag() {
		return bag;
	}

	public ImageView getBleeding() {
		return bleeding;
	}
	
}
