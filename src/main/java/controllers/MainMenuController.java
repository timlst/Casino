package main.java.controllers;

/**
@author VonSieger
**/

import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainMenuController{
  public Button bjButton;
  public Button oabButton;
  public Button rouletteButton;
  public Button homeworkButton;

  private Scene popUpMenuScene;
  private PopUpMenuController popUpMenuController;

  private static String OAB_HELP = "Let the bandit spin and stop each roller with a button.\n"+
  "Hope that you have luck, and win money.";

  public void bjButtonClicked(){

  }

  public void bjHelpButtonClicked(){

  }

  public void oabButtonClicked(){

  }
  public void oabHelpButtonClicked(){
    popUpMenuController.setInfoText(OAB_HELP);
    Stage rootStage = (Stage) oabButton.getScene().getWindow();
    rootStage.setScene(popUpMenuScene);
    rootStage.show();
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

  public void setPopUpMenuController(PopUpMenuController popUpMenuController){
    this.popUpMenuController = popUpMenuController;
  }
}
