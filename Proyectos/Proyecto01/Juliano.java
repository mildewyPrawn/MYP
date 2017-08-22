import java.util.ArrayList;

public class Juliano{

    /**
     * Nos dice si el año introducido por el usuario es o no bisiesto dependiendo de 
     * si fue antes o despues de la reforma Gregoriana.
     * @param a, el año del que se quiere saber si es o no bisiesto.
     * @return <tt>true</tt> si el año es bisiesto antes o despues de la reforma.
     *         <tt>false</tt> sino lo es
     */
    public boolean esBis(int a){
	if (a <= 1583)
	    return(a % 4 == 0);
	return((a % 4 == 0) && (a % 100 != 0) || (a % 400 == 0));
    }
    /**
     * Toma una cadena, no importa si es en mayusculas o minusculas, las va a pasar
     * a minusculas y luego la va a recortar a los primeros tres caracteres para 
     * tener el mes en forma de numero. Si no es un mes valido, devuelve 13, que
     * usaremos para mandar error.
     * @param cadena de texto que es el mes.
     * @return numero del mes.
     */
    public int mesCalc(String mes){
	int i = 0;
	mes = mes.toLowerCase();
	mes = mes.substring(0,3);
	switch(mes){
	case("ene"):
	    i += 1;
	    break;
	case("feb"):
	    i += 2;
	    break;
	case("mar"):
	    i += 3;
	    break;
	case("abr"):
	    i += 4;
	    break;
	case("may"):
	    i += 5;
	    break;
	case("jun"):
	    i += 6;
	    break;
	case("jul"):
	    i += 7;
	    break;
	case("ago"):
	    i += 8;
	    break;
	case("sep"):
	    i += 9;
	    break;
	case("oct"):
	    i += 10;
	    break;
	case("nov"):
	    i += 11;
	    break;
	case("dic"):
	    i += 12;
	    break;
	default:
	    i += 0;
	    System.out.println("Tonalpohualli: no es un mes valido '" + mes + "'.");
	    System.out.println("Tonalpohualli: error en argumento del mes.");
	    System.out.println("Tonalpohualli: intente 'Tonalpohualli dd mmm aaaa'.");	    
	    System.exit(0);
	    break;
	}
	return i;
    }

    /**
     * Pasa la fecha dada por el usuario al Dia Juliano.
     * @param enteros d, m, y, que son el dia, mes y año respectivamente.
     * @return i, el numero Juliano de la fecha
     */
    public int juliano(int dia, int mes, int año){
	int a = (14 - mes)/12;
	int y = año + 4800 - a;
	int m = mes + (12*a) - 3;
	int jd = dia + (((153*m)+2)/5) + 365*y + (y/4) - 32083;
	return jd;
    }

    /**
     * Pasa la fecha dada por el usuario al Dia Juliano despues de la reforma gregoriana.
     * @param enteros d, m, y, que son el dia, mes y año respectivamente.
     * @return i, el numero Juliano despues de la reforma Gregoriana de la fecha
     */
    public int gregoriano(int dia, int mes, int año){
	int a = (14 - mes)/12;
	int y = año + 4800 - a;
	int m = mes + (12*a) -3;
	int gd = dia + (((153*m)+2)/5) + 365*y + (y/4) - (y/100) + (y/400) - 32045;
	return gd;
    }

    /**
     * Si la fecha es menos a la reforma Gregoriana manda a llamar a <tt>juliano</tt> 
     * para calcular la fecha Juliana. Si es despues de la reforma, manda a llamar a 
     * <tt>gregoriano</tt> para calcular el dia juliano aplicando la reforma.
     * @param enteros d, m, y que son la fecha deseada.
     * @return i, el numero juliano de la fecha con la reforma aplicada(Si es que se 
     *         se necesita).
     */
    public int ajustaFecha(int d, int m, int y){
	int c;
	if((d >= 5 && d <= 14) && (m == 10) && (y == 1583))
	    c = 0;
	else if(y == 1583){
	    if(m == 10){
		if(d < 5)
		    c = juliano(d,m,y);
		else
		    c = gregoriano(d,m,y);
	    }else if(m < 10)
		c = juliano(d,m,y);
	    else
		c =gregoriano(d,m,y);
	}else if(y < 1583)
	    c = juliano(d,m,y);
	else
	    c = gregoriano(d,m,y);
	return c;
    }

    /**
     * Saca la diferencia de la fecha que se le da con el 13 de Agosto de 1521 (Alfonos Caso).
     */
    public int sacaDif(int f){
	if(f == 0){
	    System.exit(0);
	    return 0;
	}
	int dif = f - 2276828;
	return dif;
    }
    
    /**
     * Saca el numero de año en el calendario Tonalpohualli.
     * @param el numero juliano(O gregoriano) de la fecha deseada para hacer la correspondencia.
     * @return el numero de año con su respectivo simbolo
     */
    public String yearTonal(int i){
	ArrayList<String> simYe = new ArrayList<String>();
	simYe.add("-Pedernal)");
	simYe.add("-Casa)");
	simYe.add("-Conejo)");
	simYe.add("-Caña)");

	ArrayList<String> simAn = new ArrayList<String>();
	simAn.add("Tecpatl");
	simAn.add("Calli");
	simAn.add("Tocht");
	simAn.add("Acatl");

	int ac = (i - 2276828)/260;
	int f, d;
	if(ac < 0){
	    f = 5 + (ac % 4)*-1;
	    if(f < 0)
		f = 5-(ac*-1);
	    d = (ac % 13)+14 *-1;
	}else{
	    f = (ac + 5) % 4;
	    d = (ac % 13) + 3;
	}
	return "Del año: " + d + "-" + simAn.get(f) + "("+ d + simYe.get(f);
    }
    
    /**
     * Da los numeros con modulos para obtener los meses y los dias.
     * @param la diferencia de {@link sacaDif}.
     * @return cadena con el año y figura en el calendario Tonalpohualli.
     */
    public String tonalpohualli(int f){
	ArrayList<String> simNa = new ArrayList<String>();
	simNa.add("Lagarto)");
	simNa.add("Viento)");
	simNa.add("Casa)");
	simNa.add("Lagartija)");
	simNa.add("Serpiente)");
	simNa.add("Muerte)");
	simNa.add("Venado)");
	simNa.add("Conejo)");
	simNa.add("Agua)");
	simNa.add("Perro)");
	simNa.add("Mono)");
	simNa.add("Hierba)");
	simNa.add("Caña)");
	simNa.add("Jaguar)");
	simNa.add("Aguila)");
	simNa.add("Buitre)");
	simNa.add("Movimiento)");
	simNa.add("Pedernal)");
	simNa.add("luvia)");
	simNa.add("Flor)");
	
	ArrayList<String> simTon = new ArrayList<String>();
	simTon.add("Cipactli");
	simTon.add("Ehecatl");
	simTon.add("Calli");
	simTon.add("Cuetzpalin");
	simTon.add("Coatl");
	simTon.add("Miquiztli");
	simTon.add("Mazatl");
	simTon.add("Tochtli");
	simTon.add("Atl");
	simTon.add("Itzcuintli");
	simTon.add("Ozomatli");
	simTon.add("Malianalli");
	simTon.add("Acatl");
	simTon.add("Ocelotl");
	simTon.add("Cuauhtli");
	simTon.add("Cozcaquauhtli");
	simTon.add("Ollin");
	simTon.add("Tecpatl");
	simTon.add("Quiahuitl");
	simTon.add("Xochitl");
	int m,d;
	if(f < 0){
	    m = 4 + (f % 20);
	    if(m < 0)
		m = 20 - (m * -1);
	    d =(f % 13) + 14;
	}else{
	    m = (f + 4) % 20;
	    d = (f % 13) + 3;
	}
	return "Dia:     " + d + "-"+ simTon.get(m) + "("+ d + "-" + simNa.get(m);
    }    
}
