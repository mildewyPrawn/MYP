/********************************************
 * Proyecto01.
 * Tonalpohualli.java
 * Universidad Nacional Autonoma de Mexico
 * Facultad de Ciencias 
 * @author EmilianoGaleana 31403232-4
 * Correlacion de Calendarios.
 * @mail galeanaara@ciencias.unam.mx
 * ******************************************
 */

public class Tonalpohualli{

    public static void main(String args[]){

	/*
	 * Si nos nos pasan parametros terminamos.
	 * Si nos pasan un parametro terminamos.
	 * Si nos pasan dos parametros terminamos.
	 */
	if(args.length <= 2){
	    System.out.println("Tonalpohualli: faltan parametros.");
	    System.out.println("Tonalpohualli: intente'Tonalpohualli dd mmm aaaa'.");
	    System.exit(0);
	}

	/*
	 * Si nos pasan mas de tres parametros, terminamos.
	 */

	if(args.length > 3){
	    System.out.println("Tonalpohualli: sobran parametros.");
	    System.exit(0);
	}

	/*
	 * Si nos pasan algo distinto a dd ******* aaaa terminamos.
	 * Nota: Pueden pasar un mes como ago, agost, AgOSto, etc.
	 */

	if(args[0].length() > 2 || args[1].length() < 3 || args[2].length() != 4){
	    System.out.println("Tonalpohualli: error en argumentos.");
	    System.out.println("Tonalpohualli: intente 'Tonalpohualli dd mmm aaaa'.");
	    System.exit(0);
	}

	/*
	 * Recibimos los parametros y cambiamos a numeros el dia y año(args[0] y args[2]
	 * respectivamente).
	 */
	try{
	    int day = Integer.parseInt(args[0]);
	    String mes = args[1];
	    int year = Integer.parseInt(args[2]);

	    /*
	     * Si el dia es mayor a 31 terminamos el programa.
	     */
	    if(day > 31 || day < 01){
          System.out.println("Tonalpohualli: error en argumento del dia.");
          System.out.println("Tonalpohualli: intente 'Tonalpohualli dd mmm aaaa'.");
          System.exit(0);
	    }


	    Juliano mesesito = new Juliano();
	    int month = mesesito.mesCalc(mes);

	    /*
	     * Si el dia de febrero es mayor a 30 (y 29 en algunos casos) terminamos.
	     */
	    Juliano bis = new Juliano();
	    if(!bis.esBis(year)){
          if(month == 2)
              if(day > 28){
                  System.out.println("Tonalpohualli: error en argumento del dia.");
                  System.out.println("Tonalpohualli: intente 'Tonalpohualli dd mmm aaaa'.");
                  System.exit(0);
              }
	    }if(bis.esBis(year)){
          if(month == 2)
              if(day > 29){
                  System.out.println("Tonalpohualli: error en argumento del dia.");
                  System.out.println("Tonalpohualli: intente 'Tonalpohualli dd mmm aaaa'.");
                  System.exit(0);
              }
	    }

	    /*
	     * Si introducen 31 de algun mes que solo tenga 30 dias terminamos.
	     */
	    if(month == 4 || month == 6 || month == 9 || month == 11)
          if(day > 30){
              System.out.println("Tonalpohualli: error en argumento del dia.");
              System.out.println("Tonalpohualli: intente 'Tonalpohualli dd mmm aaaa'.");
              System.exit(0);
          }

	    /*
	     * Nos dice si es una fecha valida que no esta entre el 4 y el 15 de octubre de 1582
	     */
	    if((day >= 5 && day <= 14)&&(month == 10)&&(year == 1583)){
          System.out.println("Tonalpohualli: No es una fecha valida.");
          System.out.println("Tonalpohualli: No existe la fecha en el calendario Gregoriano");
	    }

	    Juliano jul = new Juliano();
	    int numPaTo = jul.ajustaFecha(day, month, year);
	    Juliano tona = new Juliano();
	    int dif = tona.sacaDif(numPaTo);
	    Juliano num = new Juliano();
	    Juliano fecha = new Juliano();
	    String cadena = num.tonalpohualli(dif);
	    String año = jul.yearTonal(numPaTo);
	    System.out.println("Alfonso Caso:");
	    System.out.println(cadena);
	    System.out.println(año);
	}catch(NumberFormatException nfe){
	    System.out.println("Tonalpohualli: error en le formato de fecha.");
	    System.out.println("Tonalpohualli: intente con dd mmm aaaa (numeros, letras, numeros)");
	    System.exit(0);
	}
    }
}
