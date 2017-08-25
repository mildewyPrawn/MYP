import java.math.BigInteger;

public class Calcula{
    
    /**
     * Metodo que saca el factorial de un numero, como recibimos numeros muy 
     * grandes usamos BigInteger.
     * @param n, el numero del que se quiere sacar el factorial.
     * @return el factorial de n, que puede ser un numero muy grande.
     */
    public BigInteger factorial(int n){
	BigInteger f = new BigInteger("1");
	for(int i = 1; i <= n; i++)
	    f = f.multiply(new BigInteger(i + ""));
	return f;
    }
    
    /**
     * Metodo que reune todas las operaciones de la formula para sacar las
     * combinaciones.
     * @param dos enteros que son n, r, para sacar la diferencia y tener (n-r)!
     * @return el resultado de aplicar las operaciones correspondientes a los 
     *         enteros n y r.
     */
    public BigInteger formula(int n, int r){
	int nr = n-r;
	BigInteger ene = factorial(n);
	BigInteger mult = multiplica(factorial(nr),factorial(r));
	BigInteger res = divide(ene, mult);
	return ene;
    }

    /**
     * Metodo que multiplica dos numeros<code>BigInteger</code>.
     * @param nr, r <code>BigInteger</code>.
     * @return <code>BigInteger</code> restultado de la multiplicacion.
     */
    public BigInteger multiplica(BigInteger nr, BigInteger r){
	BigInteger result = nr.multiply(r);
	return result;
    }

    /** 
     * Metodo que divide dos numeros <code>BigInteger</code>.
     * @param n, nrr <code>BigInteger</code> que son el numerador y el
     *        denominador, respectivamente.
     * @return <code>BigInteger</code> el cociente de la division.
     */
    public BigInteger divide(BigInteger n, BigInteger nrr){
	BigInteger res = n.divide(nrr);
	System.out.println(res);
	return res;
    }
}
