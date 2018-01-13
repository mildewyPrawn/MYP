//import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import static java.lang.Character.digit;

public class AESdecifra{

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
	    e.printStackTrace();
	}
	return null;
    }

	public static void main(String[] args){
	String clave = "89a0d30ea1b4cb9aff84758a418e835f06993f9f2446851ef331d30c27173828";
	byte[] iv = "0000000000000000".getBytes();
	byte[] key = stringToBytes(clave); 
	String encriptado = "dXCgfJLzmrKH3rxNjXXXHk4gKgPezbapzHmXrbXBKnn+ZrTck4mhZ4E/41/q8rwsbIRYcnotTo17R12ej1MKXueMcJXDjUJVePJwdIeKF+4NXHfxJgo59CeBsCa2Q0Dl/gp0MpdFCVeXG1a9TSrobts/Bow5y74bdiMoGMwH+fEiK+Xtw9mXwCP6LwIWFPY/zHchJwteJYDfsLh1pgCeN9bBzA6SUMi20M2BRS4OvWNJHJ+kM9Cu7S6IfNTf+Lu1tVNumAo+SsK3yd3RdNNIcRiGFMfDeRaBacOBDCJoi9rVYK4Z9o7AjW/0bm5yV1WbsEdJ+BNjeNlZ4XGDnWkRKWd5uvWPE+YH0zQRkZH+cVMYXrlrj0aA4wh8yliFdHDSBH+VI+Knek9cSJbfbqcJRNjoCeSd1PSFNfi2/ygF8C3RQjaYmWbSyLNOx8dPrGNZ8gUnzl4Gk/EqdzM5mgjzyeVdCPHM5D3od5EuAhzjVx7viRrZcsMMYNkn05OomTlVXYiBJ3Fdrt0BQpICaPY9XsEJ43Fjarzsl9Th3RmFJkT2spNC0UKzsG4yCnfz736baWi4unYstqkMP8ZjZf76orgPAH7qZVY8X35Gjt19/JBkcgWmGwdI+wTDPub5oaZwhEFgIJ5B28I88FNmYBoiVR6m0bAfIz2JwYm2eBM5me0SrvfJ1GRDgFVoSQEIkCEvFuqTBovPpsBkC2bi2PQEjos1dgHwA39WUk7Q5X8bJ6391nHGiq8V24A34enAtsWOIEprRHLmo6gS0tlZZWydMl628N8074BFooNUzIwjQLtDvyFBCyC6JbiU6Ha5x4nWN95iV18e6299T0h/r0hoD/lVkkuts/u5LK139AnDcPnwxuAal7lskO3LqeqdH/RLaAmdzlsK7uU7IfH+g/ReRs3W2MO6ODLnEiKMVYQxfehgbrR6uehZM/cmS13+j/c6Q76C1qwm3JCfyQqg7orJ0pnQqrAq/KhXNwnd4/X/AtD77Wejw1oyQO4kuBopTpjAbA6gkqV228AxQfxFKUfvG4F7a2pK6V2DxmeGsfMyWow7D55f4Dm9eO4vJ+cZRkQ7LBcy+2gS1KUZ6NzTZ3WnpoumHyuB1qCwcdNixrWED3YEPhVjnDUao3mozI6PEp1XbCbfr1u1KXdHcUij5SKkKHBUd7revqWEVyqHZ955CyydSQclnURVhu1Rmf+eNKCp8d3u5Q9VF0l3r3/MB2JIKZFu+GrSx6YLVxKsoWr7DjeGntPkk+6iDWX2qhalXTXxKFnP0yhOUgK7x90KNI5ZZnvFqpLFe/rxCY6+Dtr4J8NVNIoIZQy5x/u5v3bG1qpRQ6meahFKde1LDTM9l53UeQ==";
	System.out.println(decriptar(key, iv, encriptado));
    }
    
}
