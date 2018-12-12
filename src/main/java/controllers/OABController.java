package main.java.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

import main.java.oab.ReelControl;
import main.java.oab.ReelSymbol;
import main.java.oab.Reel;

public class OABController implements Initializable{

	@FXML
	VBox reelLeft, reelMiddle, reelRight;
	ReelControl r;
	ArrayList<ReelSymbol> symbols;

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
		//for(ReelSymbol s:symbols) System.out.println(s);

		Reel left = new Reel(reelLeft);
		Reel middle = new Reel(reelMiddle);
		Reel right = new Reel(reelRight);

		r = new ReelControl(left,middle,right,symbols);
	}

	/*
	 * Alle mit @FXML Methoden sorgen fuer die GUI-Backend-Interaktion
	 */

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
