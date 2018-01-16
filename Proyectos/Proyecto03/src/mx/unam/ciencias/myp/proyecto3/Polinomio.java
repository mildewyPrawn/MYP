package mx.unam.ciencias.myp.proyecto3;

import java.io.File;
import java.util.Random;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.math.BigDecimal;
import java.io.BufferedWriter;

public class Polinomio{

    /* Número decimal del hash.*/
    private BigInteger bi;

    /* El nombre para guardar el archivo.*/
    private String name;

    /* Las n claves */
    private int n;

    /* t de las n claves */
    private int t;

    /* Campo en el que vamos a trabajar. */
    private BigInteger PRIMO = new BigInteger("208351617316091241234326746312124448251235562226470491514186331217050270460481");
    
    /** Constructor sin parámetros. Para no perder el constructor sin parámetros.
     */
    public Polinomio() {}

    /** Constructor para Polinomio.
     * @param la dispersión de la cual se quiere obtener K, el término independiente.
     * @param el nombre en donde guardar las claves.
     * @param las n claves.
     * @param t de las n claves para evaluar el polinomio.
     */
    public Polinomio(String hash, String name, int n, int t){
	this.bi = hexToDec(hash);
	this.name = name;
	this.n = n;
	this.t = t;
    }
    
    /**
     * Método que llena un arreglo para tener un polinomio válido.
     * @return un polinomio con su primer entrada el término independiente.
     */
    public BigInteger[] llena(){
	BigInteger[] polinomio = new BigInteger[t];
	polinomio[0] = bi;
	for(int i = 1; i < t ; i++){
	    //BigInteger random = new BigInteger(256, new Random());
	    int r = (int)(Math.random()*1000) + 1;
	    BigInteger random = new BigInteger(r + "");
	    if(random.toString().equals("0"))
		i--;
	    BigInteger m = random.mod(PRIMO);
	    polinomio[i] = m;
	}
	return polinomio;
    }

    /**
     * Método que se engarga de evaluar un polinomio.
     * @param <code>x</code> el número en el que se va a evaluar.
     * @param <code>polinomio</code> el polinomio a evaluar.
     * @return el resultado de evaluar el polinomio.
     */
    public BigInteger evaluar(int x, BigInteger[] polinomio){
	BigInteger valor = new BigInteger("0");
	for(int i = polinomio.length-1; i >= 0; i--){
	    valor = polinomio[i].add(valor.multiply(new BigInteger(x + "")));
	}
	return valor;
    }    

    /**
     * Método que se encarga de obtener las n claves.
     * @param <code>n</code> la cantidad de claves que se requieren.
     * @param <code>polinomio</code> el polinomio en el que se van a evaluar.
     * @param <code>modulo</code> el campo en el que se va a trabajar.
     * @return las <code>n</code> claves para compartir.
     */
    public String clave(int num, BigInteger[] polinomio, BigInteger modulo){
	String claves = "";
	for(int i = 1; i <= num; i++){
	    BigInteger temp = evaluar(i, polinomio);
	    temp = temp.mod(modulo);
	    claves += claves.format("(%s, %s)\n",i,temp);
	}
	return claves;
    }

    /**
     * Método principal de la clase, se encarga de llamar a los métodos
     * que corresponden.
     */
    public void calcula(){
	BigInteger[] polinomio = llena();
	String s = clave(n, polinomio, PRIMO);//secreto
	escribirArchivo(s, "./" + name);
    }

    /**
     * Metodo que escribe un archivo con el mensaje que recibe.
     * @param el mensaje que vamos a escribir y la ruta del mensaje.
     */
    private void escribirArchivo(String msje, String arch){
	File f = new File(arch);
	try{
	    FileWriter fw = new FileWriter(f);
	    BufferedWriter bw = new BufferedWriter(fw);
	    PrintWriter pw = new PrintWriter(bw);
	    pw.write(msje);
	    pw.close();
	    bw.close();
	}catch(IOException ioe){
	    System.out.println("Hubo un error al crear el archivo " + arch);
	    System.exit(1);
	}
    }
    
    /***
     * Método que cambia un número en hexadecimal a decimal.
     * @param <code>s</code> cadena en hexadecimal.
     * @return representación en decimal.
     */
    public BigInteger hexToDec(String s){
	String digits = "0123456789abcdef";
	BigInteger val = new BigInteger("0");
	for(int i = 0; i < s.length(); i++){
	    char c = s.charAt(i);
	    int d = digits.indexOf(c);
	    val = val.multiply(new BigInteger("16"));
	    val = val.add(new BigInteger(d + ""));
	}
	return val;
    }

    /**
     * Método que pasa un decimal a su representación en hexadecimal.
     * @param <code>decimal</code> del que se quiere su representación en hexadecimal.
     * @return representación de <code>decimal</code> en hexadecimal.
     */
    public String decToHex(BigInteger decimal){
	String s = decimal.toString(16);
	return s;
    }
    
}
