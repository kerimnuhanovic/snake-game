package logika;

import java.util.Random;

import javax.swing.Timer;

import gui.Gui;

/**
 * Klasa koja predstavlja logiku igrice Snake
 * @author User
 *
 */
public class Logika {
	Gui gui;
	private int visina = 600;
	private int sirina = 600;
	private int velicina = 25;
	private int vrijeme = 50;
	Timer timer;
	private int jabukaX = 5;
	private int jabukaY = 5;
	
	Random random;
	private int nizX[];
	private int nizY[];
	private String smjer;
	private int velicinaZmije;
	private boolean pokrenuta = false;
	private int poeni = 0;
	private boolean gameOver = false;
	private int preprekeX[];
	private int preprekeY[];
	private int brojPrepreka;
	private boolean igranjePrekoKonzole;
	/**
	 * Funkcija vraca da li je igrica pokrenuta
	 * @return pokrenuta
	 */
	public boolean getPokrenuta() {
		return pokrenuta;
	}
	/**
	 * Funkcija vraca sirinu gui-a
	 * @return sirina
	 */
	public int getSirina() {
		return sirina;
	}
	/**
	 * Funkcija vraca visinu gui-a
	 * @return visina
	 */
	public int getVisina() {
		return visina;
	}
	/**
	 * Funckija vraca velicinu jednog dijela zmijice
	 * @return velicina
	 */
	public int getVelicina() {
		return velicina;
	}
	/**
	 * Funckija vraca x poziciju jabuke
	 * @return jabukaX
	 */ 
	public int getJabukaX() {
		return jabukaX;
	}
	/**
	 * Funkcija vraca y poziciju jabuke
	 * @return jabukaY
	 */
	public int getJabukaY() {
		return jabukaY;
	}
	/**
	 * Funckija vraca velicinu zmijice
	 * @return velicina Zmije
	 */
	public int getVelicinaZmije() {
		return velicinaZmije;
	}
	
	/**
	 * Funckija vraca pozicju x zmijice sa indeksom proslijedjenim kao parametar
	 * @param indeks - indeks pozicije koordinate x zmijice 
	 * @return nizX[indeks]
	 */
	public int getElementNizaX(int indeks) {
		return nizX[indeks];
	}
	/**
	 *Funckija vraca poziciju y zmijice sa indeksom proslijedjenim kroz parametar 
	 * @param indeks - indeks pozicije y zmijice u nizu koji cuva koordinate zmije
	 * @return nizY[indeks]
	 */
	public int getElementNizaY(int indeks) {
		return nizY[indeks];
	}
	
	/**
	 * Funckija vraca broj poena
	 * @return poeni
	 */
	public int getPoeni() {
		return poeni;
	}
	/**
	 * Funkcija vraca da li je igra zavrsena
	 * @return gameOver
	 */
	public boolean getGameOver() {
		return gameOver;
	}
	/**
	 * Funckija vraca broj prepreka 
	 * @return brojPrepreka
	 */
	public int getBrojPrepreka() {
		return brojPrepreka;
	}
	
	/**
	 *Funkcija vraca x koordinatu prepreke na zadatom indeksu 
	 * @param indeks - indeks koordinate x prepreke koju zelimo dohvatiti
	 * @return preprekeX[indeks]
	 */
	public int getPreprekaX(int indeks) {
		return preprekeX[indeks];
	}
	/**
	 * Funckija vraca y koordinatu prepreke na zadatom indeksu
	 * @param indeks - indeks koordinate y prepreke koju zelimo dohvatiti
	 * @return preprekeY[indeks]
	 */
	public int getPreprekaY(int indeks) {
		return preprekeY[indeks];
	}
	
	/**
	 *Funkcija postavlja da li se igra igra preko konzole ili preko GUI-a 
	 * @param b - true ako se igra preko konzole, false u protivnom
	 */
	public void setIgranjePrekoKonzole(boolean b) {
		this.igranjePrekoKonzole = b;
	}
	
	/**
	 * Restartuje vrijednosti pri kraju igre, kako bi pri novom zapocinjanju igre bile na pocetnim vrijednostima
	 */
	public void restartuj() {
		velicinaZmije = 6;
		poeni = 0;
		nizX[0] = 0;
		nizY[0] = 0;
		for(int i = 0; i < velicinaZmije; i++) {
			nizX[i] = 0;
			nizY[i] = 0;
		}
		smjer = "R";
		postaviBlokade();
	}
	/**
	 * Konstruktorska funkcija - inicjalizira parametre igre
	 */
	private void build() {
		velicinaZmije = 6;
		smjer = new String("R");
		nizX = new int[(sirina*visina)/velicina];
		nizY = new int[(sirina*visina)/velicina];
		
		random = new Random();

		jabukaX = random.nextInt(sirina/velicina);
		jabukaY = random.nextInt(visina/velicina);
		postaviBlokade();
		
	}
	/**
	 * Konstruktor logike
	 */
	public Logika() {
		build();
	}
	/**
	 * Funkcija koja povezuje logiku sa GUI-em
	 * @param gui - Gui kojeg povezujemo sa logikom
	 */
	public void poveziSaGUI(Gui gui) {
		
		this.gui = gui;
	}
	
	
	/**
	 * Postavlja nasumican broj prepreka na nasumicna mjesta pri pokretanju igre
	 */
	private void postaviBlokade() {
		brojPrepreka = random.nextInt(3)+4;
		preprekeX = new int[brojPrepreka*3];
		preprekeY = new int[brojPrepreka*3];
		for(int i = 0; i < brojPrepreka*3; i+=3) {
			if(random.nextDouble() < 0.3) {
				int x = random.nextInt(sirina/velicina-2);
				int y = random.nextInt(visina/velicina);
				preprekeX[i] = x;
				preprekeY[i] = y;
				
				preprekeX[i+1] = x+1;
				preprekeY[i+1] = y;
				
				preprekeX[i+2] = x+2;
				preprekeY[i+2] = y;	
			}
			else if(random.nextDouble()<0.6) {
				int x = random.nextInt(sirina/velicina);
				int y = random.nextInt(visina/velicina-2);
				preprekeX[i] = x;
				preprekeY[i] = y;
				
				preprekeX[i+1] = x;
				preprekeY[i+1] = y+1;
				
				preprekeX[i+2] = x;
				preprekeY[i+2] = y+2;	
			}
			else {
				if(random.nextDouble()<=0.25) {
					int x = random.nextInt(sirina/velicina-1);
					int y = random.nextInt(visina/velicina-1);
					preprekeX[i] = x;
					preprekeY[i] = y;
					
					preprekeX[i+1] = x+1;
					preprekeY[i+1] = y;
					
					preprekeX[i+2] = x;
					preprekeY[i+2] = y+1;	
				}
				else if(random.nextDouble()<=0.5) {
					int x = random.nextInt(sirina/velicina-2);
					int y = random.nextInt(visina/velicina-1);
					preprekeX[i] = x;
					preprekeY[i] = y;
					
					preprekeX[i+1] = x+1;
					preprekeY[i+1] = y;
					
					preprekeX[i+2] = x+1;
					preprekeY[i+2] = y+1;	
				}
				else if(random.nextDouble()<=0.75) {
					int x = random.nextInt(sirina/velicina-2);
					int y = random.nextInt(visina/velicina-1);
					preprekeX[i] = x;
					preprekeY[i] = y;
					
					preprekeX[i+1] = x-1;
					preprekeY[i+1] = y;
					
					preprekeX[i+2] = x-1;
					preprekeY[i+2] = y-1;	
				}
				else  {
					int x = random.nextInt(sirina/velicina-2);
					int y = random.nextInt(visina/velicina);
					preprekeX[i] = x;
					preprekeY[i] = y;
					
					preprekeX[i+1] = x+1;
					preprekeY[i+1] = y;
					
					preprekeX[i+2] = x+1;
					preprekeY[i+2] = y-1;	
				}
			}
		}
		
	}
	
	/**
	 * Provjerava da li je zmija udarila u samu sebe
	 */
	public void provjeraSudara() {
		for(int i = 1;i<velicinaZmije;i++) {
			if(nizX[0] == nizX[i] && nizY[0] == nizY[i]) {
				gameOver = true;
				pokrenuta = false;
				if(!igranjePrekoKonzole)
				timer.stop();
			}
		}
	}
	/**
	 * Provjerava da li je zmija udarila u prepreku
	 */
	public void provjeraSudaraSaPreprekom() {
		for(int i = 1;i<brojPrepreka*3;i++) {
			if(nizX[0] == preprekeX[i] && nizY[0] == preprekeY[i]) {
				gameOver = true;
				pokrenuta = false;
				if(!igranjePrekoKonzole)
				timer.stop();
			}
		}
	}
	
	/**
	 * Provjerava da li je glava zmijice na istom mjestu kao jabuka.
	 * Ukoliko je to slucaj, generise se nova jabuka i poeni se povecaju.
	 */
	public void provjeraJabuke() {
		if(nizX[0] == jabukaX && nizY[0] == jabukaY) {
			
			
			jabukaX = random.nextInt(sirina/velicina);
			jabukaY = random.nextInt(visina/velicina);
			for(int i = 0; i < brojPrepreka*3; i++) {
				if(jabukaX == preprekeX[i] && jabukaY == preprekeY[i]) {
					jabukaX = 1;
					jabukaY = 1;
				}
			}
			
			if(random.nextDouble() > 0.8) {
				
				velicinaZmije -= 1;
				
				
			}
			else {
				velicinaZmije++;
				
			}
			poeni++;
			System.out.println(velicinaZmije);
		}
	}
	
	/**
	 * Funkcija koja pomjera zmijicu na nacin da svi clanci zmije prolaze putem kojim je prosla glava
	 */
	public void pomjeraj() {
		for(int i = velicinaZmije; i>0; i--) {
			nizX[i] = nizX[i-1];
			nizY[i] = nizY[i-1];
		}
		if(smjer.equals("R")) {
			if(nizX[0] != sirina/velicina-1)
				nizX[0] += 1;
			else nizX[0] = 0;
		}
		else if(smjer.equals("L")) {
			if(nizX[0] != 0)
				nizX[0] -= 1;
			else nizX[0] = sirina/velicina-1;
		}
		else if(smjer.equals("U")) {
			if(nizY[0] != 0)
				nizY[0] -= 1;
			else nizY[0] = visina/velicina-1;
		}
		else {
			if(nizY[0] != visina/velicina-1)
				nizY[0] += 1;
			else nizY[0] = 0;
		}
		
		if(!igranjePrekoKonzole)
			gui.repaint();
	}
	
	/**
	 * U odnosu na tipku koju je korisnik pritisnuo, postavlja se smjer kretanje zmijice
	 * @param tipka - tipka koja je pritisnuta od strane korisnika
	 */
	public void postaviSmjer(int tipka) {
		if(tipka == 37 && (smjer.equals("U") || smjer.equals("D"))) {
			smjer = "L";
		}
		else if(tipka == 39 && (smjer.equals("U") || smjer.equals("D"))) {
			smjer = "R";
		}
		else if(tipka == 38 && (smjer.equals("L") || smjer.equals("R"))) {
			smjer = "U";
		}
		else if(tipka == 40 && (smjer.equals("L") || smjer.equals("R"))) {
			smjer = "D";
		}
	}
	
	/**
	 * Postavlja smjer zmijice kada se igra igra preko konzole
	 * @param s - smjer koji je korsinik unio
	 */
	public void postaviSmjerKonzola(String s) {
		if(s.equals("L") && (smjer.equals("U") || smjer.equals("D"))) {
			
			smjer = "L";
		}
		else if(s.equals("R") && (smjer.equals("U") || smjer.equals("D"))) {
			smjer = "R";
		}
		else if(s.equals("U") && (smjer.equals("L") || smjer.equals("R"))) {
			smjer = "U";
		}
		else if(s.equals("D") && (smjer.equals("L") || smjer.equals("R"))) {
			smjer = "D";
		}
	}
	
	/**
	 * Postavlja parametar logike koji govori da li je igrica pokrenuta
	 * @param pokrenuta - kroz ovaj parametar pokrcemo igricu
	 */
	public void setPokrenuta(boolean pokrenuta) {
		this.pokrenuta = pokrenuta;
	}
	/**
	 * Restartuje timer pri ponovnom pokretanju igre
	 */
	public void restartujTimer() {
		timer.restart();
	}
	
	/**
	 * Inicijalizira timer i pokrece ga pri pokretanju igre
	 */
	public void zapocni() {

		timer = new Timer(vrijeme, this.gui.getPanel());
		timer.start();
	}
}
