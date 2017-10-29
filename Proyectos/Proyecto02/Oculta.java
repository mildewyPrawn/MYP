import java.io.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Oculta{

    // Mensaje que se quiere ocultar.
    private String mensaje = "";
    // Mensaje binario a ocultar, con la longitud del mensaje.
    private String bin = "";
    // Longitud del mensaje.
    private int longi = 0;
    // Imagen con la que vamos a trabajar.
    private BufferedImage image;
    // Contador.
    private int contador;
    // Datos de la imagen.
    private int width, height;
    
    /**
     * Toma una cadena, en este caso es la ubicacion del archivo que queremos esconder
     * y pasa todo el archivo a una cadena.
     * @param ruta del archivo a esconder.
     * @return cadena con el mensaje a esconder.
     */
    public String muestraContenido(String archivo) throws FileNotFoundException, IOException{
	String cadena;
	//	String mensaje = "";
	FileReader fr = new FileReader(archivo);
	BufferedReader br = new BufferedReader(fr);
	while((cadena = br.readLine()) != null)
	    mensaje += " " + cadena;
	br.close();
	return mensaje;
    }

    /**
     * Prepara el mensaje para esconderlo.
     * @return el mensaje en 0's y 1's. 
     */
    public String preparaBinario(){
	String bi="";
        longi = mensaje.length();
       	bin = bi + mensajeABinario(mensaje);
	return bin;
	}

    /**
     * Dado una cadena s, la pasa a su equivalente en binario.
     * @param s, una cadena.
     * @return la misma cadena, solo que en binario.
     */
    public String mensajeABinario(String s){
	String mb = "";
        char[] mensaje_tmp = mensaje.toCharArray();
        for(int i = 0; i < mensaje_tmp.length;i++){
            mb = mb + toBinary( (byte) mensaje_tmp[i]);
        }
        return mb;
    }

    /**
     * Pasa un caracter a binario, el cual regresaremos en cadena a binario.
     * @param un caracter en numero.
     * @return su representacion en binario.
     */    
    private String toBinary(byte caracter){
        byte byteDeCaracter = (byte)caracter;
	String binario="";
        for( int i = 7; i>=0; i--){
	    binario = binario + ( ( ( byteDeCaracter & ( 1<<i ) ) > 0 ) ? "1" : "0" ) ;
        }
        return binario;
    }

    /**
     * Reemplaza el bit menos significatico con un bit del mensaje a esconder.
     * @param una cadena, es el la representacion de rojo, azul o verde en binario.
     * @return una cadena, a la cual le cambiamos el ultimo bit por uno del mensaje a ocultar.
     */
    private String  cambiaUltimo(String colorRGB){
        if(contador < bin.length()){
            colorRGB = colorRGB.substring(0,7) + bin.substring(contador, contador+1);
            contador++;    
        }
        return colorRGB;
    }
    
    /**
     * Oculta el mensaje <tt>bin</tt> en la foto.
     * @param el mensaje y la ruta de la foto deseada, el nombre de la nueva foto.
     * @return la nueva foto.
     */
    public void ocultaImagen(String bin, String fo, String nueFo){
	try{
	    int r,g,b;
	    File input = new File(fo);
	    image = ImageIO.read(input);
	    width = image.getWidth();
	    height = image.getHeight();
	    int count = 0;
	    Color color;
        for(int i = 0; i < image.getHeight();i++)
	    for(int j = 0;j < image.getWidth();j++){
                color = new Color( image.getRGB(j, i) );
                if(count <= this.bin.length()){
                    //se convierten a su equivalente en binario
                    String red = toBinary( (byte) color.getRed() );
                    String verde = toBinary( (byte) color.getGreen() );
                    String azul = toBinary( (byte) color.getBlue() );
                    //se reemplaza el ultimo bit
                    red = cambiaUltimo(red);
                    verde = cambiaUltimo(verde);
                    azul = cambiaUltimo(azul);
                    //binario a entero
                    r = Integer.parseInt(red ,2);
                    g = Integer.parseInt(verde ,2);
                    b = Integer.parseInt(azul ,2);
                }else{
		    r = color.getRed();
		    g = color.getGreen();
		    b = color.getBlue();
                }
                image.setRGB(j, i, new Color(r,g,b).getRGB());
		count+=3;
	    }
	File output = new File(nueFo);
	ImageIO.write(image, "png", output);
	}catch(Exception e){
	    System.out.println("El programa no pudo leer la imagen");
	    System.exit(1);
	}
    }    
}
