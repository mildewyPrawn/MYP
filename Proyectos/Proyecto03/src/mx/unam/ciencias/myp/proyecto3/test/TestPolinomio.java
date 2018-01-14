package mx.unam.ciencias.myp.proyecto3.test;

import java.math.BigInteger;
import mx.unam.ciencias.myp.proyecto3.Polinomio;
import mx.unam.ciencias.myp.proyecto3.Cifrar;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import java.util.Random;

/**
 * Clase para pruebas unitarias de la clase {@link Polinomio}.
 */
public class TestPolinomio {
    
    /** Expiración para que ninguna prueba tarde más de 5 segundos. */
    @Rule public Timeout expiracion = Timeout.seconds(5);

    private Polinomio polinomio;
    
    /**
     * Prueba unitaria para {@link Polinomio#hexToDec}.
     */
    @Test public void testHexToDec() {
	polinomio = new Polinomio();
	Assert.assertTrue(polinomio.hexToDec("16").toString().equals("22"));
	Assert.assertTrue(polinomio.hexToDec("1a2b3c").toString().equals("1715004"));
    }

    /**
     * Prueba unitaria para {@link Polinomio#evaluar}.
     */
    @Test public void testEvaluar(){
	polinomio = new Polinomio();
	BigInteger[] bi= new BigInteger[]{new BigInteger("3"),
					  new BigInteger("14"),
					  new BigInteger("15")};
	BigInteger mod = new BigInteger("17");
	for(int i = 0; i <= 5; i++){
	    BigInteger r = polinomio.evaluar(i,bi);
	    
	    if(i == 0)
		Assert.assertTrue(r.toString().equals("3"));
	    if(i == 1){
		Assert.assertTrue(r.toString().equals("32"));
		Assert.assertTrue(r.mod(mod).toString().equals("15"));
	    }
	    if(i == 2){
		Assert.assertTrue(r.toString().equals("91"));
		Assert.assertTrue(r.mod(mod).toString().equals("6"));
	    }
	    if(i == 3){
		Assert.assertTrue(r.toString().equals("180"));
		Assert.assertTrue(r.mod(mod).toString().equals("10"));
	    }
	    if(i == 4){
		Assert.assertTrue(r.toString().equals("299"));
		Assert.assertTrue(r.mod(mod).toString().equals("10"));
	    }
	    if(i == 5){
		Assert.assertTrue(r.toString().equals("448"));
		Assert.assertTrue(r.mod(mod).toString().equals("6"));
	    }
	}
    }

    /**
     * Prueba unitaria para {@link Polinomio#clave}.
     */
    @Test public void testClave(){
	String clave = "(1, 15)\n(2, 6)\n(3, 10)\n(4, 10)\n(5, 6)\n";
	polinomio = new Polinomio();
	BigInteger[] bi= new BigInteger[]{new BigInteger("3"),
					  new BigInteger("14"),
					  new BigInteger("15")};
	String poliClave = polinomio.clave(5, bi, new BigInteger("17"));
	Assert.assertTrue(poliClave.equals(clave));
    }

    /**
     * Prueba unitaria para {@link Polinomio#decToHex}.
     */
    @Test public void testDecToHex(){
	polinomio = new Polinomio();
	Assert.assertTrue(polinomio.decToHex(new BigInteger("15")).equals("f"));
	Assert.assertTrue(polinomio.decToHex(new BigInteger("303056")).equals("49fd0"));
	Assert.assertTrue(polinomio.decToHex(new BigInteger("1783373")).equals("1b364d"));
	Assert.assertTrue(polinomio.decToHex(new BigInteger("1234567890")).equals("499602d2"));
	for(int i = 0; i < 5; i++){
	    BigInteger random = new BigInteger(256, new Random());
	    String hex = polinomio.decToHex(random);
	    BigInteger temp = polinomio.hexToDec(hex);
	    Assert.assertTrue(random.equals(temp));
	}
    }
}
