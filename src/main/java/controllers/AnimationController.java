package main.java.controllers;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.animation.RotateTransition;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.util.Duration;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.animation.PathTransition;

import java.lang.InterruptedException;

public class AnimationController{
  @FXML
  private ImageView logo;
  private Scene next;
  private Duration duration;

  private RotateTransition rotateTransition;
  //private PathTransition pathTransition;

  public AnimationController(){
    duration = Duration.millis(1000);
  }

  public void animate(){
    /*
    Path path = new Path();

    //move on a rectangular path
    MoveTo moveTo = new MoveTo(1000, 1000);
    path.getElements().add(moveTo);
    moveTo = new MoveTo(3000, 1000);
    path.getElements().add(moveTo);
    moveTo = new MoveTo(3000, 3000);
    path.getElements().add(moveTo);
    moveTo = new MoveTo(1000, 3000);
    path.getElements().add(moveTo);

    moveTo = new MoveTo(1000, 1000);
    path.getElements().add(moveTo);

    pathTransition = new PathTransition(duration, path, logo);
    pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
    pathTransition.play();
    */
    rotateTransition = new RotateTransition();
    rotateTransition.setDuration(duration);
    rotateTransition.setNode(logo);
    rotateTransition.setByAngle(360);
    rotateTransition.setAutoReverse(false);
    rotateTransition.play();
    rotateTransition.setOnFinished(new FinishedEventHandler(this));
    rotateTransition.play();
  }

  public void setNext(Scene next){
    this.next = next;
  }

  private void show(Scene scene){
    Stage rootStage = (Stage) logo.getScene().getWindow();
    rootStage.setScene(scene);
    rootStage.show();
  }

  private class FinishedEventHandler implements EventHandler{
    private Object controller;
    public FinishedEventHandler(Object controller){
      this.controller = controller;
    }

    public void handle(Event event){
      show(next);
    }
  }
}
