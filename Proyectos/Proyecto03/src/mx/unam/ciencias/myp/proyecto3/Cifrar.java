package mx.unam.ciencias.myp.proyecto3;

import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.security.MessageDigest;
import java.io.FileNotFoundException;
import javax.xml.bind.DatatypeConverter;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import static java.lang.Character.digit;

public class Cifrar{
    
    public void cifrarMensaje(String archNT, String ene, String te, String arch, String pwd){
	int n = getInt(ene);
	int t = getInt(te);
	validaNums(n, t);
	validaNom(archNT, arch);
	String textocipher = "";
	try{
	    textocipher = lecturaArchivo(arch);
	}catch(IOException ioe){
	    System.out.println("Error en la lectura del archivo.");
	    System.exit(1);
	}
	String sha256 = getSHA256Hash(pwd);
	byte[] key = stringToBytes(sha256); 
	byte[] iv = "0000000000000000".getBytes();
	String encriptado = encriptar(key, iv, textocipher );//guardar esto en el otro argumento
	System.out.println(encriptado);
    }

    public void validaNom(String arch, String archi){
	if(!compara(arch, archi)){
	    System.out.println("Shamir: Nombres inválidos " + arch + " y " + archi +
			       " no tienen el mismo nombre");
	    System.exit(1);
	}
    }

    private boolean compara(String uno, String dos){
	return recorta(uno).equals(recorta(dos));
    }
    
    private String recorta(String arch){
	return arch;//////
    }
    
    public static byte[] stringToBytes(String clavehash){
    	int length = clavehash.length();
    	byte[] output = new byte[length / 2];
	for (int i = 0; i < length; i += 2) 
	    output[i / 2] = (byte) ((digit(clavehash.charAt(i), 16) << 4) | digit(clavehash.charAt(i+1), 16));
    	return output;
    }

    public static String encriptar(byte[] key, byte[] iv, String value){
	try{
	    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
	    SecretKeySpec sks = new SecretKeySpec(key, "AES");
	    cipher.init(Cipher.ENCRYPT_MODE, sks, new IvParameterSpec(iv));
	    
	    byte[] encriptado = cipher.doFinal(value.getBytes());
	    return DatatypeConverter.printBase64Binary(encriptado);
	}catch(Exception e){
	    e.printStackTrace();
	}
	return null;
    }

    public String getSHA256Hash(String data){
	String result = null;
	try{
	    MessageDigest digest = MessageDigest.getInstance("SHA-256");
	    byte[] hash = digest.digest(data.getBytes("UTF-8"));
	    return bytesToHex(hash);
	}catch(Exception e){
	    e.printStackTrace();
	}
	return result;
    }

    private String bytesToHex(byte[] hash){
	String s = DatatypeConverter.printHexBinary(hash);
	s = s.toLowerCase();
	return s;
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
     * Método igual a {@link #validaNums}, se hizo para pruebas unitarias.
     * @param dos enteros.
     * @return <code>false</code> si <code>n</code> es menor o igual a 2,
     *         <code>false</code> si <code>t</code> es mayor a n,
     *         <code>true</code> en otro caso.
     */
    public boolean validaNUms(int n, int t){
	if(n <= 2)
	    return false;
	if(n < t)
	    return false;
	return true;
    }
    
    public void validaNums(int n, int t){
	if(n <= 2){
	    System.out.println("Error en las evaluaciones intente: n > 2");
	    System.exit(1);
	}
	if(n < t){
	    System.out.println("Error en el mínimo de evaluaciones requeridas, intente: 1 < t ≤ n");
	    System.exit(1);
	}
    }

    public int getInt(String num){
	int i = 0;
	try{
	    i = Integer.parseInt(num);
	}catch(NumberFormatException nfe){
	    System.out.println("'" + num + "'" + " No es un número valido");
	    System.exit(1);
	}
	return i;
    }
}
