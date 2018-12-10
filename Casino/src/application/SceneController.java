package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SceneController implements Initializable{

	@FXML
	ImageView roll1, roll2, roll3;
	ReelControl r;
	ArrayList<ReelSymbol> symbols;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		ArrayList<ImageView> rolls = new ArrayList<ImageView>();
		rolls.add(roll1);
		rolls.add(roll2);
		rolls.add(roll3);
		
		symbols = new ArrayList<ReelSymbol>();
		symbols.add(new ReelSymbol(new Image("/icons/1.png"),SymbolDescription.ANGELINA));
		symbols.add(new ReelSymbol(new Image("/icons/2.png"),SymbolDescription.AARON));
		symbols.add(new ReelSymbol(new Image("/icons/3.png"),SymbolDescription.CLAAS));
		symbols.add(new ReelSymbol(new Image("/icons/4.png"),SymbolDescription.HANNES));
		symbols.add(new ReelSymbol(new Image("/icons/5.png"),SymbolDescription.JOSCHUA));
		symbols.add(new ReelSymbol(new Image("/icons/6.png"),SymbolDescription.TIM));
		symbols.add(new ReelSymbol(new Image("/icons/7.png"),SymbolDescription.TOBIAS));
		symbols.add(new ReelSymbol(new Image("/icons/8.png"),SymbolDescription.XENIA));
		symbols.add(new ReelSymbol(new Image("/icons/9.png"),SymbolDescription.TEST));
		
		r = new ReelControl(rolls,symbols);
		r.rolls.get(0).setEffect(null);
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
}
