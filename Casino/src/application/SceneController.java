package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

public class SceneController implements Initializable{

	@FXML
	VBox reelLeft, reelMiddle, reelRight;
	ReelControl r;
	ArrayList<ReelSymbol> symbols;
	boolean spinning;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
			
		symbols = new ArrayList<ReelSymbol>();
		
		symbols.add(ReelSymbol.AARON);
		symbols.add(ReelSymbol.ANGELINA);
		symbols.add(ReelSymbol.CLAAS);
		symbols.add(ReelSymbol.HANNES);
		symbols.add(ReelSymbol.JOSCHUA);
		symbols.add(ReelSymbol.TEST);
		symbols.add(ReelSymbol.TIM);
		symbols.add(ReelSymbol.TOBIAS);
		symbols.add(ReelSymbol.XENIA);
		for(ReelSymbol s:symbols) System.out.println(s);
		
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
		if(!r.isRunning()) r.startSpinning();
	}
	
	@FXML
	private void stopL() {
		r.stopSpin(r.l);
	}
	@FXML
	private void stopM() {
		r.stopSpin(r.m);
	}
	@FXML
	private void stopR() {
		r.stopSpin(r.r);
	}
}
