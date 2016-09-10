import easyIO. *;


class Oblig3Pi {
    public static void main(String[] args) {
	if (args.length != 2) {
	    //"Feilmelding"
	    System.out.print("Trenger 2 parametere fra kommandolinja.");
	    System.out.println(" <Antall siffer> og <resultatfil.txt>");
	} else {
	    int n = Integer.parseInt(args[0]);
	    String res = args[1];
	    
	    Pi pi = new Pi(n, res);
	    pi.print();
	}
    }
}


class Pi {
    Out skjerm = new Out();
    int n;
    int num = n/4;
    String filnavn;
    int[] sifferAvPi = new int[num];

    //Konstruktør
    Pi(int n, String res) {
	this.n = n;
	filnavn = res;
	
	//Kaller metodene sub() og add()
	add(n, 5, 16);
	sub(n, 239, 4);
    }


    void add(int a, int b, int c) {
	//Lager et Arctan objekt som regner ut arctan med parameterene 
	//og adderer så serie[] til sifferAvPi[] med hjelp av addisjonsmetoden i Arctan
 	Arctan arc5 = new Arctan(a, b, c);
	arc5.addisjon(sifferAvPi, arc5.serie);
    }

    //Tilsvarende som metoden add ^^
    void sub(int a, int b, int c) {
	Arctan arc239 = new Arctan(a, b, c);
	arc239.subtraksjon(sifferAvPi, arc239.serie);
    }

    
    void print() {
	// legger til nuller der det trengs utenom i sifferAvPi[0]
	for (int i=0; i<num; i++) {
	    if (i == 0) {
		skjerm.out(sifferAvPi[i]);
	    } if (sifferAvPi[i] == 0 && i > 0) {
		skjerm.out("0000");
	    } else if (sifferAvPi[i] < 10) {
		skjerm.out("000"+sifferAvPi[i]);
	    } else if (sifferAvPi[i] <100) {
		skjerm.out("00"+sifferAvPi[i]);
	    } else if (sifferAvPi[i] < 1000) {
		    skjerm.out("0"+sifferAvPi[i]);
	    } else {
		skjerm.out(sifferAvPi[i]);
	    }
	}
	skjerm.outln();

	Out utFil = new Out(filnavn);

	// ikke helt ferdig..
	for (int j=0; j<num; j++) {
	    utFil.out(sifferAvPi[j]+" ");
	}
    }
}


class Arctan {
    Out skjerm = new Out();

    //statiske variabler 
    static int n;
    static int verdi;
    static int mult;

    static int max = 10000; //Grense for verdi til et element i en array.
    static int num = n/4; //Antall plasser i arrayene

    //Har ikke fått implementert denne enda, så er bare satt til 0. Det værste som skjer er
    //at det tar lengere tid å regne pi.
    static int isZero = 0;
    
    static int[] serie = new int[num];
    static int[] ledd = new int[num];

    //Konstruktøren, kaller metoden beregnSerie.    
    Arctan(int n, int verdi, int mult) {
	this.n = n;
	this.verdi = verdi;
	this.mult  = mult;

	beregnSerie();
    }
    
    void beregnSerie() {
	//Det første leddet
	int[] en = new int[num];
	en[0] = 1;
	addisjon(ledd, divisjon(en, verdi));
	
	//Adderer og substraherer leddene annen hver gang.
	for (int i=1; i<num; i++) {
	    if (i % 2 == 0) {
		addisjon(serie, lagNesteLedd(i));
	    } else {
		subtraksjon(serie, lagNesteLedd(i));
	    }
	}
	//Multipliserer serie med 16 eller 4 og er dermed ferdig å regne ut arctan ekspansjonen.
	multiplikasjon(serie, mult); 
    }

    //Lager neste ledd ved å 
    int[] lagNesteLedd(int i) {
	int[] A = new int[num];
	int x = 2*i - 1;
	int y = 2*i +1;

	addisjon(A, ledd); //Legger til forrige ledd til A[]
	multiplikasjon(A, x);
	divisjon(A, y);  //Metoden er ikke ferdig...
	ledd = A; //Setter leddet lik A, til neste iterasjon
    	return A; // Returnere int[] A til beregnSerie metoden
    }

    //Addisjonsmetode
    static void addisjon(int[] A, int[] B) {
	//A = A + B
	int res;
	for (int i = num; i>=isZero; i--) {
	    res = A[i] + B[i];
	    A[i] = res%max;
	    int j = i;
	    while(res >= max) {
		res = res/max + A[j-1];
		A[j-1] = res%max;
		j--;
	    }
	}
    } 

    //Subtraksjonsmetode
    static void subtraksjon(int[] A, int[] B) {
	int res;
	for (int i = num; i>=isZero; i--) {
	    res = A[i] - B[i];
	    A[i] = res;
	    if (res < 0) {
		A[i-1] -=1;
		A[i] +=max;
	    }
	}
    }

    //Multiplikasjonsmetode
    void multiplikasjon(int[] A, int X) {
	int res;
	int[] mente = new int[A.length];
	for (int i = num; i>=isZero; i--) {
	    res = A[i] * X;
	    A[i] = res % max;
	    if (res > max) {
		mente[i-1] = res/max;
	    }
	}
	addisjon(A, mente);
    }
    
    //Får ikke til metoden...
    int[] divisjon(int[] A, int X) {
	int[] B = new int[num];
	return B;
    }
}

/* Implementering av Strømers formel er veldig lett, alt vi trenger å gjøre er å kalle 3 til add
   metoder i Pi klassen og endre til riktige parametere */