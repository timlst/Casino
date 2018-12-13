package main.java.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import main.java.oab.ReelControl;
import main.java.oab.ReelSymbol;
import main.java.oab.Reel;

public class OABController implements Initializable{

	@FXML
	VBox reelLeft, reelMiddle, reelRight;
	ReelControl r;
	ArrayList<ReelSymbol> symbols;
	@FXML
	Label lblPunkte;
	@FXML
	TextField txtEinsatz;
	@FXML
	Button spin;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		symbols = new ArrayList<ReelSymbol>();

		symbols.add(ReelSymbol.AARON);
		symbols.add(ReelSymbol.ANGELINA);
		symbols.add(ReelSymbol.CLAAS);
		symbols.add(ReelSymbol.HANNES);
		symbols.add(ReelSymbol.JOSCHUA);
		//symbols.add(ReelSymbol.TEST);
		symbols.add(ReelSymbol.TIM);
		symbols.add(ReelSymbol.TOBIAS);
		symbols.add(ReelSymbol.XENIA);
		//for(ReelSymbol s:symbols) System.out.println(s);

		Reel left = new Reel(reelLeft);
		Reel middle = new Reel(reelMiddle);
		Reel right = new Reel(reelRight);
		r = new ReelControl(left,middle,right,symbols, lblPunkte, txtEinsatz, 500);
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
		if(!r.isRunning()) r.startSpinning(Integer.parseInt(txtEinsatz.getText()));
		spin.setDisable(true);
		txtEinsatz.setText("");
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
	@FXML
	private void readBet() {
		spin.setDisable(!checkBet());
	}
	private boolean checkBet() {
		int bet = Integer.parseInt(txtEinsatz.getText());
		int points = Integer.parseInt(lblPunkte.getText());
		if(bet<=0) return false;
		else if(txtEinsatz.getText().length()==0) return false;
		else if (r.hasFreeSpin()) return true;
		else return Integer.parseInt(txtEinsatz.getText())<=Integer.parseInt(lblPunkte.getText());
	}
}
