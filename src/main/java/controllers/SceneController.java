package main.java.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

import main.java.oab.Reel;
import main.java.oab.ReelControl;
import main.java.oab.ReelSymbol;

public class SceneController implements Initializable{

	@FXML
	VBox reelLeft, reelMiddle, reelRight;
	ReelControl r;
	ArrayList<ReelSymbol> symbols;
	boolean spinning;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		symbols = new ArrayList<ReelSymbol>();
		symbols.add(new ReelSymbol(new Image("/main/resources/images/1.png"),ReelSymbol.SymbolDescription.ANGELINA));
		symbols.add(new ReelSymbol(new Image("/main/resources/images/2.png"),ReelSymbol.SymbolDescription.AARON));
		symbols.add(new ReelSymbol(new Image("/main/resources/images/3.png"),ReelSymbol.SymbolDescription.CLAAS));
		symbols.add(new ReelSymbol(new Image("/main/resources/images/4.png"),ReelSymbol.SymbolDescription.HANNES));
		symbols.add(new ReelSymbol(new Image("/main/resources/images/5.png"),ReelSymbol.SymbolDescription.JOSCHUA));
		symbols.add(new ReelSymbol(new Image("/main/resources/images/6.png"),ReelSymbol.SymbolDescription.TIM));
		symbols.add(new ReelSymbol(new Image("/main/resources/images/7.png"),ReelSymbol.SymbolDescription.TOBIAS));
		symbols.add(new ReelSymbol(new Image("/main/resources/images/8.png"),ReelSymbol.SymbolDescription.XENIA));
		symbols.add(new ReelSymbol(new Image("/main/resources/images/9.png"),ReelSymbol.SymbolDescription.TEST));

		Reel left = new Reel(reelLeft);
		Reel middle = new Reel(reelMiddle);
		Reel right = new Reel(reelRight);

		r = new ReelControl(left,middle,right,symbols);
		spinning = false;
	}
	@FXML
	private void quit() {
		System.exit(0);
	}
	@FXML
	private void shuffle() {
		if(spinning) r.stopSpinning();
		else r.startSpinning();
		spinning ^= true;
	}
}
