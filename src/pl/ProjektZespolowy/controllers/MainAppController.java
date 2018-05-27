package pl.ProjektZespolowy.controllers;

import javafx.animation.AnimationTimer;
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

	private int quantity = 1;

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

		for (int i = 1; i <= 3; i++)
			images.add("/pl/ProjektZespolowy/resources/arrow" + i + ".png");

		typeOfArrow.getItems().addAll(images);
		typeOfArrow.setCellFactory(c -> new StatusListCell());
		typeOfArrow.setButtonCell(new StatusListCell());
		typeOfArrow.getSelectionModel().select(0);
	}

	class StatusListCell extends ListCell<String> {
		protected void updateItem(String item, boolean empty) {
			super.updateItem(item, empty);
			if (item != null) {
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
		
		double moc = power.getValue();
		double nachylenie = incline.getValue();
		
		int x = (int) drawing.getArrow().getTranslateX();
		int y = (int) drawing.getArrow().getTranslateY();
		
		Groty grot = Groty.NORMALNA;
		if(typeOfArrow.getSelectionModel().getSelectedIndex() == 0)
			grot = Groty.NORMALNA;
		if(typeOfArrow.getSelectionModel().getSelectedIndex() == 1)
			grot = Groty.SZYBKA;
		if(typeOfArrow.getSelectionModel().getSelectedIndex() == 2)
			grot = Groty.WOLNA;
		
		
		
		Strzala strzala = new Strzala(moc, -nachylenie, 0, 0, grot);
		Powietrze powietrze = new Powietrze();
		powietrze.setGestoscPowietrza(airDensity.getValue());
		Wiatr wiatr = new Wiatr();
		wiatr.setSilaWiatru(wind.getValue());
        Wzory wzory = new Wzory();
        
        quantity = 1;
        
		new AnimationTimer() {

			@Override
			public void handle(long now) {
				
				int nachylenie2 = -(int) strzala.getNachylenie();
				drawing.changeArrowIncline(nachylenie2, (int)power.getValue());

				double[] newPosition = wzory.nowapozycja(strzala, powietrze, wiatr, 0.04);
				newPosition[0] *= 10;
				newPosition[1] *= 10;
				
				int newArrowPositionX = (int) (newPosition[0] + x);
				int newArrowPositionY = (int) (-newPosition[1] + y);
				
				
				if (newArrowPositionX >= 0) {
					newArrowPositionX = 0;
					drawing.moveScene((int)-newPosition[0], 0);
				}
				if(newArrowPositionX <= -360) {
					newArrowPositionX = -360;
					drawing.moveScene((int)-newPosition[0], 0);
				}
				drawing.changeArrowPosition(newArrowPositionX, newArrowPositionY);
				drawing.moveMarker(newArrowPositionX + 340, 0);
				drawing.getDistance().setText("Odległość: " + (int)newPosition[0]/40 + " m");
				
				
				// po 4 przewinietych drzewach w prawo pojawia acziwment :D
				if (quantity == 4) {
					drawing.getDog().setVisible(true);
					drawing.buildAchievement();
				} else
					drawing.getDog().setVisible(false);
				
				// po 4 przewinietych drzewach w lewo pojawia sie acziwment
				if (quantity == -3) {
					drawing.getBag().setVisible(true);
					drawing.buildAchievement();
				} else
					drawing.getBag().setVisible(false);


				// po schowaniu sie obiektow z lewej strony ekranu sa przewijane
				// spowrotem na prawo
				if (drawing.getCloud1().getTranslateX() < -190) {
					drawing.getCloud1().setTranslateX(760);
				}
				if (drawing.getCloud2().getTranslateX() < -190) {
					drawing.getCloud2().setTranslateX(760);
				}
				if (drawing.getCloud3().getTranslateX() < -190) {
					drawing.getCloud3().setTranslateX(760);
				}
				
				if (drawing.getGrass1().getTranslateX() < -170) {
					drawing.getGrass1().setTranslateX(760);
				}
				if (drawing.getGrass2().getTranslateX() < -170) {
					drawing.getGrass2().setTranslateX(760);
				}
				if (drawing.getGrass3().getTranslateX() < -170) {
					drawing.getGrass3().setTranslateX(760);
				}
				if (drawing.getGrass4().getTranslateX() < -170) {
					drawing.getGrass4().setTranslateX(760);
				}
				
				if(drawing.getDog().getTranslateX() < -420)
					drawing.getDog().setTranslateX(680);
				
				if (drawing.getTree().getTranslateX() < -420) {
					drawing.getTree().setTranslateX(680);
					quantity++;
				}
				
				// po schowaniu sie obiektow z prawej strony ekranu sa przewijane
				// spowrotem na lewo
				if (drawing.getCloud1().getTranslateX() > 760) {
					drawing.getCloud1().setTranslateX(-200);
				}
				if (drawing.getCloud2().getTranslateX() > 760) {
					drawing.getCloud2().setTranslateX(-200);
				}
				if (drawing.getCloud3().getTranslateX() > 760) {
					drawing.getCloud3().setTranslateX(-200);
				}
				
				if (drawing.getGrass1().getTranslateX() > 770) {
					drawing.getGrass1().setTranslateX(-120);
				}
				if (drawing.getGrass2().getTranslateX() > 770) {
					drawing.getGrass2().setTranslateX(-120);
				}
				if (drawing.getGrass3().getTranslateX() > 770) {
					drawing.getGrass3().setTranslateX(-120);
				}
				if (drawing.getGrass4().getTranslateX() > 770) {
					drawing.getGrass4().setTranslateX(-120);
				}
				
				if(drawing.getBag().getTranslateX() > 770)
					drawing.getBag().setTranslateX(-400);
				
				
				if (drawing.getTree().getTranslateX() > 770) {
					drawing.getTree().setTranslateX(-400);
					quantity--;
				}
				
				if(newPosition[1]<0){
					
					if(newArrowPositionX > -362 && newArrowPositionX < -325) {
						drawing.getBleeding().setVisible(true);
						drawing.buildDeath();;
					} else
						drawing.getBleeding().setVisible(false);

					
		            stop();
		            power.setDisable(false);
		    		incline.setDisable(false);
		    		wind.setDisable(false);
		    		airDensity.setDisable(false);
		    		archerPosition.setDisable(false);
		    		typeOfArrow.setDisable(false);
		    		start.setDisable(false);
		    		
				}
				
				if(Double.isInfinite(newPosition[0]))
					drawing.buildOverflow();
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
		changeIncline();
		drawing.getBleeding().setLayoutY(position-80);
	}

	@FXML
	public void changePower() {
		double pow = power.getValue() * 0.4;
		double position = archerPosition.getValue();
		double angle = incline.getValue();
		drawing.changeArrowIncline(angle, (int) pow, (int) -pow - 320, (int) (position + 250));
	}

	@FXML
	public void changeIncline() {
		double angle = incline.getValue();
		double pow = (int) power.getValue() * 0.4;
		drawing.changeArcIncline(angle, (int) pow);
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
