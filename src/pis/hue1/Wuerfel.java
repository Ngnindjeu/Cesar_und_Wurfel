package pis.hue1;
/**
 *
 * @author Ngnindjeu Sonfack Doriane Lovline
 * @version 14.0.1
 */
public class Wuerfel implements Codec {
	 String losung = "";
	int[]	returnInverseVonPermutation;
	 
	 public Wuerfel(String losung) {

	 	this.losung = losung;
	 }

	 public Wuerfel() {

	 }
	 
	    private int verschiebung = 0;

	/**
	 * diese Methode erlaubt uns ein String also ein klartext zu kodieren oder verschuesseln
	 * @param klartext  ist was man verschuelsseln moechte
	 * @return gibt ein geheimtext aus also hier ist mit new_str gezeichnet
	 * klassen invarianten :klartext muss ungleich null sein und muss ein String sein . die methode liefert uns zuruck die entsprechende  geheimtext
	 */
	    @Override
	    public  String kodiere(String klartext) {
	    	String new_str = "";
	        int[] permutation1 = new int [this.losung.length()];

	        int stelle =0;
	        for(int i = 'a' ; i<= 'z' ;i++) {
	            for (int j = 0 ; j < losung.length(); j++ ) {
	                if( i == this.losung.toLowerCase().charAt(j) || i == this.losung.toUpperCase().charAt(j)) {
	                    permutation1[stelle] = j ;
	                    stelle++;
	                }
	            }
	        }

	    	
	        
	       int i  =0;
	       while(i<losung.length()) {
	    	   
	    	   int p = permutation1 [i];
	    	   while (p<klartext.length()) {

	        		
	        		new_str += klartext.charAt(p);
	        		p+=losung.length();
	    	   }
	        i++;
	        
	        }
			return new_str;
	    }


	/**
	 *permutation der losung indexweise
	 *
	 */
	public void returnPermutation() {

		returnInverseVonPermutation = new int[this.losung.length()];
		int pos = 0;
		char i = 'a';
		while (i <= 'z') {
			for (int j = 0; j < losung.length(); j++) {
				if (i == this.losung.toLowerCase().charAt(j) || i == this.losung.toUpperCase().charAt(j)) {
					//perm[j] = pos;
					returnInverseVonPermutation[pos] = j;
					pos++;
				}
			}
			i++;
		}
	}

	/**
	 * @param geheimtext ist was man entschuesseln moechte
	 * @return ist das Ergebnis dieser Methode und diese Ergebniss ist der klartext
	 * klassen invarianten : geheimtext muss nicht null sein und muss ein String sein .muss ein klartext zuruck liefert der entspricht genau den Wuerfel
	 * verschluesselung prinzip
	 */
	    @Override
		public String dekodiere(String geheimtext) {
			int size = geheimtext.length() / losung.length(); // size  ist die Anzahl der Zeilen.
			int modulo = geheimtext.length() % losung.length(); // anzahl von stellen die size+ 1 bekommen müssen
			char[][] myCharArray = new char[size + 1][losung.length()]; // char wird aus sicherheitsgrunden immer mit size+1 initialisiert
			int counter = 0; // counter ist einen Pointer auf dem Geheimtext
			int i = 0;
			while (i < returnInverseVonPermutation.length) { //durchlaufen unsere inversepermArray
				if (returnInverseVonPermutation[i] < modulo) { // reversperm <5 zb dan wird anzahl spalte incremetiert
					int j = 0;
					while (j < size + 1) {
						myCharArray[j][returnInverseVonPermutation[i]] = geheimtext.charAt(counter++);
						j++;
					}
				} else {//else nur die 3 zeilen fuellen
					for (int j = 0; j < size; j++) {
						myCharArray[j][returnInverseVonPermutation[i]] = geheimtext.charAt(counter++);
					}
				}
				i++;
			}
			String container = "";
			int k = 0;
			while (k < myCharArray.length) {
				int j = 0;
				while (j < myCharArray[k].length) {
					if (myCharArray[k][j] != '\0')
						container += myCharArray[k][j];
					j++;
				}
				k++;
			}
			return container;
		}

	/**
	 * klassen invarianten: der zuruck gegebene Wert muss gleich das entsprechende losungswort
	 * @return gibts das losungswort zuruck
	 */
		@Override
		public String gibLosung() {

	        return losung;
	    }

	/**
	 * @param schluessel die losung wird gesetzt
	 * @throws IllegalArgumentException bei ungeeignette schluessel die meldung wird geworfen
	 * Klassen invarianten: das SChluessel darf kein Sondern zeichen haben. das schluessel muss nicht null sein und muss ein String sein.
	 */
	    @Override
	    public void setzeLosung(String schluessel) throws IllegalArgumentException {
	        //verschiebung =schluessel.length();
	        losung = schluessel;
			returnPermutation();
			if(schluessel.contains("ä")||schluessel.contains("ö")||schluessel.contains("ß")||schluessel.contains("ü") )
			throw new IllegalArgumentException ("es darf kein sondern zeichen eingetragen werden bitte noch mal versuchen");

			if(schluessel.isEmpty())
				throw new IllegalArgumentException ("der schluessel muss nicht leer sein.bitte gut eintragen.");
	    }

	}
	

