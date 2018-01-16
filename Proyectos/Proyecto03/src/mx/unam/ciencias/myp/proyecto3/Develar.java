package mx.unam.ciencias.myp.proyecto3;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import javax.crypto.Cipher;
import java.io.IOException;
import java.math.BigInteger;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import static java.lang.Character.digit;
import javax.crypto.BadPaddingException;
import javax.crypto.spec.IvParameterSpec;

public class Develar{
    
    public void develaMensaje(String frg, String aes){
	validaNom(frg, aes);
	valida(frg, ".frg");
	valida(aes, ".aes");
	String cipher = "";
	String claves = ""; 
	try{
	    cipher = lecturaArchivo(aes);
	    claves = lecturaArchivo(frg);
	}catch(IOException ioe){
	    System.out.println("Error en la lectura del archivo.");
	    System.exit(1);
	}
	Lagrange l = new Lagrange();
	String hex = l.getEval(claves);
	byte[] key = stringToBytes(hex); 
	byte[] iv = "0000000000000000".getBytes();
	escribirArchivo(decriptar(key, iv, cipher),  recorta(aes) + ".txt");
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

    public static byte[] stringToBytes(String clavehash){
	int length = clavehash.length();
	byte[] output = new byte[length / 2];
	
    	for (int i = 0; i < length; i += 2) {
	    output[i / 2] = (byte) ((digit(clavehash.charAt(i), 16) << 4) | digit(clavehash.charAt(i+1), 16));
    	}
    	return output;
    }

    public static String decriptar(byte[] key, byte[] iv, String encriptado){
	try{
	    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
	    SecretKeySpec sks = new SecretKeySpec(key, "AES");
	    cipher.init(Cipher.DECRYPT_MODE, sks, new IvParameterSpec(iv));

	    byte[] dec = cipher.doFinal(DatatypeConverter.parseBase64Binary(encriptado));
	    return new String(dec);
	}catch(Exception e){
	    System.out.println("Clave incorrecta");
	    System.exit(1);
	}
	return null;
    }

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
     * Método que verifica que el primer argumento del usuario sea un
     * nombre válido.
     * @param <code>argumento</code> el primer argumento despupes de la 
     *        bandera.
     * @param <code>form</code> formato que se desea obtener.
     */
    public void valida(String argumento, String form){
	String valido = form;
	int index = argumento.indexOf(valido);
	if(index < 0){
	    System.out.println("Nombre no válido, " + argumento +
			       " tiene que terminar con '" + valido + "'." );
	    System.exit(1);
	}
    }


    /**
     * Método que verifica los nombres de los archivos introducidos por el 
     * usuario (sin formato, es decir, solo el nombre).
     * @param <code>arch</code> primer archivo.
     * @param <code>archi</code> segundo archivo.
     * Si no son iguales, el programa termina.
     */
    public void validaNom(String arch, String archi){
	if(!compara(arch, archi)){
	    System.out.println("Shamir: Nombres inválidos " + arch + " y " + archi +
			       " no tienen el mismo nombre");
	    System.exit(1);
	}
    }
    
    /**
     * Método que compara dos cadenas.
     * @param <code>uno</code> cadena.
     * @param <code>dos</code> otra cadena.
     * @return <code>false</code> si <code>uno</code> y <code>dos</code> son
     *         cadenas distintas. <code>true</code> en otro caso.
     */
    public boolean compara(String uno, String dos){
	return recorta(uno).equals(recorta(dos));
    }

    /**
     * Método que regresa el nombre de un archivo sin formato.
     * @param <code>arch</code> cadena con caracteres especiales.
     * @return cadena con el nombre de un archivo.
     */
    private String recorta(String arch){
	String v = voltea(arch);
	int i = v.indexOf(".");
	int s = v.indexOf("/");
	if(s < 0){
	    String ve = v.substring(i + 1, v.length());
	    ve = voltea(ve);
	    return ve;
	}else{
	    String ve = v.substring(i + 1,s);
	    ve = voltea(ve);
	    return ve;
	}
    }

    /**
     * Método que voltea una cadena.
     * @param <code>cadena</code> que se quiere voltear.
     * @return <code>cadena</code> al revés.
     */
    public String voltea(String cadena){
	String invertida = "";
	for(int i = cadena.length() - 1; i >= 0; i--)
	    invertida += cadena.charAt(i);
	return invertida;
    }


}
