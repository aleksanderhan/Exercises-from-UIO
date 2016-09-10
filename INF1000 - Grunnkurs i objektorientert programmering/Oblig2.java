import easyIO.*;

class Oblig2 {
    public static void main(String[] args) {
        Olje ol = new Olje();
	System.out.println("*** VELKOMMEN TIL UTOPIAS OLJEFELTSOVERSIKT ***");
	System.out.println();
        ol.ordreløkke();
        System.out.println("Takk for nå");
    }
}

class Olje {
    In tast = new In();
    Out skjerm = new Out();

    //arrayer som holder navn på eier og utvunnete fat
    String[][] eier = new String[11][17];
    int[][] utvunnet = new int[11][17];

    void ordreløkke() {
        int ordre = 0;

	//menyvalg
        while (ordre != 6) {
	    skjerm.outln("Du har følgende valgmuligheter:");
	    skjerm.outln("1) Kjøp felt");
	    skjerm.outln("2) Lag feltliste");
	    skjerm.outln("3) Lag oversiktskart");
	    skjerm.outln("4) Halvårsoppdatering");
	    skjerm.outln("5) Lag statistikk");
	    skjerm.outln("6) Avslutt");
	    skjerm.out("Velg kommando: ");
            ordre = tast.inInt();
            switch (ordre) {
                case 1: kjøpFelt(); break;
                case 2: lagFeltliste(); break;
                case 3: lagOversiktskart(); break;
	        case 4: halvårsoppdatering(); break;
	        case 5: lagStatistikk(); break;
	        case 6: avslutt(); break;
                default: break;
            }
        }
    }

    void kjøpFelt() {
	skjerm.outln("** Kjøp felt **");
	skjerm.out("Oppgi ønsket felt: ");

	//brukerinput for ønsket felt
	int radnr = tast.inInt("-\n\r ");
        int kolnr = tast.inInt("-\n\r ");

	skjerm.out("Oppgi oljeselskapets navn: ");

	String navn = tast.inLine();

	//hvis feltet er uten eier fra før
	if (eier[radnr][kolnr] == null) {
	    eier[radnr][kolnr] = navn;
	    skjerm.outln("Felt "+radnr+"-"+kolnr+" er nå kjøpt av "+navn);
	    skjerm.outln();

	    //hvis feltet har samme eier fra før
	} else if (eier[radnr][kolnr].equals(navn)) {
	    skjerm.out("Oljeselskapet "+navn+" eier feltet "+radnr);
	    skjerm.outln("-"+kolnr+" fra før!");
	    skjerm.outln();

	    //hvis feltet eies av et annet selskap fra før
	} else {
	    skjerm.outln("Eier av feltet er "+eier[radnr][kolnr]);
	    skjerm.out("Vil du virkelig gjennomføre kjøpet? (ja/nei): ");

	    String svar = tast.inWord();

	    if (svar.equals("ja")) {
		eier[radnr][kolnr] = navn;
		skjerm.outln("Felt "+radnr+"-"+kolnr+" er nå kjøpt av "+navn);
		skjerm.outln();
	    } else {
		skjerm.outln("Ingen endring ble registrert");
		skjerm.outln();
	    }
	}

    }

    void lagFeltliste() {
	skjerm.outln("** Lag feltliste **");

	//loop som skriver ut felt med eier og totalt antall fat utvunnet
	for (int i=0; i<11; i++) {
	    for (int j=0; j<17; j++) {
		if (eier[i][j] != null) {
		    skjerm.out("Feltet "+i+"-"+j+" eies av "+eier[i][j]+". ");
		    skjerm.outln("Total utvinning i dette feltet er "+utvunnet[i][j]+" fat.");
		}
	    }
	}
	skjerm.outln();
    }

    void lagOversiktskart() {
	skjerm.outln("** Lag oversiktskart **");

	int antallSolgte = 0; //antall solgte felt

	for (int i=0; i<11; i++) {
	    skjerm.outln(); //hopper til neste linje etter første rad er skrevet ut
	    for (int j=0; j <17; j++) {

		//hvis feltet ikke er kjøpt
		if (eier[i][j] == null) {
		    skjerm.out(". ");

		    //hvis feltet er kjøpt
		} else {
		    skjerm.out("x ");
		    antallSolgte += 1;
		}
	    }
	}
	skjerm.outln();

	//187.0 fordi 11x17=187 og for å unngå heltallsdivisjon
	double prosent = 100*antallSolgte/187.0; 

	skjerm.out("Antall solgte felt: "+antallSolgte+" (");
	skjerm.out(prosent, 1); //1 desimal
	skjerm.outln("% av feltene)");
	skjerm.outln();
    }

    void halvårsoppdatering() {
	skjerm.outln("** Halvårsoppdatering **");

	//loop over eier[][] for å spørre bruker om utvunnede fat i felt med eier
	for (int i=0; i<11; i++) {
	    for (int j=0; j<17; j++) {
		if (eier[i][j] != null) {
		    skjerm.out("Antall fat utvunnet i felt "+i+"-"+j);
		    skjerm.out(" ("+eier[i][j]+") siste 6 mnd.: ");

		    int fat = tast.inInt();

		    utvunnet[i][j] += fat;
		}
	    }
	}
    }
 
    void lagStatistikk() {
	skjerm.outln("** Lag statistikk **");

	double totalFat = 0; //double variabel for å unngå heltallsdivisjon senere
	int størsteFat = 0;
	int feltKjøpt = 0;

	//187 fordi det er maks antall selskaper
	String alleFelt[] = new String[187]; //array med alle felts eiere
	String selskaper[] = new String[187]; //array med unike eiere
	
	for (int i=0; i<11; i++) {
	    for (int j=0; j<17; j++) {
		if (eier[i][j] != null) {
		    totalFat += utvunnet[i][j];
		    feltKjøpt += 1;

		    //loop for å få alle felts eiere inn i egen array
		    for (int k=0; k<187; k++) {
			if (alleFelt[k] == null) {
			    alleFelt[k] = eier[i][j];
			    break;
			}
		    }
		    //hvis et større antall fat i et annet felt finnes overtar det plassen
		} if (størsteFat <  utvunnet[i][j]) {
		    størsteFat = utvunnet[i][j];
		}
	    }
	}

      	int a = 0;		
      	
      	for (int b=0; b<187; b++) {

	    //boolsk variabel for å holde styr på om eiere finnes i selskaper[]
	    boolean finnes = false;
	    
	    //loop for å finne ut om eieren finnes i selskaper[]
	    for (int c=0; c<187; c++) {
		if (alleFelt[b] != null && selskaper[c] != null) {
		    if (alleFelt[b].equals(selskaper[c])) {
			finnes = true;
		    }
		}
	    }

	    //hvis eieren ikke er i selskaper[] legges den til	    
	    if (finnes == false) {
		selskaper[a] = alleFelt[b];
		a += 1;
	    }	   
      	}

	int antallSelskaper = 0;

	//loop for å finne ut hvor mange selskaper som er i selskaper[]
	for (int d=0; d<187; d++) {
	    if (selskaper[d] != null) {
		antallSelskaper += 1;
	    }
	}
	skjerm.outln("Antall fat olje som er utvunnet totalt: "+totalFat+" fat");
	skjerm.outln("Største antall fat olje som utvinnes i et enkelt felt: "+størsteFat+" fat");
	skjerm.out("Gjennomsnittlig antall fat olje som utvinnes i et felt: ");
	skjerm.outln(totalFat/feltKjøpt+" fat");
	skjerm.outln("Antall oljeselskaper: "+antallSelskaper);
	skjerm.outln();
    }

    void avslutt(){
	//siden programmet ikke skal lagre dataene er det ikke nødvendig med noe her.
    }
}

