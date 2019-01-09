/*
 *  Eine Reihe von Konstanten die jeweils ein Symbol darstellt welches auf den Walzen auftauchen kann
 * 	Ein neues Walzensymbol mit Pfad der Bilddatei und Punkten erstellen (siehe unten)
 *  @author Tim
 */

package main.java.oab;

import javafx.scene.image.Image;

public enum ReelSymbol {	
	AARON(5,50,new Image("/main/resources/images/1.png")),
	ANGELINA(5,50,new Image("/main/resources/images/2.png")),
	CLAAS(25,100,new Image("/main/resources/images/3.png")),
	HANNES(5,50,new Image("/main/resources/images/4.png")),
	JOSCHUA(5,50,new Image("/main/resources/images/5.png")),
	TIM(10,75,new Image("/main/resources/images/6.png")),
	TOBIAS(10,75,new Image("/main/resources/images/7.png")),
	XENIA(5,50,new Image("/main/resources/images/8.png"));

	private final Image img;
	private final double twice, thrice;

	ReelSymbol(double zwei, double drei, Image i){
		img = i;
		twice = zwei;
		thrice = drei;
	}
	/*
	 *@return Gibt ein Bild in Form eines Image Objekts zurueck
	 */
	public Image getImage() {
		return img;
	}
	/*
	 *@return Gibt die Punkte die dem Walzensymbol zugeordnet sind zurueck
	 */
	public double getTwo() {
		return twice;
	}
	
	public double getThree(){
		return thrice;
	}

}
