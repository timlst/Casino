package main.java.controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

  Scene startScene, mainMenu, popUpMenu, oab;
  MainMenuController mainMenuController;
  PopUpMenuController popUpMenuController;
  AnimationController startAnimationController;

  private static String WELCOME_TEXT = "This is a Casino.\nYou can lose a lot of money here.\nBut it is much cooler than playing Arcade-Games.";

  @Override
  public void start(Stage primaryStage) throws Exception{
    FXMLLoader loader = new FXMLLoader();
    //mainMenuScene
    Pane mainMenuPane = loader.load(getClass().getResource("/main/resources/view/MainMenu.fxml").openStream());
    mainMenu = new Scene(mainMenuPane);
    mainMenuController = loader.getController();
    //popUpScene
    loader = new FXMLLoader();
    Pane popUpMenuPane = loader.load(getClass().getResource("/main/resources/view/PopUpMenu.fxml").openStream());
    popUpMenu = new Scene(popUpMenuPane);
    popUpMenuController = loader.getController();
    popUpMenuController.setInfoText(WELCOME_TEXT);

    //start Scene
    loader = new FXMLLoader();
    Pane startPane = loader.load(getClass().getResource("/main/resources/view/StartAnimation.fxml").openStream());
    startScene = new Scene(startPane);
    startAnimationController = loader.getController();

    popUpMenuController.setReturnScene(mainMenu);
    mainMenuController.setPopUpMenuScene(popUpMenu);
    mainMenuController.setPopUpMenuController(popUpMenuController);
    startAnimationController.setNext(popUpMenu);

    //OAB
    loader = new FXMLLoader();
    Pane oabPane = loader.load(getClass().getResource("/main/resources/view/Scene.fxml").openStream());
    oab = new Scene(oabPane);

    mainMenuController.setOABScene(oab);

    primaryStage.setTitle("Casino");
    primaryStage.setScene(startScene);
    primaryStage.show();
    startAnimationController.animate();
  }

  public static void main(String[] args){
    launch(args);
  }

}
