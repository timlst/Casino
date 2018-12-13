/*
 *  Eine Reihe von Konstanten die jeweils ein Symbol darstellt welches auf den Walzen auftauchen kann
 * 	Ein neues Walzensymbol mit Pfad der Bilddatei und Punkten erstellen (siehe unten)
 *  @author Tim
 */

package main.java.oab;

import javafx.scene.image.Image;

public enum ReelSymbol {

	//Werte sind geändert (2,2,2,5,5,10,0,69,1000)
	//Werte sind wieder geändert: Multiplikatoren Idee implementiert, I guess?
	
	AARON(0.55,new Image("/main/resources/images/1.png")),
	ANGELINA(0.85,new Image("/main/resources/images/2.png")),
	CLAAS(1.35, new Image("/main/resources/images/3.png")),
	HANNES(0.85, new Image("/main/resources/images/4.png")),
	JOSCHUA(0.4,new Image("/main/resources/images/5.png")),
	TIM(1.75,new Image("/main/resources/images/6.png")),
	TOBIAS(1.05,new Image("/main/resources/images/7.png")),
	XENIA(0.7,new Image("/main/resources/images/8.png"));

	private final Image img;
	private final double points;

	ReelSymbol(double p, Image i){
		img = i;
		points = p;
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
	public double getPoints() {
		return points;
	}

}
