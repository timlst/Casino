package main.java.controllers;

import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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

	@FXML
	ImageView gifViewer;

	int bet;
	int points;

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

		r = new ReelControl(left,middle,right,symbols, lblPunkte, txtEinsatz, 500, gifViewer);
		//UNGEFILTERTER USER INPUT IST NICHT GUT
		DecimalFormat format = new DecimalFormat( "#0000" );
		txtEinsatz.setTextFormatter( new TextFormatter<>(c ->{if ( c.getControlNewText().isEmpty() )return c;
		    ParsePosition parsePosition = new ParsePosition( 0 );
		    Object object = format.parse( c.getControlNewText(), parsePosition );
		    if ( object == null || parsePosition.getIndex() < c.getControlNewText().length() ) return null;
		    else return c;
		}));

		txtEinsatz.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable,
		            String oldValue, String newValue) {
		    		readBet();
		    	}
		});

		txtEinsatz.addEventHandler(KeyEvent.KEY_PRESSED, ev -> {
	        if (ev.getCode() == KeyCode.ENTER) {
	           spin.fire();
	           ev.consume();
	        }
	    });

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
		if(!r.isRunning()) r.startSpinning(bet);
		spin.setDisable(true);
		txtEinsatz.clear();
		readBet();
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
		try {
		bet = Integer.parseInt(txtEinsatz.getText());
		points = Integer.parseInt(lblPunkte.getText());
		} catch (Exception e){
			spin.setDisable(false);
		}
		spin.setDisable(!checkBet());
	}
	private boolean checkBet() {
		if(r.isRunning()) return false;
		else if(bet<=0) return false;
		else if(txtEinsatz.getText().length()==0) return false;
		else if (r.hasFreeSpin()) return true;
		else return Integer.parseInt(txtEinsatz.getText())<=Integer.parseInt(lblPunkte.getText());
	}
}
