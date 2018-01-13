package mx.unam.ciencias.myp.proyecto3.test;

import mx.unam.ciencias.myp.proyecto3.Cifrar;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

/**
 * Clase para pruebas unitarias de la clase {@link Cifrar}.
 */
public class TestCifrar {
    
    /** Expiración para que ninguna prueba tarde más de 5 segundos. */
    @Rule public Timeout expiracion = Timeout.seconds(5);

    private Cifrar cifrar = new Cifrar();
    
    /**
     * Prueba unitaria para {@link Cifrar#getSHA256Hash}.
     * NOTA: las dispersiones se pueden corroborar con: 
     * 'echo -n "cadena" | openssl sha256' en Linux.
     */
    @Test public void testGetSHA256Hash() {
	String emi = cifrar.getSHA256Hash("emi");
	Assert.assertTrue(emi.equals("70a62c6116f2ebacf5abf7e23b07a711aebafdf518f7752f23322c6467c89ce9"));
	String janin = cifrar.getSHA256Hash("Janín");
	Assert.assertTrue(janin.equals("af88f839c6cf92f47193bef2cdf19affc8a378a5296f184923c7345dc30ca95b"));
	String modelado = cifrar.getSHA256Hash("Modelado");
	Assert.assertTrue(modelado.equals("afd6f5cd86db14d06e3ce321a5b964fceb01cbfdff1a09687205f0da07970265"));
    }

    /**
     * Prueba unitaria para {@link Cifrar#validaNums}.
     */
    @Test public void testValidaNums() {
	for(int i = 2; i < 100; i++){
	    int j = i++;
	    Assert.assertTrue(cifrar.validaNUms(i,j));
	}
    }

    /**
     * Prueba unitaria para {@link Cifrar#getInt}.
     */
    @Test public void testGetInt() {
	for(int i = 0; i < 100; i++){
	    String s = String.valueOf(i);
	    Assert.assertTrue(cifrar.getInt(s) == i);
	}	
    }
}

