//import java.io.BufferedReader;
import java.io.*;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
import java.awt.*;
import java.awt.image.BufferedImage;
//import java.io.File;
import javax.imageio.ImageIO;
import java.util.ArrayList;

public class Oculta{
    
    public ArrayList<String> muestraContenido(String archivo) throws FileNotFoundException, IOException{
	ArrayList<String> mensaje = new ArrayList<String>();
	String cadena;
	FileReader fr = new FileReader(archivo);
	BufferedReader br = new BufferedReader(fr);
	while((cadena = br.readLine()) != null )
	    mensaje.add(cadena);
	br.close();
	return mensaje;
    }
    
    public String muestraMensaje(ArrayList<String> arlsm){
	String s = "";
	for(int i = 0; i < arlsm.size(); i++){
	    s += arlsm.get(i);
	}
	return s;
    }

    public static String cadenaABinario(String cadena){
	String cb = "";
	int longitud;
	for(int i = 0; i < cadena.length(); i++){
	    cb += String.format("%8s",Integer.toBinaryString(cadena.charAt(i)));
	}
	return cb;
    }

    public static String cambiaFormato(String cadena){
	cadena = cadena.toUpperCase();
	cadena = cadena.replaceAll("Ñ","N");
	cadena = cadena.replaceAll("Á","A");
	cadena = cadena.replaceAll("É","E");
	cadena = cadena.replaceAll("Í","I");
	cadena = cadena.replaceAll("Ó","O");
	cadena = cadena.replaceAll("Ú","U");
	cadena = cadena.replaceAll(","," ");
	cadena = cadena.replaceAll(" ", "");
	return cadena;
    }

    public String cambiaFormatoDeBinario(String binario){
 	return binario.replaceAll(" ","0");
    }

    //    private int cambiaUC(String s){
    public String cambiaUnoOCero(String s){
	if(s.length() != 8){
	    System.out.println("Ocurrió un error al ocultar el mensaje");
	    System.exit(1);
	}
	int i = 0;
	int l = s.length();
	String num = "";
        char n;
	char u = '1';
	char c = s.charAt(l-1);
	while(i < l-1){
	    num += s.charAt(i);
	    i++;
	}
	if(c == u)
	    n = '0';
	else
	    n = '1';
	String a = num + n;
	//int numero = Integer.parseInt(a);
	//	System.out.println("         " + s);
	//	System.out.println("Regresa: " + numero);
	return a;
    }

    public ArrayList<String> meteCadaOcho(String mensaje){
	ArrayList<String> alsco = new ArrayList<String>();
	int i = 0;
	int j = 8;
	String s = "";
	while(i < mensaje.length()){
	    while(i < j){
		s += mensaje.charAt(i);	        
		i++;
		if(s.length() == 8)
		    alsco.add(s);
	    }
	    s = "";
	    j += 8;
	}
	return alsco;
    }

    public ArrayList<String> cambiaLista(String mensaje){
	ArrayList<String> als = new ArrayList<String>();
	ArrayList<String> ali = new ArrayList<String>();
	Oculta o = new Oculta();
	als = o.meteCadaOcho(mensaje);
	int i = 0;
	while(i < als.size()){
	    String j;
	    String s = als.get(i);
	    j = o.cambiaUnoOCero(s);
	    ali.add(j);
	    i++;
	}
	return ali; 
    }
    public void ocultaMsje(String name, String nueName){
    //    public void ocultaMsje(String name, String nueName, ArrayList<String> als){
	BufferedImage image;
	int width;
	int height;
	//	String s = muestraMensaje(als);
	try{
	    File input = new File(name);
	    image = ImageIO.read(input);
	    width = image.getWidth();
	    height = image.getHeight();
	    //  if((s.length()/8) > (width*height)){
	    //	System.out.println("El mensaje no cabe en la imagen.");
	    //	System.exit(1);
	    //}
	    int count = 0;
	    for(int i = 0; i < height; i++)
		for(int j = 0; j < width; j++){
		    Color c = new Color(image.getRGB(j,i));
		    //		    int alpha = c.getAlpha();
		    //int blue = c.getBlue();
		    //int green = c.getGreen();
		    //int red = c.getRed();
		    //imprimeBit(alpha, blue, green, red, count);
		    int a = cambiaBitA(c.getAlpha());
		    int b = cambiaBitA(c.getBlue());
		    int g = cambiaBitA(c.getGreen());
		    int r = cambiaBitA(c.getRed());
		    Color newColor = new Color(b+g+r, b+g+r, b+g+r);
		    image.setRGB(j,i,newColor.getRGB());
		    
		    imprimeBit(a, b, g, r, count);
		    
		    //	    if(count == s.length()){
		    //	System.out.println(count);
		    //	System.out.println("Se han recorrido " + count + " bits de la imagen");
		    //		System.exit(1);
		    //}
		    count++;
		}
	    File output = new File(nueName);
	    ImageIO.write(image,"jpg",output);
	}catch(Exception e){
	    System.err.println("El programa no pudo leer la imagen.");
	    System.exit(1);
	}
    }

    private String ultimoDigito(String s){
	String first = s.substring(0,7);
	String last = s.substring(7,8);
	int i = Integer.parseInt(last);
	int j = cambia(i);
	last = Integer.toString(j);
	return first + last;
    }
    
    public int cambiaBitA(int a){
	int i  = 0;
	String s = "";
	s = Integer.toString(a);
	String r = cadenaABinario(s);
	s = cambiaFormatoDeBinario(r);
	r = ultimoDigito(s);
	i = binarioADec(r);
	return i;
    }
    
    private void imprimeBit(int a, int b, int g, int r, int c){
	System.out.println(c + " : " + a  + " : " + b + " : " + g + " : " + r);
    }

    private int cambia(int i){
	return i == 1 ? 0 : 1;
    }

    private char decATxt(int i){
	char c = (char)(i);
	return c;
    }
    
    private int binarioADec(String cadena){
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


}
