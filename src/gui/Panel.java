package gui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.Timer;


/**
 * Klasa panel po kojem iscrtajemo objekte
 * @author User
 *
 */
public class Panel extends JPanel implements ActionListener {
	/**
	 * GUI kojem panel pripada
	 */
	Gui gui;
	/**
	 * sirina panela
	 */
	private int sirina = 600;
	/**
	 * visina panela
	 */
	private int visina = 600;
	
	/**
	 * Funkcija koja iscrtava jabuku, zmijicu, poene, prepreke ukoliko je igra zapoceta
	 * Ako igrac zavrsi igru, prikazuje se poruka Game Over sa brojem poena i mogucnost ponovnog pokretanja igre
	 * @param g
	 */
	private void draw(Graphics g) {
		if(gui.logika.getPokrenuta()) {
			g.setColor(new Color(75, 76, 77));
			/*
			for(int i = 0; i < gui.logika.getVisina()/gui.logika.getVelicina(); i++) {
				g.drawLine(0,i*gui.logika.getVelicina(), gui.logika.getSirina(), i*gui.logika.getVelicina());
				g.drawLine(i*gui.logika.getVelicina(),0,i*gui.logika.getVelicina(),gui.logika.getSirina());
			}
			*/
			//spawna jabuku
			g.setColor(new Color(236, 240, 241));
			g.fillRect(gui.logika.getJabukaX()*gui.logika.getVelicina(),gui.logika.getJabukaY()*gui.logika.getVelicina(), gui.logika.getVelicina(),
					gui.logika.getVelicina());
			
			g.setColor(new Color(250, 107, 5));
			for(int i = 0; i < gui.logika.getVelicinaZmije();i++) {
				if(i == 0)
					g.setColor(new Color(52, 152, 216));
				else g.setColor(new Color(52, 152, 216));
				g.fillRect(gui.logika.getElementNizaX(i)*gui.logika.getVelicina(), gui.logika.getElementNizaY(i)*gui.logika.getVelicina(), 
						gui.logika.getVelicina(),gui.logika.getVelicina());
			}
			//postavi prepreke
			g.setColor(new Color(41, 128, 137));
			for(int i = 0; i < gui.logika.getBrojPrepreka()*3;i++) {
				g.fillRect(gui.logika.getPreprekaX(i)*gui.logika.getVelicina(), gui.logika.getPreprekaY(i)*gui.logika.getVelicina(), 
						gui.logika.getVelicina(),gui.logika.getVelicina());
			}
			
			g.setColor(new Color(7, 120, 232));
			g.setFont(new Font("Ink free", Font.BOLD, 30));
			FontMetrics metrics = getFontMetrics(g.getFont());
			g.drawString("Skor: " + gui.logika.getPoeni(), (gui.logika.getSirina()-metrics.stringWidth("Skor: " + gui.logika.getPoeni()))/2, 50);
			
			
		}
		
		else {
			if(gui.logika.getGameOver()) {
				g.setColor(new Color(52, 152, 216));
				g.setFont(new Font("Ink free", Font.BOLD, 30));
				FontMetrics metrics = getFontMetrics(g.getFont());
				g.drawString("Game over-Score: " + gui.logika.getPoeni(), (gui.logika.getSirina()-metrics.stringWidth("Game over-Score: " + gui.logika.getPoeni()))/2, gui.logika.getVisina()/2);
				g.setFont(new Font("Ink free", Font.BOLD, 20));
				metrics = getFontMetrics(g.getFont());
				g.drawString("Press any keyboard", (gui.logika.getSirina()-metrics.stringWidth("Press any keyboard"))/2, gui.logika.getVisina()/2+30);
				gui.logika.restartuj();
				
			}
			else {
				gui.logika.zapocni();
			}
		}
		
	}
	
	/**
	 * Akcije koje se izvode prilikom igranja igrice
	 */
	public void actionPerformed(ActionEvent e) {
		if(gui.logika.getPokrenuta()) {
			gui.logika.pomjeraj();
			gui.logika.provjeraJabuke();
			gui.logika.provjeraSudara();
			gui.logika.provjeraSudaraSaPreprekom();
		}
		
	}
	
	/**
	 *Iscrtava zmijicu, prepreke, jabuku 
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
		
	}
	
	private void build() {
		this.setBackground(new Color(44, 62, 80));
		this.setPreferredSize(new Dimension(sirina,visina));
		
		

		
		
	}
	
	/**
	 * Konstruktor za panel
	 * @param gui - Postavljamo Gui za panel
	 */
	public Panel(Gui gui) {
		this.gui = gui;
		build();
	}

}
