package mx.unam.ciencias.myp.proyecto3;

public class Shamir{

    public static void main(String args[]){
	if(args.length > 5 || args.length < 3 || args.length == 4){
	    System.out.println("Shamir: Error en argumentos.");
	    System.out.println("Shamir: para cifrar un archivo intente:");
	    System.out.println("'Shamir c nombreArchivo numEvaluaciones numMinimo nomArchDoc'");
	    System.out.println("Shamir: para develar un archivo intente:");
	    System.out.println("'Shamir d nomArch nomArchCifrado'");
	    System.exit(1);
	}
	if(args[0].equals("c")){
	    if(args.length != 5){
		System.out.println("Shamir: Error en argumentos.");
		System.out.println("Shamir: para cifrar un archivo intente:");
		System.out.println("'Shamir c nombreEvaluaciones numEvaluaciones numMinimo nomArchivo'");
		System.exit(1);
	    }
	    /* CIfra el mensaje */
	    Cifrar c = new Cifrar();
	    String pwd = new String(System.console().readPassword());
	    c.cifrarMensaje(args[1], args[2], args[3], args[4], pwd);
	}else if(args[0].equals("d")){
	    if(args.length != 3){
		System.out.println("Shamir: Error en argumentos.");
		System.out.println("Shamir: para develar un archivo intente:");
		System.out.println("'Shamir d nomArch nomArchCifrado'");
		System.exit(1);
	    }
	    /* Devela el mensaje */
	    Develar d = new Develar();
	    d.develaMensaje(args[1], args[2]);
	}else{
	    System.out.println("Esta bandera '" + args[0] + "' no es una opcion.");
	    System.exit(1);	    
	}
        
    }
}
