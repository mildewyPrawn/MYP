import java.util.ArrayList;

public class Devela{

    public int binarioADec(String cadena){
	int numero = Integer.parseInt(cadena);
	int digito;
	int exponente = 0;
	int numBin = 0;
	while(numero != 0){
	    digito = numero % 10;
	    numBin = numBin + digito * (int)Math.pow(2,exponente);
	    exponente++;
	    numero = numero / 10;
	}
	return numBin;
	
    }

    public char decATxt(int i){
	char c = (char)(i);
	return c;
    }
    
    public ArrayList<String> separaBits(String mensBin){
	ArrayList<String> byt = new ArrayList<String>();
	int i = 0;
	while(i < mensBin.length()){
	    int j = 0;
	    while(j < 8){
		
		i++;
	    }
	}
	return byt;
    }

    /*main

		String prueba = args[0];
		System.out.println(prueba + " este es un Caracter");
		String bin = oc.cadenaABinario(prueba);
		System.out.println(bin + " este es el numero en binario");
		String binCero = oc.cambiaFormatoDeBinario(bin);
		System.out.println(binCero);
		int dec = de.binarioADec(binCero);
		System.out.println(dec);
		char car = de.decATxt(dec);
		System.out.println(car);
    */
}
