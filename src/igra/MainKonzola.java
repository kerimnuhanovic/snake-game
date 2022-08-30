package igra;

import java.util.Random;
import java.util.Scanner;

import logika.Logika;

/**
 * Main u kojem pokrecemo igricu ukoliko se igra igra preko konzole
 * @author User
 *
 */
public class MainKonzola {

	/**
	 * 
	 * @param args - parametar
	 */
	public static void main(String[] args) {
		Logika logika = new Logika();
		System.out.println("evo me");
		Scanner ulaz = new Scanner(System.in);
		
		Random random = new Random();
		logika.setIgranjePrekoKonzole(true);
		/*Postavljamo dimenzije matrice
		 * */
		int dimenzijaSirina = logika.getSirina()/logika.getVelicina();
		int dimenzijaVisina = logika.getVisina()/logika.getVelicina();
		/*Konstruisemo matricu koja ce sadrzavati elemente za ispis
		 * */
		char matrica[][] = new char[dimenzijaSirina][dimenzijaVisina];
		/*Postavljamo u matricu Z na mjesto koordinata zmijice
		 * */
		for(int i = 0; i < logika.getVelicinaZmije();i++) {
			int x = logika.getElementNizaX(i);
			int y = logika.getElementNizaY(i);
			matrica[y][x] = 'Z';
		}
		/*Postavljamo u matricu P na mjesto gdje se nalaze prepreke
		 * */
		for(int i = 0; i < logika.getBrojPrepreka()*3; i++) {
			int x = logika.getPreprekaX(i);
			int y = logika.getPreprekaY(i);
			matrica[y][x] = 'P';
		}
		/*Postavljamo u matricu J na mjesto gdje se nalazi jabuka
		 * */
		matrica[logika.getJabukaY()][logika.getJabukaX()] = 'J';
		/*Ispisujemo pocetne pozicije zmije, prepreke, slobodna mjesta, jabuku
		 * */
		for(int i = 0; i < dimenzijaVisina; i++) {
			for(int j = 0; j < dimenzijaSirina; j++) {
				if(matrica[i][j] != 'Z' && matrica[i][j] != 'J' && matrica[i][j] != 'P')
					System.out.print("S" + " ");
				else System.out.print(matrica[i][j] + " ");
			}
			System.out.println();
		}
		/*Ponavljamo sve dok igra nije zavrsena tj. dok zmijica ne udari u samu sebe ili u prepreku
		 * */
		while(true) {
			/*Korisnik unosi potez birajuci jednu od cetiri opcije
			 * */
			System.out.println("Unesite potez: R-desno/L-lijevo/U-gore/D-dole");
			String smjer = ulaz.next();
			/**Postavljamo smjer zmijice
			 * */
			logika.postaviSmjerKonzola(smjer);
			/*Pomjeramo zmijicu, provjeravamo da li je jabuka pojedena, da li je doslo do sudara sa preprekom ili samom zmijicom
			 * */
			logika.pomjeraj();
			logika.provjeraJabuke();
			logika.provjeraSudara();
			logika.provjeraSudaraSaPreprekom();
			if(logika.getGameOver()) {
				System.out.println("Igra gotova");
			}
			else {
				/*Ispisujemo novu poziciju zmijice
				 * */
				matrica = new char[dimenzijaVisina][dimenzijaSirina];
				for(int i = 0; i < logika.getVelicinaZmije();i++) {
					int x = logika.getElementNizaX(i);
					int y = logika.getElementNizaY(i);
					matrica[y][x] = 'Z';
				}
				for(int i = 0; i < logika.getBrojPrepreka()*3; i++) {
					int x = logika.getPreprekaX(i);
					int y = logika.getPreprekaY(i);
					matrica[y][x] = 'P';
				}
				matrica[logika.getJabukaY()][logika.getJabukaX()] = 'J';
				for(int i = 0; i < dimenzijaVisina; i++) {
					for(int j = 0; j < dimenzijaSirina; j++) {
						if(matrica[i][j] != 'Z' && matrica[i][j] != 'J' && matrica[i][j] != 'P')
							System.out.print("S" + " ");
						else System.out.print(matrica[i][j] + " ");
					}
					System.out.println();
				}
			}
		}
		
	}

}
