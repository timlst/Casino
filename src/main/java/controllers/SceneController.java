package main.java.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import main.java.oab.*;

public class SceneController implements Initializable{

	@FXML
	ImageView roll1, roll2, roll3;
	ReelControl r;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		ArrayList<ImageView> rolls = new ArrayList<ImageView>();
		rolls.add(roll1);
		rolls.add(roll2);
		rolls.add(roll3);

		ArrayList<ReelSymbol> symbols = new ArrayList<ReelSymbol>();
		symbols.add(new ReelSymbol(new Image("/main/resources/images/1.png"),SymbolDescription.ANGELINA));
		symbols.add(new ReelSymbol(new Image("/main/resources/images/2.png"),SymbolDescription.AARON));
		symbols.add(new ReelSymbol(new Image("/main/resources/images/3.png"),SymbolDescription.CLAAS));
		symbols.add(new ReelSymbol(new Image("/main/resources/images/4.png"),SymbolDescription.HANNES));
		symbols.add(new ReelSymbol(new Image("/main/resources/images/5.png"),SymbolDescription.JOSCHUA));
		symbols.add(new ReelSymbol(new Image("/main/resources/images/6.png"),SymbolDescription.TIM));
		symbols.add(new ReelSymbol(new Image("/main/resources/images/7.png"),SymbolDescription.TOBIAS));
		symbols.add(new ReelSymbol(new Image("/main/resources/images/8.png"),SymbolDescription.XENIA));
		symbols.add(new ReelSymbol(new Image("/main/resources/images/9.png"),SymbolDescription.oWo));

		r = new ReelControl(rolls,symbols);
		r.start();
		r.stop();
	}
	@FXML
	private void play() {
	   System.out.println("deprecated");
	}
	@FXML
	private void quit() {
		System.exit(0);
	}
	@FXML
	private void shuffle() {
		r.start();
		r.stop();
	}
	@Deprecated @FXML private void stop1() {}
	@Deprecated @FXML private void stop2() {}
	@Deprecated @FXML private void stop3() {}
}
