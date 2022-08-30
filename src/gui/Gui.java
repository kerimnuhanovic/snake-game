package gui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import logika.Logika;

/**
 * Klasa koja predstavlja GUI za korsinika
 * @author User
 *
 */
public class Gui extends JFrame {
	/**
	 * Panel gui-a po kojem crtamo
	 */
	Panel panel;
	/**
	 * Logika koju povezujemo sa GUI-em
	 */
	Logika logika;

	
	private void build() {
		this.setTitle("Snake");
		panel = new Panel(this);
		add(panel);
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	/**
	 * Konstruktor za Gui
	 */
	public Gui() {
		build();
	}
	
	/**Funkcija setuje logiku gui-a
	 *dodajuci listenere na panel gui-a. 
	 * Ako korisnik pritisne tipku a igrica nije pokrenuta, igrica se pokrece
	 * u suprotnom, zmije skrece u tom smjeru
	 * @param logika - Gui povezuje sa proslijedjenom logikom
	 * 
	 * */
	
	public void poveziSaLogikom(Logika logika) {
		this.logika = logika;
		this.panel.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				
				if(logika.getPokrenuta()) {
					logika.postaviSmjer(e.getKeyCode());
				}
				else {
					logika.setPokrenuta(true);
					logika.restartujTimer();
				}
			}
		});
		
		
		
		this.panel.setFocusable(true);
	}
	
	/**
	 * Prikazuje GUI korisniku
	 */
	public void prikazi() {
		setVisible(true);
	}
	/**
	 * Vraca panel Gui-a
	 * @return Panel 
	 */
	public Panel getPanel() {
		return panel;
	}
	
}
