package pl.ProjektZespolowy.controllers;

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
import pl.ProjektZespolowy.physic.Powietrze;
import pl.ProjektZespolowy.physic.Strzala;
import pl.ProjektZespolowy.physic.Wiatr;
import pl.ProjektZespolowy.physic.Wzory;

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
				double nachylenie = incline.getValue();
				
				int x = (int) drawing.getArrow().getTranslateX();
				int y = (int) drawing.getArrow().getTranslateY();

				Powietrze powietrze = new Powietrze();
				Strzala strzala = new Strzala(0.0001, nachylenie, x, y);
				Wiatr wiatr = new Wiatr();
				
				Wzory wzory = new Wzory();

				powietrze.setGestoscPowietrza(airDensity.getValue());
				wiatr.setSilaWiatru(wind.getValue());

				
				double speed = 0.31 + power.getValue() / 100;
				
				
				int newX = (int) wzory.otrzymajDrogeX(strzala, powietrze, wiatr, speed); // ostatnim parametrem mozna bedzie sterowac predkoscia strzaly po strzale
				int newY = (int) wzory.otrzymajDrogeY(strzala, powietrze, wiatr, speed);
				
				

				transition.setFromX(x);
				transition.setFromY(y);
				transition.setToX(x + newX);
				transition.setToY(y + newY);
				transition.play();

				double arcY = drawing.getArrow().getTranslateY();
				//System.out.println("ArcY: " + arcY);
				
				
				if((newY+y)<5) {
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
				if((newY+y)>arcY){
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
		
		drawing.changeArcIncline(0, (int) power.getValue());
		drawing.changeArcherPosition(50, 530);
		
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
