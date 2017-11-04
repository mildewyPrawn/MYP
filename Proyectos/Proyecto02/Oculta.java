import java.io.File;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Oculta {
    
    private int contador = 0;
    
    /**
     * Lee un archivo 
     * @param la ruta de donde se encuentra el archivo.
     * @return el contenido en cadena del mensaje en el archivo.
     */
    private String lecturaArchivo(String archivo) throws FileNotFoundException, IOException{
	String mensaje = "";
	FileReader fr = null;
	BufferedReader br = null;
	try{
	    fr = new FileReader(archivo);
	    br = new BufferedReader(fr);
	    String cadena;
	    while((cadena = br.readLine()) != null)
		mensaje += cadena + " ";
	}catch(IOException ioe){
	    System.out.println("Error en la lectura del archivo");
	    System.exit(1);
	}
	return mensaje;
    }
    
    /**
     * Prepara el mensaje para poder esconderlo en bits y con la longitud de este.
     * @param el mensaje que se quiere ocultar.
     * @return el mensaje en binario con la longitud de este.
     */
    private String preparaMensaje(String mensaje){
	String binario;
	int longitud = 0;
        String bi="";
        longitud = mensaje.length() + 4;
        for( int i = 15; i>=0; i--){
	    bi += ( ( ( longitud & ( 1<<i ) ) > 0 ) ? "1" : "0" ) ;
        }
	return binario = cadenaABinario("  ") + bi + cadenaABinario(mensaje);
    }

    /**
     * Busca la foto que se desea utilizar.
     * @param la ruta de la foto que se va a utilizar.
     * @return la imagen que se obtiene de la ruta.
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
     * Oculta el mensaje haciendo varias llamadas a metodos. Lo oculta en los pixeles,
     * en las componentes R,G,B.
     * @param las cadenas del mensaje, y la foto, asi como el nombre con el que se va 
     *        a guardar la nueva foto.
     */
    public void ocultarMensaje(String msje, String fo, String nueFo){
	String mensaje, binario;
	Color color;
	int r,g,b;
	try{
	    mensaje = lecturaArchivo(msje);
	    binario = preparaMensaje(mensaje);
	    BufferedImage image = sacaFoto(fo);
	    int k = 0;
	    for(int i = 0; i < image.getHeight(); i++)
		for(int j = 0; j < image.getWidth(); j++){
		    color = new Color(image.getRGB(j, i));
		    if(k <= binario.length()){
			String red = toBinary((byte) color.getRed());
			String green = toBinary((byte) color.getGreen());
			String blue = toBinary((byte) color.getBlue());
			red = reemplazarLSB(red, binario);
			green = reemplazarLSB(green, binario);
			blue = reemplazarLSB(blue, binario);
			r = Integer.parseInt(red ,2);
			g = Integer.parseInt(green ,2);
			b = Integer.parseInt(blue ,2);
		    }else{
			r = color.getRed();
			g = color.getGreen();
			b = color.getBlue();
		    }
		    image.setRGB(j, i, new Color(r,g,b).getRGB());
		    k+=3;
		}
	    File output = new File(nueFo);
	    ImageIO.write(image, "png", output);
	}catch(IOException ioe){
	    System.out.println("Hubo un error en la escritura de la imagen");
	    System.exit(1);
	}
    }
    
    /**
     * Toma un caracter(un numero en este caso) y lo transforma en su representacion
     * en binario
     * @param un byte que es el caractera a transformar.
     * @return la representacion en cadena binaria.
     */
    private String toBinary(byte caracter){
        byte byteDeCaracter = (byte)caracter;
        String binario="";
        for( int i = 7; i>=0; i--){
	    binario += ( ( ( byteDeCaracter & ( 1<<i ) ) > 0 ) ? "1" : "0" ) ;
        }
        return binario;
    }

    /**
     * Toma una cadena y la pasa a su representante en binario.
     * @param la cadena que se quiere cambiar.
     * @return la representacion en binario de la cadena.
     */
    private String cadenaABinario(String cadena){
	String cb = "";
	int longitud;
	for(int i = 0; i < cadena.length(); i++){
	    cb += String.format("%8s",Integer.toBinaryString(cadena.charAt(i)));
	}
	cb = formato(cb);
	return cb;
    }
    
    /**
     * Toma una cadena con espacios y la regresa completa, rellena de ceros 
     * @param una cadena en "binario".
     * @return la misma cadena pero rellena de ceros.
     */
    private String formato(String cadena){
	cadena = cadena.replaceAll(" ","0");
	return cadena;
    }
    
    /**
     * Reemplaza el bit menos significativo con un bit del mensaje.
     * @param la cadena en binario del color que se quiere modificar, el mensaje
     *        a ocultar en binario.
     * @return el color modificado con el ultimo bit transformado en un bit del mensaje.
     */
    private String  reemplazarLSB(String colorRGB, String binario){
        if(contador < binario.length()){
            colorRGB = colorRGB.substring(0,7) + binario.substring(contador, contador+1);
            contador++;    
        }
        return colorRGB;
    }

}
