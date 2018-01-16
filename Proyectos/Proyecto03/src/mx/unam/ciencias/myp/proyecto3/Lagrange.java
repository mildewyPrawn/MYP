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
    
    
    public String getEval(String claves){
	claves = claves.substring(1,claves.length() - 2);
	String[] puntos = claves.split(Pattern.quote(") ("));
	
	ArrayList<String> dots = new ArrayList<String>();
	ArrayList<String> ev = new ArrayList<String>();
	
	for(String s : puntos){
	    String[] temp = s.split(", ");
	    String p = temp[0];
	    String e = temp[1];
	    dots.add(p);
	    ev.add(e);
	}
	String[] punFin = new String[dots.size()];
	punFin = dots.toArray(punFin); 
	
	String[] evaFin = new String[ev.size()];
	evaFin = ev.toArray(evaFin);

	BigDecimal[] puntitos = stringToBigDecimal(punFin);
	BigDecimal[] eval = stringToBigDecimal(evaFin);
	BigDecimal[] bases = creaBases(puntitos);

	BigDecimal llave = obtenLlave(bases,eval);
	BigDecimal m = llave.remainder(new BigDecimal("208351617316091241234326746312124448251235562226470491514186331217050270460481"));
	
	BigInteger key;
	
      	key = m.toBigInteger();
	Polinomio poli = new Polinomio();
	String sha = poli.decToHex(key);
	return sha;
	
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
	double p = 1;
	BigDecimal base = null;

	double aux1 = 0;
	double aux2 = 0;
	double aux3 = 0; 

	for(int i=0; i<puntos.length; i++){
	    for(int j=0; j<puntos.length; j++){
		if(i == j){
		    continue;
		} else {
		    aux1 = x - puntos[j].intValue();
		    aux2 = puntos[i].intValue() - puntos[j].intValue();
		    aux3 = aux1 / aux2;
		    p = p * aux3;
		}
	    }
	    base = new BigDecimal(p);
	    bases[i] = base;
	    p = 1;
	    aux1 = 0;
	    aux2 = 0;
	    aux3 = 0;		
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
