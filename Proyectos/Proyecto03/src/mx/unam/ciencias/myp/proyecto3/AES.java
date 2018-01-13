//import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import static java.lang.Character.digit;

public class AES{
    
    public static byte[] stringToBytes(String clavehash){
    	int length = clavehash.length();
    	byte[] output = new byte[length / 2];
	
    		for (int i = 0; i < length; i += 2) {
        		output[i / 2] = (byte) ((digit(clavehash.charAt(i), 16) << 4) | digit(clavehash.charAt(i+1), 16));
    		}
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
    /*
    public static String decriptar(String clave, byte[] iv, String encriptado){
	try{
	    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
	    SecretKeySpec sks = new SecretKeySpec(clave.getBytes("UTF-8"), "AES");
	    cipher.init(Cipher.DECRYPT_MODE, sks, new IvParameterSpec(iv));

	    byte[] dec = cipher.doFinal(DatatypeConverter.parseBase64Binary(encriptado));
	    return new String(dec);
	}catch(Exception e){
	    e.printStackTrace();
	}
	return null;
    } 
    */

	public static void main(String[] args){
	String clave = "89a0d30ea1b4cb9aff84758a418e835f06993f9f2446851ef331d30c27173828"; 
	byte[] key = stringToBytes(clave); 
	byte[] iv = "0000000000000000".getBytes();
	String encriptado = encriptar(key, iv, "Príncipe de Marinela");
	System.out.println(String.format("encriptado: %s", encriptado));
	//System.out.println(decriptar(key, iv, encriptado));


    }
}
