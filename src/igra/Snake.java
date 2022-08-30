package igra;

import gui.Gui;
import logika.Logika;

/**
 * Igrica snake - Sadrzi logiku i gui
 * @author User
 *
 */
public class Snake {
	Gui gui;
	Logika logika;
	
	private void build() {
		logika = new Logika();
		gui = new Gui();
		
		gui.poveziSaLogikom(logika);
		logika.poveziSaGUI(gui);
		logika.setIgranjePrekoKonzole(false);
		gui.prikazi();
	}
	/**
	 * Konstruktor igre snake
	 */
	public Snake() {
		build();
	}

	
}
