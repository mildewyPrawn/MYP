/********************************************
 * Semanal01.
 * Combinaciones.java
 * Universidad Nacional Autonoma de Mexico
 * Facultad de Ciencias 
 * @authors EmilianoGaleana 31403232-4
 *          
 *          
 *          
 * Combinaciones de r en n.
 * @mails galeanaara@ciencias.unam.mx
 *        
 *        
 *        
 * ******************************************
 */

import java.math.BigInteger;

public class Combinacion{

    /**
     * Semanal01: Combinaciones.
     */
    public static void main(String args[]){
	
	/*
	 * Si no pasan parametros, terminamos
	 */
	if(args.length < 2){
	    System.out.println("Combinaciones: faltan argumentos como operandos.");
	    System.out.println("Combinaciones: pruebe 'Combinaciones n r' con n y r numeros naturales.");
	}

	/*
	 * Si nos pasan mas de dos paramatros terminamos
	 */
	if(args.length > 2){
	    System.out.println("Combinaciones: sobran argumentos como operandos.");
	    System.out.println("Combinaciones: pruebe 'Combinaciones n r' con n y r numeros naturales.");
	}
	
	try{
	    int n = Integer.parseInt(args[0]);
	    int r = Integer.parseInt(args[1]);
	    
	    if(n < r){
		System.out.println("Combinaciones: error en argumentos.");
		System.out.println("Combinaciones: pruebe 'Combinaciones n r' con n mayor que r");
		System.exit(0);
	    }
	    Calcula c = new Calcula();
	    BigInteger res = c.formula(n,r);
 	}catch(NumberFormatException nfe){
	    System.out.println("Combinaciones: error en argumentos.");
	    System.out.println("Combinaciones: pruebe 'Combinaciones n r' con n y r numeros naturales.");
	}catch(ArrayIndexOutOfBoundsException aiofe){
	    System.exit(0);
	}
    }
}
	    
