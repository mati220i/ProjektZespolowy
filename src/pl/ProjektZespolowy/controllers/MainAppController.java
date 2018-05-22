package pl.ProjektZespolowy.controllers;

import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import pl.ProjektZespolowy.drawing.Drawing;
import pl.ProjektZespolowy.jPhysics.*;

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

	private int i = 0, quantity = 1, tempArrowY = 0;
	
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
		System.out.println("--------------------");
		new AnimationTimer() {
			
			@Override
			public void handle(long now) {
				double moc = power.getValue() / 100;
				double nachylenie = incline.getValue();
				
				
				
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
				TranslateTransition transitionForTree = new TranslateTransition(new Duration(1), drawing.getTree());
				TranslateTransition transitionForDog = new TranslateTransition(new Duration(1), drawing.getDog());
				
				
				
				int x = (int) drawing.getArrow().getTranslateX();
				int y = (int) drawing.getArrow().getTranslateY();
				
				// tymczasowa wartosc y strzaly, ktora nie jest tracona pomimo zatrzymania (centrowania) kamery na srodku ekranu
				tempArrowY = y;
				
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
				
				int xTree = (int) drawing.getTree().getTranslateX();
				int yTree = (int) drawing.getTree().getTranslateY();
				int xDog = (int) drawing.getDog().getTranslateX();
				int yDog = (int) drawing.getDog().getTranslateY();
				
				
				Powietrze powietrze = new Powietrze();
				Strzala strzala = new Strzala(moc, nachylenie, x, y, Groty.NORMALNA);
				
				
				
				
				// obiekt drugiej "strzaly" potrzebny do poruszania tlem, strzala oryginalna zatrzymuje sie na srodku ekranu (centrowanie)
				Strzala strzala2 = new Strzala(moc, nachylenie, x, tempArrowY, Groty.NORMALNA);
				
				Wiatr wiatr = new Wiatr();
				
				Wzory wzory = new Wzory();
				
				powietrze.setGestoscPowietrza(airDensity.getValue());
				wiatr.setSilaWiatru(wind.getValue());

				//double speed = 100;//0.31 + power.getValue() / 100;
				i++;
				//TODO zamieniłem na nowe funkcje,stare w komentarzach jest nowa działajaca fizyka, tylko że nie działa ..... sprawdzone w jPhisics main działa okej
                int nachylenie2 = (int) strzala.getNachylenie();
//				int nachylenie2 = (int) wzory.otrzymajNachylenie(strzala, powietrze, wiatr);
				// to ma podobno zmieniac nachylenie ale jest cos spierdolone i nie dziala
				drawing.changeArrowIncline(nachylenie2, (int) power.getValue(), x, y);


				double[] newPosition = wzory.nowapozycja(strzala,powietrze,wiatr,0.01);

                int nextX = (int) newPosition[0];
				int nextY = (int) newPosition[1];
//				int nextX = (int) wzory.otrzymajDrogeX(strzala, powietrze, wiatr, i);
//				int nextY = (int) wzory.otrzymajDrogeY(strzala, powietrze, wiatr, i/10);
				
				int newX = nextX + x; 
				int newY = nextY + y;
				
				// dane potrzebne do przesuwania tlem, osobne, powniewaz oryginalna strzala zatrzymuje sie w centrum (centrowanie)
                int nextY2 = (int) newPosition[1];
//				int nextY2 = (int) wzory.otrzymajDrogeY(strzala2, powietrze, wiatr, i/10);
				int newY2 = nextY2 + tempArrowY;
				
				
				// tymczasowe do naprawienia fizyki, powoduje sztuczne opadanie strzaly po jakims czasie
				if(newY <= -100) {
					newY += 5 - nextY;
				}
				System.out.println("nextX: " + nextX + " nextY: " + nextY);
				drawing.getDistance().setText("Odległość: " + nextX + " m");
				
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
				
				int newXTree = xTree;
				int newYTree = yTree;
				int newXDog = xDog;
				int newYDog = yDog;
				
				// kolejne pozycje do przesuniecia tla
				if(newY2 < 200) {
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
					newXTree -= nextX;
					newXDog -= nextX;
				}

				// po 4 przewinietych drzewach pojawia sie srajacy pies za drzewem, taki acziwment :D 
				if(quantity == 4) {
					drawing.getDog().setVisible(true);
					drawing.buildAchievement();
				} else
					drawing.getDog().setVisible(false);
				
				
				// po schowaniu sie obiektow z lewej strony ekranu sa przewijane spowrotem na prawo
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
				
				if(xTree < -420){
					newXTree += 1150;
					quantity++;
				}
				if(xDog < -420){
					newXDog += 1150;
				}
				
				// po osiagnieciu srodka ekranu strzala nie leci dalej w bok (centrowanie)
				if(drawing.getArrow().getTranslateX() >= 0) {
					newX = 0;
				}
				
				// zmiana pozycja znacznika
				drawing.moveMarker(x+340, 0);
								
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

				transitionForTree.setFromX(xTree);
				transitionForTree.setFromY(yTree);
				transitionForTree.setToX(newXTree);
				transitionForTree.setToY(newYTree);
				transitionForTree.play();
				transitionForDog.setFromX(xDog);
				transitionForDog.setFromY(yDog);
				transitionForDog.setToX(newXDog);
				transitionForDog.setToY(newYDog);
				transitionForDog.play();

				
				
				/*
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
				}*/
				
				
				// TODO naprawic ten szit zeby sie zatrzymywalo do dolu lucznika
				// tymczasowo zatrzymuje sie w losowym miejscu na ziemi ale dziaï¿½a :D
				if((newY)> 450){
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
