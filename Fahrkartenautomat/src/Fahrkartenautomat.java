import java.util.Scanner;

class Fahrkartenautomat {
	public static void main(String[] args) {

		Scanner tastatur = new Scanner(System.in);
		double zuZahlenderBetrag = 0.0; // verändert
		double eingezahlterGesamtbetrag;
		
		begruessung();
		zuZahlenderBetrag = fahrkartenbestellungErfassen(tastatur);
		eingezahlterGesamtbetrag = fahrkartenBezahlen(tastatur, zuZahlenderBetrag);
		fahrkartenAusgeben();
		rueckgeldAusgeben(eingezahlterGesamtbetrag, zuZahlenderBetrag);

		tastatur.close();
	}

	private static void begruessung() {
		System.out.println("Herzlich Willkommen!");
	}

	private static double fahrkartenbestellungErfassen(Scanner tastatur) {

		double zuZahlenderBetrag = 0.0; // verändert
		double ticketPreis = 0;
		int anzahlTickets = 0;

		while (true) { // neu

			boolean korrekteEingabe = false; // neu
			boolean auswahlBeenden = false;
			int auswahlTickets = 0; // neu

			// Kartenauswahl
			System.out.println("Wählen Sie ihre Wunschfahrkarte für Berlin AB aus:");
			System.out.println("Kurzstrecke AB [2,00 EUR] (1)");
			System.out.println("Einzelfahrschein AB [3,00 EUR] (2)");
			System.out.println("Tageskarte Regeltarif AB [8,80 EUR] (3)");
			System.out.println("4-Fahrten-Karte AB [9,40 EUR] (4)\n");
			System.out.println("Bezahlen (9)\n"); // neu

			while (korrekteEingabe == false) {
				System.out.print("Ihre Wahl: ");
				auswahlTickets = tastatur.nextInt();
				if (auswahlTickets >= 1 && auswahlTickets <= 4) {
					korrekteEingabe = true;
				} else if (auswahlTickets == 9) { // neu
					korrekteEingabe = true; // neu
					auswahlBeenden = true; // neu
				} // neu
				else {
					System.out.println(" >>falsche Eingabe<< ");
				}
			}

			if (auswahlBeenden) { // neu
				return zuZahlenderBetrag;
			}

			if (auswahlTickets == 1)
				ticketPreis = 2.0;
			else if (auswahlTickets == 2)
				ticketPreis = 3.0;
			else if (auswahlTickets == 3)
				ticketPreis = 8.8;
			else if (auswahlTickets == 4)
				ticketPreis = 9.4;

			// Ticketanzahl
			korrekteEingabe = false;

			while (korrekteEingabe == false) {
				System.out.print("Anzahl der Tickets: ");
				anzahlTickets = tastatur.nextInt();

				if (anzahlTickets >= 1 && anzahlTickets <= 10)
					korrekteEingabe = true;
				else
					System.out.println(" >> Wählen Sie bitte eine Anzahl von 1 bis 10 Tickets aus. <<\n");

			}

			zuZahlenderBetrag = zuZahlenderBetrag + ticketPreis * anzahlTickets; // verändert

			System.out.format("%nZwischensumme: %4.2f € %n%n", zuZahlenderBetrag); // neu

		} // neu
	}

	private static double fahrkartenBezahlen(Scanner tastatur, double zuZahlenderBetrag) {
		double eingezahlterGesamtbetrag = 0.0;
		double eingeworfeneMuenze;
		double nochZuZahlen = 0.0;

		while (eingezahlterGesamtbetrag < zuZahlenderBetrag) {
			nochZuZahlen = zuZahlenderBetrag - eingezahlterGesamtbetrag;
			System.out.printf("Noch zu zahlen: %4.2f Euro\n", nochZuZahlen);

			System.out.print("Eingabe (mind. 5 Cent, höchstens 2 Euro): ");
			eingeworfeneMuenze = tastatur.nextDouble();
			eingezahlterGesamtbetrag = eingezahlterGesamtbetrag + eingeworfeneMuenze;
		}

		return eingezahlterGesamtbetrag;
	}
	
	private static void fahrkartenAusgeben() {
		
		// Fahrscheinausgabe
				System.out.println("\nFahrschein wird ausgegeben");
				for (int i = 0; i < 8; i++) {
					System.out.print("=");
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println("\n\n");
		
	}
	boolean test;

	private static void rueckgeldAusgeben(double eingezahlterGesamtbetrag, double zuZahlenderBetrag) {

		double rueckgabebetrag;

		// Rückgeldberechnung und -ausgabe
		rueckgabebetrag = eingezahlterGesamtbetrag - zuZahlenderBetrag;
		if (rueckgabebetrag > 0.0) {
			System.out.format("Der Rückgabebetrag in Höhe von %4.2f Euro \n", rueckgabebetrag);
			System.out.println("wird in folgenden Münzen ausgezahlt:");

			while (rueckgabebetrag >= 2.0) { // 2-Euro-Münzen
				System.out.println("2 Euro");
				rueckgabebetrag = Math.round(rueckgabebetrag * 100.0) / 100.0;
				rueckgabebetrag = rueckgabebetrag - 2.0;
			}
			while (rueckgabebetrag >= 1.0) { // 1-Euro-Münzen
				System.out.println("1 Euro");
				rueckgabebetrag = Math.round(rueckgabebetrag * 100.0) / 100.0;
				rueckgabebetrag = rueckgabebetrag - 1.0;
			}
			while (rueckgabebetrag >= 0.5) { // 50-Cent-Münzen
				System.out.println("50 Cent");
				rueckgabebetrag = Math.round(rueckgabebetrag * 100.0) / 100.0;
				rueckgabebetrag = rueckgabebetrag - 0.5;
			}
			while (rueckgabebetrag >= 0.2) { // 20-Cent-Münzen
				System.out.println("20 Cent");
				rueckgabebetrag = Math.round(rueckgabebetrag * 100.0) / 100.0;
				rueckgabebetrag = rueckgabebetrag - 0.2;
			}
			while (rueckgabebetrag >= 0.1) { // 10-Cent-Münzen
				System.out.println("10 Cent");
				rueckgabebetrag = Math.round(rueckgabebetrag * 100.0) / 100.0;
				rueckgabebetrag = rueckgabebetrag - 0.1;
			}
			while (rueckgabebetrag >= 0.05) { // 5-Cent-Münzen
				System.out.println("5 Cent");
				rueckgabebetrag = Math.round(rueckgabebetrag * 100.0) / 100.0;
				rueckgabebetrag = rueckgabebetrag - 0.05;
			}
		}

		System.out.println("\nVergessen Sie nicht, den Fahrschein\n" + "vor Fahrtantritt entwerten zu lassen!\n"
				+ "Wir wünschen Ihnen eine gute Fahrt.");
	}

}
//git Änderung