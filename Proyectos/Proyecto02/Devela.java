import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Devela {
    
    /**
     * Metodo que escribe un archivo con el mensaje que recibe.
     * @param el mensaje que vamos a escribir y la ruta del mensaje.
     */
    private void escribirArchivo(String msje, String arch){
        File f = new File(arch);
        if(f.exists()){
            System.out.println("El archivo tiene un nombre existente.");
            System.exit(1);
        }else{
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
    }

    /**
     * Metodo principal que recibe los argumentos desde el maina.
     * hace llamadas a metodos auxiliares.
     * @param la ruta de la foto y la ruta del archivo donde se quiere escribir.
     */
    public void develarMensaje(String uno, String dos){
	String mensaje = sacaMensaje(uno);
	escribirArchivo(mensaje, dos);
    }

    /**
     * Metodo con el que obtenemos la longitud del mensaje, esta escondido 
     * dentro del mensaje y asi sabemos cuando termina el mensaje.
     * @param la imagen de la que queremos obtener el mensaje.
     * @return la longitud del mensaje.
     */
    private int longitudMensaje(BufferedImage f){
	int longitud;
	Color color;
	String t = "";
	for(int j = 5; j < 12; j++){
	    color = new Color(f.getRGB(j,0));
	    String red = toBinary( (byte) color.getRed() );
	    String green = toBinary( (byte) color.getGreen() );
	    String blue = toBinary( (byte) color.getBlue() );
	    red = getLSB(red);
	    green = getLSB(green);
	    blue = getLSB(blue);
	    t += red + green + blue;
	}
	longitud = binarioADec(t.substring(1, 17));
	return longitud;
    }

    /**
     * metodo que busca la imagen y verifica si existe o no.
     * @param la direccion de la imagen que queremos encontrar.
     * @return la imagen dada la direccion recibida.
     */
    private BufferedImage sacaFoto(String fo){
	File input = null;
	BufferedImage image = null;
	try{
	    input = new File(fo);
	    image = ImageIO.read(input);
	}catch(IOException ioe){
	    System.out.println("Hubo un error en la lectura de la imagen");
	    System.exit(1);
	}
	return image;
    }
    
    /**
     * Recorre pixel por pixel la imagen para tomar el mensaje oculto. 
     * @param la ruta de la imagen.
     * @return el mensaje oculto en la imagen.
     */
    private String sacaMensaje(String fo){
	int l;
	Color color;
	String mensajeOriginal = "";
	BufferedImage image = sacaFoto(fo);
        l = longitudMensaje(image);
	String[] s = new String[l];
	String tmp="";
	for(int i = 0; i < image.getHeight(); i++)
	    for(int j = 0; j < image.getWidth(); j++){
		color = new Color(image.getRGB(j, i));
		String red = toBinary( (byte) color.getRed() );
		String verde = toBinary( (byte) color.getGreen() );
		String azul = toBinary( (byte) color.getBlue() );
		red = getLSB(red);
		verde = getLSB(verde);
		azul = getLSB(azul);
		if(tmp.length()<=(l*8))
		    tmp += red + verde + azul;
		else
		    break;
	    }
	int i = 0;
	for(int a = 0; a < (l*8); a += 8){
	    s[i]=tmp.substring(a, a + 8);                
	    i++;
	}
	mensajeOriginal = mensajeACadena(s);
	return mensajeOriginal;
    }

    /**
     * Toma un caracter(un numero en este caso) y lo transforma en su representacion
     * en binario
     * @param un byte que es el caractera a transformar.
     * @return la representacion en cadena binaria.
     */
    public String toBinary(byte caracter){
        byte byteDeCaracter = (byte)caracter;
        String binario="";
        for( int i = 7; i>=0; i--){
	    binario += ( ( ( byteDeCaracter & ( 1<<i ) ) > 0 ) ? "1" : "0" ) ;
        }
        return binario;
    }
    
    /**
     * Pasa una cadena de binario a decimal.
     * @param la cadena en binario.
     * @return el valor de la cadena en decimal.
     */
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

    /**
     * Pasa un numero en su representacion en cadena a su valor en ASCII.
     * @param el numero en cadena.
     * @return la equivalencia en la tabla ASCII.
     */
    private char decATxt(String cadena){
	int i = binarioADec(cadena);
	char c = (char)(i);
	return c;
    }

    /**
     * Toma un arreglo y saca el mensaje para mostrarlo.
     * @param arreglo con el mensaje en binario.
     * @return el mensaje en texto.
     */
    private String mensajeACadena(String[] mensaje){
        String mo ="";
        for(int i=4; i < mensaje.length; i++){
            mo += decATxt(mensaje[i]) ;
        }
        return mo;
    }

    /**
     * Regresa el bit menos significativo.
     * @param una cadena en binario.
     * @return el bit menos significativo de la cadena.
     */
    private String getLSB(String binario){
        return binario.substring(7, 8);
    }

}
