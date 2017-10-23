import java.io.IOException;
import java.util.ArrayList;

public class Esteganografia{
	
    public static void main(String [] args){
	/*
	 * Por si nos pasan un numero de parametros distintos a los requeridos 
 	 * para ocultar o para develar mandamos un error. 
	 */
	if(args.length < 3 || args.length > 4){
	    System.out.println("Esteganografia: Error en argumentos.");
	    System.out.println("Esteganografia: Para OCULTAR un archivo intente");
	    System.out.println("'Esteganografia h archivoAOcultar.txt imagen.png nuevaImagen.png'");
	    System.out.println("Esteganografia: Para DEVELAR un archivo intente");
	    System.out.println("'Esteganografia u imagenConArchivosOcultos.png nuevaImagen.png'");
	    System.exit(1);
	}
	try{
	    String flag = args[0];
	    if(flag.equals("h")){
		if(args.length == 3){
		    System.out.println("Esteganografia: Error en argumentos.");
		    System.out.println("Esteganografia: Para OCULTAR un archivo intente");
		    System.out.println("'Esteganografia h archivoAOcultar.txt imagen.png nuevaImagen.png'");
		    System.exit(1);
		}
		System.out.println("OCULTAR");
		Oculta oc = new Oculta();
		Devela de = new Devela();
	        ArrayList<String> als = new ArrayList<String>();
		als = oc.muestraContenido(args[1]);
		String s = oc.muestraMensaje(als);
		String t = oc.cambiaFormato(s);
		String r = oc.cadenaABinario(t);
		String i = oc.cambiaFormatoDeBinario(r);//mensaje en binario
		//System.out.println(i);

		ArrayList<String> emi = new ArrayList<String>();
		emi = oc.cambiaLista(i);
		//		oc.ocultaMsje(args[2], args[3]);
		//		oc.ocultaMsje(args[2], args[3], emi);
		System.out.println(emi + "que pedo ");
		//		int j = oc.cambiaUnoOCero("01000000");
	        
	    }else if(flag.equals("u")){
		if(args.length == 4){
		    System.out.println("Esteganografia: Error en argumentos.");
		    System.out.println("Esteganografia: Para DEVELAR un archivo intente");
		    System.out.println("'Esteganografia u imagenConArchivosOcultos.png nuevaImagen.png'");
		    System.exit(1);
		}
		System.out.println("DEVELAR");
	    }else{
		System.out.println("Esta bandera '" + flag + "' no es una opcion.");
		System.exit(1);
	    }
	}catch(IOException ioe){
	    System.out.println("Error en el archivo de lectura.");
	    System.exit(1);
	}
    }
}
