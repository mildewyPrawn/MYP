package mx.unam.ciencias.myp.proyecto3.test;

import mx.unam.ciencias.myp.proyecto3.Develar;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

/**
 * Clase para pruebas unitarias de la clase {@link Cifrar}.
 */
public class TestDevelar {
    
    /** Expiración para que ninguna prueba tarde más de 5 segundos. */
    @Rule public Timeout expiracion = Timeout.seconds(5);

    private Develar cifrar = new Develar();
        
    /**
     * Prueba unitaria para {@link Develar#voltea}.
     */
    @Test public void testVoltea() {
	String s = "anitalavalatina";
	Assert.assertTrue(cifrar.voltea(s).equals(s));
    }

    /**
     * Prueba unitaria para {@link Develar#compara}.
     */
    @Test public void testCompara() {
	Assert.assertTrue(cifrar.compara("archivo1.frg","archivo1.txt"));
    }
}
