package pl.ProjektZespolowy.controllers;

import java.io.IOException;

import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ListCell;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import pl.ProjektZespolowy.drawing.Drawing;
import pl.ProjektZespolowy.jPhysic.Powietrze;
import pl.ProjektZespolowy.jPhysic.Strzala;
import pl.ProjektZespolowy.jPhysic.Wiatr;
import pl.ProjektZespolowy.jPhysic.Wzory;

public class MainAppController {
	@FXML
	private Slider power, incline, wind, airDensity, archerPosition;
	@FXML
	private AnchorPane pane;
	@FXML
	private ComboBox<String> typeOfArrow;
	@FXML
	private Button start, reset;
	
	private Stage primaryStage;
	private Scene primaryScene;

	private Drawing drawing = new Drawing();

	@FXML
	public void initialize() {
		wind.setMin(-100);
		incline.setMin(-90);
	}

	public void refresh() {
		drawing.setPrimaryStage(primaryStage);
		drawing.setPrimaryScene(primaryScene);
		drawing.setPane(pane);
		drawing.buildScene();
		
		final ObservableList<String> images = FXCollections.observableArrayList();
		
		for(int i=1; i<=3; i++)
			images.add("/pl/ProjektZespolowy/resources/arrow" + i + ".png");
        
		typeOfArrow.getItems().addAll(images);
		typeOfArrow.setCellFactory(c -> new StatusListCell());
		typeOfArrow.setButtonCell(new StatusListCell());
		typeOfArrow.getSelectionModel().select(0);
		//typeOfArrow.setCellFactory(value);
		
		// drawing.changeArrowPosition(400, 400);
		//drawing.changeArcPosition(90, 200);
	}
    
    class StatusListCell extends ListCell<String> {
        protected void updateItem(String item, boolean empty){
            super.updateItem(item, empty);
            if(item!=null){
                ImageView imageView = new ImageView(new Image(item));
                imageView.setFitWidth(180);
                imageView.setFitHeight(40);
                setGraphic(imageView);
            }
        }

    }
 
    @FXML
    public void setArrow() {
    	Image arrow = new Image(typeOfArrow.getSelectionModel().getSelectedItem());
    	drawing.setArrow(arrow);
    }

	@FXML
	public void start() throws InterruptedException {
		power.setDisable(true);
		incline.setDisable(true);
		wind.setDisable(true);
		airDensity.setDisable(true);
		archerPosition.setDisable(true);
		typeOfArrow.setDisable(true);
		start.setDisable(true);
		//reset.setDisable(true);
		
		
		new AnimationTimer() {

			@Override
			public void handle(long now) {
				TranslateTransition transition = new TranslateTransition(new Duration(1), drawing.getArrow());
				TranslateTransition transitionForArcher = new TranslateTransition(new Duration(1), drawing.getArcher());
				TranslateTransition transitionForCloud1 = new TranslateTransition(new Duration(1), drawing.getCloud1());
				TranslateTransition transitionForCloud2 = new TranslateTransition(new Duration(1), drawing.getCloud2());
				TranslateTransition transitionForCloud3 = new TranslateTransition(new Duration(1), drawing.getCloud3());
				TranslateTransition transitionForGrass1 = new TranslateTransition(new Duration(1), drawing.getGrass1());
				TranslateTransition transitionForGrass2 = new TranslateTransition(new Duration(1), drawing.getGrass2());
				TranslateTransition transitionForGrass3 = new TranslateTransition(new Duration(1), drawing.getGrass3());
				TranslateTransition transitionForGrass4 = new TranslateTransition(new Duration(1), drawing.getGrass4());
				TranslateTransition transitionForArcArm = new TranslateTransition(new Duration(1), drawing.getArc().getArm());
				TranslateTransition transitionForArcString = new TranslateTransition(new Duration(1), drawing.getArc().getString());
				
				
				double nachylenie = incline.getValue();
				
				int x = (int) drawing.getArrow().getTranslateX();
				int y = (int) drawing.getArrow().getTranslateY();
				
				int xArcher = (int) drawing.getArcher().getTranslateX();
				int yArcher = (int) drawing.getArcher().getTranslateY();
				
				int xCloud1 = (int) drawing.getCloud1().getTranslateX();
				int yCloud1 = (int) drawing.getCloud1().getTranslateY();
				int xCloud2 = (int) drawing.getCloud2().getTranslateX();
				int yCloud2 = (int) drawing.getCloud2().getTranslateY();
				int xCloud3 = (int) drawing.getCloud3().getTranslateX();
				int yCloud3 = (int) drawing.getCloud3().getTranslateY();
				
				int xGrass1 = (int) drawing.getGrass1().getTranslateX();
				int yGrass1 = (int) drawing.getGrass1().getTranslateY();
				int xGrass2 = (int) drawing.getGrass2().getTranslateX();
				int yGrass2 = (int) drawing.getGrass2().getTranslateY();
				int xGrass3 = (int) drawing.getGrass3().getTranslateX();
				int yGrass3 = (int) drawing.getGrass3().getTranslateY();
				int xGrass4 = (int) drawing.getGrass4().getTranslateX();
				int yGrass4 = (int) drawing.getGrass4().getTranslateY();
				
				int xArcArm = (int) drawing.getArc().getArm().getTranslateX();
				int yArcArm = (int) drawing.getArc().getArm().getTranslateY();
				int xArcString = (int) drawing.getArc().getString().getTranslateX();
				int yArcString = (int) drawing.getArc().getString().getTranslateY();
				
				
				Powietrze powietrze = new Powietrze();
				Strzala strzala = new Strzala(0.01, nachylenie, x, y);
				Wiatr wiatr = new Wiatr();
				
				Wzory wzory = new Wzory();

				powietrze.setGestoscPowietrza(airDensity.getValue());
				wiatr.setSilaWiatru(wind.getValue());

				System.out.println("xCloud1 po inicjowaniu: " + xCloud1);
				double speed = 0.31 + power.getValue() / 100;

				// ostatnim parametrem mozna bedzie sterowac predkoscia strzaly po strzale
				int nextX = (int) wzory.otrzymajDrogeX(strzala, powietrze, wiatr, speed * 1000);
				int nextY = (int) wzory.otrzymajDrogeY(strzala, powietrze, wiatr, speed );
				int newX = nextX + x; 
				int newY = nextY + y;
				
				int newXArcher = xArcher;
				int newYArcher = yArcher;
				
				int newXCloud1 = xCloud1;
				int newYCloud1 = yCloud1;
				int newXCloud2 = xCloud2;
				int newYCloud2 = yCloud2;
				int newXCloud3 = xCloud3;
				int newYCloud3 = yCloud3;
				
				int newXGrass1 = xGrass1;
				int newYGrass1 = yGrass1;
				int newXGrass2 = xGrass2;
				int newYGrass2 = yGrass2;
				int newXGrass3 = xGrass3;
				int newYGrass3 = yGrass3;
				int newXGrass4 = xGrass4;
				int newYGrass4 = yGrass4;
				
				int newXArcArm = xArcArm;
				int newYArcArm = yArcArm;
				int newXArcString = xArcString;
				int newYArcString = yArcString;
				
				System.out.println("xCloud1 po nadpisaniu: " + xCloud1);
				if(newY < 200) {
					newXArcher -= nextX;
					newXCloud1 -= nextX;
					newXCloud2 -= nextX;
					newXCloud3 -= nextX;
					newXGrass1 -= nextX;
					newXGrass2 -= nextX;
					newXGrass3 -= nextX;
					newXGrass4 -= nextX;
					newXArcArm -= nextX;
					newXArcString -= nextX;
				}
				System.out.println("xCloud1 po zmianie polozenia: " + xCloud1);
				
				if(xCloud1 < -170){
					newXCloud1 += 930;
				}
				if(xCloud2 < -170){
					newXCloud2 += 940;
				}
				if(xCloud3 < -170){
					newXCloud3 += 940;
				}
				
				if(xGrass1 < -170){
					newXGrass1 += 950;
				}
				if(xGrass2 < -170){
					newXGrass2 += 950;
				}
				if(xGrass3 < -170){
					newXGrass3 += 950;
				}
				if(xGrass4 < -170){
					newXGrass4 += 950;
				}
				
				System.out.println("xCloud1 po zmianie " + xCloud1 + "\n-----------------------------------------------------------------------------");
				
				

				transition.setFromX(x);
				transition.setFromY(y);
				transition.setToX(newX);
				transition.setToY(newY);
				transition.play();
				
				transitionForArcher.setFromX(xArcher);
				transitionForArcher.setFromY(yArcher);
				transitionForArcher.setToX(newXArcher);
				transitionForArcher.setToY(newYArcher);
				transitionForArcher.play();

				transitionForCloud1.setFromX(xCloud1);
				transitionForCloud1.setFromY(yCloud1);
				transitionForCloud1.setToX(newXCloud1);
				transitionForCloud1.setToY(newYCloud1);
				transitionForCloud1.play();
				transitionForCloud2.setFromX(xCloud2);
				transitionForCloud2.setFromY(yCloud2);
				transitionForCloud2.setToX(newXCloud2);
				transitionForCloud2.setToY(newYCloud2);
				transitionForCloud2.play();
				transitionForCloud3.setFromX(xCloud3);
				transitionForCloud3.setFromY(yCloud3);
				transitionForCloud3.setToX(newXCloud3);
				transitionForCloud3.setToY(newYCloud3);
				transitionForCloud3.play();

				transitionForGrass1.setFromX(xGrass1);
				transitionForGrass1.setFromY(yGrass1);
				transitionForGrass1.setToX(newXGrass1);
				transitionForGrass1.setToY(newYGrass1);
				transitionForGrass1.play();
				transitionForGrass2.setFromX(xGrass2);
				transitionForGrass2.setFromY(yGrass2);
				transitionForGrass2.setToX(newXGrass2);
				transitionForGrass2.setToY(newYGrass2);
				transitionForGrass2.play();
				transitionForGrass3.setFromX(xGrass3);
				transitionForGrass3.setFromY(yGrass3);
				transitionForGrass3.setToX(newXGrass3);
				transitionForGrass3.setToY(newYGrass3);
				transitionForGrass3.play();
				transitionForGrass4.setFromX(xGrass4);
				transitionForGrass4.setFromY(yGrass4);
				transitionForGrass4.setToX(newXGrass4);
				transitionForGrass4.setToY(newYGrass4);
				transitionForGrass4.play();

				transitionForArcArm.setFromX(xArcArm);
				transitionForArcArm.setFromY(yArcArm);
				transitionForArcArm.setToX(newXArcArm);
				transitionForArcArm.setToY(newYArcArm);
				transitionForArcArm.play();
				transitionForArcString.setFromX(xArcString);
				transitionForArcString.setFromY(yArcString);
				transitionForArcString.setToX(newXArcString);
				transitionForArcString.setToY(newYArcString);
				transitionForArcString.play();

				
				
				double arcY = drawing.getArrow().getTranslateY();
				//System.out.println("ArcY: " + arcY);
				
				
				if((newY)<-250) {
					stop();
					power.setDisable(false);
					incline.setDisable(false);
					wind.setDisable(false);
					airDensity.setDisable(false);
					archerPosition.setDisable(false);
					typeOfArrow.setDisable(false);
					start.setDisable(false);
					reset.setDisable(false);
				}
				
				// TODO naprawic ten szit zeby sie zatrzymywalo do dolu lucznika
				if((newY)>arcY){
					stop();
					drawing.getArrow().setTranslateX(70);
					System.out.println("szit: " + arcY);
					drawing.getArrow().setTranslateY(arcY);
					
					power.setDisable(false);
					incline.setDisable(false);
					wind.setDisable(false);
					airDensity.setDisable(false);
					archerPosition.setDisable(false);
					typeOfArrow.setDisable(false);
					start.setDisable(false);
					reset.setDisable(false);
					
					
					
				}
				
			}
		}.start();

	}

	@FXML
	public void reset() {
		power.setValue(25);
		incline.setValue(0);
		wind.setValue(0);
		airDensity.setValue(2);
		archerPosition.setValue(80);
		
		drawing.removeScene();
		drawing.buildScene();
	}
	
	@FXML
	public void change() throws InterruptedException {
		
	}

	@FXML
	public void changePosition() {
		double position = archerPosition.getValue();
		
		
		drawing.changeArcherPosition(50, (int) position + 450);

		double angle = incline.getValue();
		double pow = power.getValue() * 0.4;
		drawing.changeArcIncline(angle, (int) pow);
		drawing.changeArrowPosition((int) -pow - 320, (int) position + 250);
		drawing.changeArrowIncline(angle, (int) pow);
		//changePower();
		changeIncline();
	
	}

	@FXML
	public void changePower() {
		double pow = power.getValue() * 0.4;
		double position = archerPosition.getValue();
		double angle = incline.getValue();
		//double arrowPosition = drawing.getArrow().getTranslateY();

		//drawing.changeArrowPosition((int) -pow - 320, (int) position + 250);
		//drawing.changeArrowPosition((int) -pow - 320, (int) arrowPosition);
		//drawing.changeArrowIncline(angle, (int)pow);
		drawing.changeArrowIncline(angle, (int) pow, (int)-pow-320, (int)(position + 250));
		//changeIncline();
	}

	@FXML
	public void changeIncline() {
		double angle = incline.getValue();
		double pow = (int) power.getValue() * 0.4;
		drawing.changeArcIncline(angle, (int) pow);
		//changePosition();
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

}
