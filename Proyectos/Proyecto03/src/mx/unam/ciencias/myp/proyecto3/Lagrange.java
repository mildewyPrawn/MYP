package mx.unam.ciencias.myp.proyecto3;

import java.io.File;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.math.BigInteger;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.io.BufferedWriter;
import java.util.regex.Pattern;

public class Lagrange{

    private final BigDecimal PRIMO = new BigDecimal("208351617316091241234326746312124448251235562226470491514186331217050270460481");
    
    public String getEval(String claves){//regresa la llave en hexa
	claves = claves.substring(1,claves.length() - 2);
	String[] puntos = claves.split(Pattern.quote(") ("));
	
	ArrayList<String> dots = new ArrayList<String>();
	ArrayList<String> ev = new ArrayList<String>();

	//	imprimeS(puntos);
	
	for(String s : puntos){
	    String[] temp = s.split(", ");
	    String p = temp[0];
	    String e = temp[1];
	    dots.add(p);
	    ev.add(e);
	}

	//	imprimeS(puntos);
	
	String[] punFin = new String[dots.size()];
	punFin = dots.toArray(punFin); 
	
	String[] evaFin = new String[ev.size()];
	evaFin = ev.toArray(evaFin);

	//	imprimeS(punFin);imprimeS(evaFin);

	BigDecimal[] punt = stringToBigDecimal(punFin);
	BigDecimal[] eval = stringToBigDecimal(evaFin);
	//	imprime(punt);imprime(eval);///mandas a imprimir punt y eval
	
	BigDecimal[] bases = creaBases(punt);

	BigDecimal llave = obtenLlave(bases, eval);
	BigDecimal m = llave.remainder(PRIMO);
	BigInteger key;
	key = m.toBigInteger();
	//	System.out.println(key + "             llave ");
	Polinomio poli = new Polinomio();
	String hex = poli.decToHex(key);
	//	System.out.println(hex);
	return hex;
    }

    public void imprimeS(String[] sa){
	for(int i = 0; i < sa.length; i++)
	    System.out.println(sa[i]);
    }
    
    public void imprime(BigDecimal[] sa){
	for(int i = 0; i < sa.length; i++)
	    System.out.println(sa[i]);
    }

    public BigDecimal[] stringToBigDecimal(String [] datos){
	BigDecimal[] puntosOEval = new BigDecimal[datos.length];
	for (int i = 0; i < datos.length; i++) {
	    puntosOEval[i] = new BigDecimal(datos[i]);
	}
        return puntosOEval;
    }
    
    public BigDecimal[] creaBases(BigDecimal[] puntos){
	
	BigDecimal[] bases = new BigDecimal[puntos.length];
	int x = 0;
	BigDecimal p = new BigDecimal(1);
	
	for(int i=0; i<puntos.length; i++){
	    for(int j=0; j<puntos.length; j++){
		if(i == j){
		    continue;
		} else {
		    	    p = p.multiply(((new BigDecimal(x)).subtract(puntos[j])).divide(puntos[i].subtract(puntos[j]), 2, RoundingMode.HALF_DOWN));
		    //		    p = p.multiply(((new BigDecimal(x)).subtract(puntos[j]), 5, RoundMode.HALF_UP).divide(puntos[i].subtract(puntos[j]), 5, RoundingMode.HALF_UP));//5 casi da
		}
	    }
	    
	    BigDecimal b = p;
	    bases[i] = b;
	    p = new BigDecimal(1);		
	}	
	
	return bases;
	
    }
    
    public BigDecimal obtenLlave(BigDecimal[] bases,BigDecimal[] eval){
	BigDecimal llave = new BigDecimal("0");
	
	for(int i=0; i<bases.length;i++){
	    BigDecimal evalAct = eval[i];
	    llave = llave.add(bases[i].multiply(evalAct));
	}
	
	return llave;
    }
}
