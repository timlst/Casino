package main.java.controllers;

/**
@author VonSieger
**/

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FMXL;

public class PopUpMenuController{

  private Scene mainMenu;
  @FXML
  private Button closeButton;
  @FXML
  private Label infoLabel;

  private Scene returnScene;

  public void closeButtonClicked(){
      Stage rootStage = (Stage) closeButton.getScene().getWindow();
      rootStage.setScene(returnScene);
      rootStage.show();
  }

  public void setReturnScene(Scene returnScene){
    this.returnScene = returnScene;
  }

  public void setInfoText(String infoText){
    infoLabel.setText(infoText);
  }
}
