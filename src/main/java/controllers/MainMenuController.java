package main.java.controllers;

/**
@author VonSieger
**/

import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXML;

public class MainMenuController{
  @FXML
  private Button bjButton;
  @FXML
  private Button oabButton;
  @FXML
  private Button rouletteButton;
  @FXML
  private Button homeworkButton;

  private Scene popUpMenuScene, oabScene;
  private PopUpMenuController popUpMenuController;

  private static String OAB_HELP = "Let the bandit spin and stop each roller with a button.\n"+
  "Hope that you have luck, and win money.";

  public void bjButtonClicked(){

  }

  public void bjHelpButtonClicked(){

  }

  public void oabButtonClicked(){
    show(oabScene);
  }
  public void oabHelpButtonClicked(){
    popUpMenuController.setInfoText(OAB_HELP);
    show(popUpMenuScene);
  }
  public void rouletteButtonClicked(){

  }

  public void rouletteHelpButtonClicked(){

  }

  public void homeworkButtonClicked(){

  }

  public void homeworkHelpButtonClicked(){

  }

  private void help(String helpText){

  }

  public void setPopUpMenuScene(Scene popUpMenuScene){
    this.popUpMenuScene = popUpMenuScene;
  }

  public void setOABScene(Scene oabScene){
    this.oabScene = oabScene;
  }

  public void setPopUpMenuController(PopUpMenuController popUpMenuController){
    this.popUpMenuController = popUpMenuController;
  }

  private void show(Scene scene){
    Stage rootStage = (Stage) oabButton.getScene().getWindow();
    rootStage.setScene(scene);
    rootStage.show();
  }
}
