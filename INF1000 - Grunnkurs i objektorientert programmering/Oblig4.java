import easyIO.*;
import java.util.*;

/**
 * Omsluttende klasse for problemet, tar opp parametre
 * fra kommandolinja og starter kommandoløkka. Feilmelding
 * hvis ikke minst to parametere.
 *********************************************************/
class Oblig4 {
    /**
     * Sjekker parametere, starter opp ordreløkka etter at
     * filene er lest via konstruktoren til 'MetInst'
     *****************************************************/
    public static void main(String[] args) {
	if (args.length>=2) {
	    MetInst m = new MetInst(args[0], args[1]);
	    m.ordreløkke();
	} else {
	    System.out.print("Bruk: >java Oblig4 <fil med Stasjonsdata>");
	    System.out.println(" <fil med Observasjonsdata>");
	}
    }
}

/**
 * Klasse som inneholder ordreløkka, metoder for å finne antall uværsdager,
 * sammenligne regn ved kyststasjoner mot innlandsstasjoner, sammenligne Østlandet mot 
 * Nord-Norge, finne kaldeste værstasjon og kaldeste mnd for denne værstasjonen, og metoder
 * for å lese stasjonsdata og klimadata.
 ******************************************************************************************/
class MetInst {
    In tast = new In();
    Out skjerm = new Out();

    /** Konstruktør, leser stasjonsdata og obervasjonsdata */
    MetInst(String stasjonsdata, String observasjonsdata) {
	lesStasjonsdata(stasjonsdata);
	lesObservasjonsdata(observasjonsdata);	
    }    
    
    /** Ordreløkke */
    void ordreløkke() {
	int kommando = 0;

	while (kommando != 5) {
	    skjerm.outln();
	    skjerm.outln("*** Meny ***");
	    skjerm.outln("1. Finn antall uværsdager");
	    skjerm.outln("2. Sammenlign regn ved kyststasjoner mot innlandsstasjoner");
	    skjerm.outln("3. Sammenlign Østlandet mot Nord-Norge");
	    skjerm.outln("4. Finn kaldeste værstasjon måned");
	    skjerm.outln("5. Avslutt");    
	    skjerm.out("Kommando: ");
	    kommando = tast.inInt();
	    skjerm.outln();
	    
	    switch(kommando) {
	    case 1: finnAntallUværsdager(); break;
	    case 2: sammenlignRegnVedKyststasjonerMotResten(); break;
	    case 3: sammenlignØstlandetMotNordNorge(); break;
	    case 4: finnKaldesteVærstasjonMåned(); break;
	    case 5: break;
	    default: break;
	    }
	}
    }
    
    /** Finner antall uværsdager i stasjon og måned, gitt av bruker */
     void finnAntallUværsdager() {
	skjerm.out("Stasjonsnummer: ");
	String stnr = tast.inWord();
	skjerm.out("Måned nr: ");
	String maaned = tast.inWord();
	
	Stasjon a = (Stasjon) stasjon_nr.get(stnr);
	Maaned b = (Maaned) a.m.get(maaned);
	skjerm.outln();
	skjerm.outln("Stasjon: "+a.navn+", Måned: "+maaned+", Antall uværsdager: "+
		     b.antallUværsdager());
    }

    /** Sammenligner regn ved kystenstasjoner mot innlandsstasjoner, svarer på
     * påstand om mer regn ved kyststasjonene
     *************************************************************************/
    void sammenlignRegnVedKyststasjonerMotResten() {
	Iterator it = stasjon_nr.values().iterator();
	double kyst_nedbør = 0;
	double innland_nedbør = 0;
	int kyst_mnd = 0;
	int innland_mnd = 0;

	for (Stasjon s: stasjon_nr.values()) {
	    double gj_snitt = 0;
	    int antall_mnd = 0;
	    Iterator it2 = s.m.values().iterator();

	    for (Maaned mnd: s.m.values()) {
		if(mnd.gjennomsnittNedbør() != -999) {
		    gj_snitt += mnd.gjennomsnittNedbør();
		    antall_mnd++;
		}
	    }
	    if (s.hoh < 60) {
		kyst_nedbør += gj_snitt;
		kyst_mnd += antall_mnd;
	    } else {
		innland_nedbør += gj_snitt;
		innland_mnd += antall_mnd;
	    }
	}
	double gj_snitt_kyst = kyst_nedbør/kyst_mnd;
	double gj_snitt_innland = innland_nedbør/innland_mnd;
	skjerm.out("Gjennomsnittlig nedbør på kysten pr. mnd: ");
	skjerm.out(gj_snitt_kyst, 4);
	skjerm.outln(" mm");
	skjerm.out("Gjennomsnittlig nedbør på innlandet pr. mnd: ");
	skjerm.out(gj_snitt_innland, 4);
	skjerm.outln(" mm");
	String b = "Nei!";
	if (gj_snitt_kyst > gj_snitt_innland) {
	    b = "Ja!";
	}
	skjerm.outln("Mer regn på kysten?: "+b);
    }

    /** 
     * Sammenligner gjennomsnittsnedbør og gjennomsnittstemperatur på Østlandet og
     * Nord-Norge, og skriver ut svar til fil ("Resultat.txt"). Svarer på påstand
     * om det er mindre nedbør og høyere temperatur på Østlandet enn i Nord-Norge.
     *****************************************************************************/
    void sammenlignØstlandetMotNordNorge() {
	skjerm.out("Måned nr: ");
	String maaned = tast.inWord();
	Iterator it = stasjon_nr.values().iterator();

	double temp_øst = 0;
	double temp_nn = 0;
	int stasjoner_øst_temp = 0;
	int stasjoner_nn_temp = 0;
	int stasjoner_øst_nedbør = 0;
	int stasjoner_nn_nedbør = 0;
	double nedbør_øst = 0;
	double nedbør_nn = 0;
		
	for (Stasjon s: stasjon_nr.values()) {
	    if (s.region.equals("ØSTLANDET")) {
		Maaned a = (Maaned) s.m.get(maaned);
		if (a.gjennomsnittTemp("TAM") != -999) {
		    temp_øst += a.gjennomsnittTemp("TAM");
		    stasjoner_øst_temp++;
		} if (a.gjennomsnittNedbør() != -999) {
		    nedbør_øst += a.gjennomsnittNedbør();
		    stasjoner_øst_nedbør++;
		}
	    } if (s.region.equals("NORD-NORGE")) {
		Maaned b = (Maaned) s.m.get(maaned);
		if (b.gjennomsnittTemp("TAM") != -999) {
		    temp_nn += b.gjennomsnittTemp("TAM");
		    stasjoner_nn_temp++;
		} if (b.gjennomsnittNedbør() != -999) {
		    nedbør_nn += b.gjennomsnittNedbør();
		    stasjoner_nn_nedbør++;
		}
	    }
	}
	double gj_temp_øst = temp_øst/stasjoner_øst_temp;
	double gj_temp_nn = temp_nn/stasjoner_nn_temp;
	double gj_nedbør_øst = nedbør_øst/stasjoner_øst_nedbør;
	double gj_nedbør_nn = nedbør_nn/stasjoner_nn_nedbør;
	
	Out utFil = new Out("Resultat.txt", true);
	utFil.outln("Valgt måned: "+maaned);
	utFil.outln();
	utFil.out("ØSTLANDET: \n"+"Gjennomsnitt temperatur: ");
	utFil.out(gj_temp_øst, 4);
	utFil.outln(" grader C");
	utFil.out("Gjennomsnitt nedbør: ");
	utFil.out(gj_nedbør_øst, 4);
	utFil.outln(" mm");
	utFil.outln();
	utFil.out("NORD-NORGE: \n"+"Gjennomsnitt temperatur: ");
	utFil.out(gj_temp_nn, 4);
	utFil.outln(" grader C");
	utFil.out("Gjennomsnitt nedbør: ");
	utFil.out(gj_nedbør_nn, 4);
	utFil.outln(" mm");
	utFil.outln();
	utFil.close();
	
	String b = "Nei!";
	if (gj_nedbør_øst < gj_nedbør_nn && gj_temp_øst > gj_temp_nn) {
	    b = "Ja!";
	}
	skjerm.outln("Mindre nedbør og høyere temperatur på Østlandet enn i Nord-Norge?: "+b);
    }

    /**
     * Finner kaldeste værstasjon i brukergitt måned, samt finner kaldeste måned
     * for den samme stasjonen.
     ***************************************************************************/     
    void finnKaldesteVærstasjonMåned() {
	skjerm.out("Måned nr: ");
	String maaned = tast.inWord();	
	Iterator it = stasjon_nr.values().iterator();

	double temp = 999; // slik at første maaned blir lagt til.
	String navn = "";
	
	for (Stasjon s: stasjon_nr.values()) {;
	    Maaned a = s.m.get(maaned);
	    if (a.gjennomsnittTemp("TAN") < temp && a.gjennomsnittTemp("TAN") != -999) {
		temp = a.gjennomsnittTemp("TAN");
		navn = s.navn;
	    }
	}
	Stasjon b = (Stasjon) stasjon_navn.get(navn);
	Iterator it2 = b.m.values().iterator();
	double temp2 = 999;
	String mnd = "";

	for (Maaned c: b.m.values()) {
	    if (c.gjennomsnittTemp("TAN") < temp2 && c.gjennomsnittTemp("TAN") != -999) {
		temp2 = c.gjennomsnittTemp("TAN");
		mnd = c.maaned;

	    }
	}
	skjerm.out("Kaldeste stasjon: "+navn+", Kaldeste mnd for samme stasjon: "+mnd);
    }
    
    HashMap <String,Stasjon> stasjon_nr = new HashMap <String,Stasjon>();
    HashMap <String,Stasjon> stasjon_navn = new HashMap <String,Stasjon>();
    
    /**
     * Leser stasjonsdata fra filnavn gitt som parameter på kommandolinja. Oppretter
     * stasjoner fortløpende og legger til relevant informasjon til stasjonsobjektet.
     ********************************************************************************/
    void lesStasjonsdata(String stasjonsdata) {
	In SD = new In(stasjonsdata);
	int i = 0;
	int antallLinjer = 26;

	SD.inLine();
	while (i++ < antallLinjer) {
	    String stnr = SD.inWord();
	    String navn = SD.inWord();
	    int hoh = SD.inInt();
	    String kommune = SD.inWord();
	    String fylke = SD.inWord();
	    String region = SD.inWord();
	    
	    Stasjon s = new Stasjon(stnr, navn, hoh, kommune, fylke, region);
	    stasjon_nr.put(s.stnr, s);
	    stasjon_navn.put(s.navn, s);   
	}
	SD.close();
    }

    /**
     * Leser observasjonsdata fra filnavn gitt som parameter på kommandolinja.
     * Kaller metode i stasjonsobjektet som legger til lest data.
     *************************************************************************/
    void lesObservasjonsdata(String observasjonsdata) {
	In OD = new In(observasjonsdata);
	while (!OD.lastItem()) {
	    String stnr = OD.inWord();
	    String dag = OD.inWord(".");
	    int m = OD.inInt(".");      // fjerner 0 før måneden
	    String maaned = m+"";
	    OD.inWord(".");             // hopper over år
	    double FFM = OD.inDouble();
	    double FFX = OD.inDouble();
	    double RR = OD.inDouble();
	    double SA = OD.inDouble();
	    double TAM = OD.inDouble();
	    double TAN = OD.inDouble();
	    double TAX = OD.inDouble();
	    
	    Stasjon a = (Stasjon) stasjon_nr.get(stnr);
	    a.leggTilData(dag, maaned, FFM, FFX, RR, SA, TAM, TAN, TAX);	    
	}
	OD.close();
    }
}

/**
 * Klasse for stasjoner med stasjonsnummer, navn, høyde over havet, kommune, fylke og region.
 ********************************************************************************************/
class Stasjon {
    String stnr;
    String navn;
    int hoh;
    String kommune;
    String fylke;
    String region;

    HashMap <String, Maaned> m = new HashMap <String, Maaned>();

    /** Konstruktør, legger til parameterene i objektvariablene. */
    Stasjon(String stnr, String navn, int hoh, String kommune, String fylke, String region) {
	this.stnr = stnr;
	this.navn = navn;
	this.hoh = hoh;
	this.kommune = kommune;
	this.fylke = fylke;
	this.region = region;
		
    }

    /** Oppretter månedsobjekter og "sender" data videre */    
    void leggTilData (String dag, String maaned, double FFM, double FFX, double RR, double SA,
		     double TAM, double TAN, double TAX) {
	boolean containsKey = m.containsKey(maaned);
	if (containsKey == true) {
	    Maaned a = (Maaned) m.get(maaned);
	    a.leggTilData(dag, FFM, FFX, RR, SA, TAM, TAN, TAX);
	} else {
	    Maaned b = new Maaned(maaned);
	    b.leggTilData(dag, FFM, FFX, RR, SA, TAM, TAN, TAX);
	    m.put(maaned, b);
	}	    
    }
}

/**
 * Klasse for måneder med månednummer. Inneholder hjelpemetoder for å finne antall uværsdager,
 * gjennomsnitts temperaturer og gjennomsnittsnedbør. Inneholder også en metode for å legge 
 * til data i dagobjekter. 
 *********************************************************************************************/
class Maaned {
    String maaned;

    Maaned(String maaned) {
	this.maaned = maaned;
    }

    HashMap <String,Dag> d = new HashMap <String,Dag>();

    /** Opretter dagobjekter og legger til data */
    void leggTilData(String dag, double FFM, double FFX, double RR, double SA, double TAM,
		     double TAN, double TAX) {
	boolean containsKey = d.containsKey(dag);
	if (containsKey == true) {
	    Dag a = (Dag) d.get(dag);
	    a.leggTilData(FFM, FFX, RR, SA, TAM, TAN, TAX);
	} else {
	    Dag b = new Dag(dag);
	    b.leggTilData(FFM, FFX, RR, SA, TAM, TAN, TAX);
	    d.put(dag, b);
	}		
    } 
    
    /** Returnerer antall uværsdager i måneden */
    int antallUværsdager() {
	int u = 0;
	Iterator it = d.values().iterator();

	for (Dag a: d.values()) {
	    double RR = a.RR;
	    double FFX = a.FFX;
	    if (a.RR == -999) {
		RR = 0;
	    }
	    if (RR+FFX>10.7) {
		u++;
	    }
	}
	return u;
    }
    
    /** Returnerer gjennomsnittsnedbøren i måneden */
    double gjennomsnittNedbør() {
	int dager = 0;
	double nedbør = 0;
	Iterator it = d.values().iterator();
	
	for (Dag a: d.values()) {
	    double RR = a.RR;
	    if (RR != -999) {
		nedbør += RR;
		dager++;
	    }
	}
	int ingenData = -999;
	// Hvis antall_dager = 0 så finnes det ingen data for denne måneden.
	if (dager == 0) {
	    return ingenData;
	} else {
	    double gj_snitt = nedbør/dager;
	    return gj_snitt;
	}
    }

    /** Returnerer gjennosnittstemperaturen i måneden for TAN eller TAM */
    double gjennomsnittTemp(String type) {
	int dager = 0;
	double temp =0;
	int ingenData = -999;
	Iterator it = d.values().iterator();

	if (type.equals("TAM")) {
	    for (Dag a: d.values()) {
		double TAM = a.TAM;
		if (TAM != -999) {
		    temp += TAM;
		    dager++;
		}
	    }
	} if (type.equals("TAN")) {
	    for (Dag a: d.values()) {
		double TAN = a.TAN;
		if (TAN != -999) {
		    temp += TAN;
		    dager++;
		}
	    }
	} if (dager == 0) {
	    return ingenData;
	} else {
	    double gj_snitt = temp/dager;
	    return gj_snitt;
	}
    }
}

/** Dagklasse som inneholder middel av vindhastigheter, høyeste vindhastighet,
 * nedbør snødybde, middeltemperatur, minimumstemperatur og maksimumstemperatur
 ******************************************************************************/
class Dag {
    String dag;

    Dag(String dag) {
	this.dag = dag;
    }

    double FFM;
    double FFX;
    double RR;
    double SA;
    double TAM;
    double TAN;
    double TAX;

    void leggTilData(double FFM, double FFX, double RR, double SA, double TAM, double TAN,
		     double TAX) {
	this.FFM = FFM;
	this.FFX = FFX;
	this.RR = RR;
	this.SA = SA;
	this.TAM = TAM;
	this.TAN = TAN;
	this.TAX = TAX;   
    }
}