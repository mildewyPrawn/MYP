public class Esteganografia{    

    public static void main(String [] args){
	Oculta oc = new Oculta();
	Devela dev = new Devela();
	/**Si nos pasan una cantidad erronea de argumentos*/
	if(args.length < 3 || args.length > 4){
	    System.out.println("Esteganografia: Error en argumentos.");
	    System.out.println("Esteganografia: Para OCULTAR un archivo intente");
	    System.out.println("'Esteganografia h archivoAOcultar.txt imagen.png nuevaImagen.png'");
	    System.out.println("Esteganografia: Para DEVELAR un archivo intente");
	    System.out.println("'Esteganografia u imagenConArchivosOcultos.png nuevaImagen.png'");
	    System.exit(1);
	}/**Si nos pasan la bandera 'h'*/
	if(args[0].equals("h")){
	    /**Si nos pasan la bandera 'h' pero con los argumentos erroneos*/
	    if(args.length == 3){
		System.out.println("Esteganografia: Error en argumentos.");
		System.out.println("Esteganografia: Para OCULTAR un archivo intente");
		System.out.println("'Esteganografia h archivoAOcultar.txt imagen.png nuevaImagen.png'");
		System.exit(1);
	    }
	    //OCULTA
	    oc.ocultarMensaje(args[1], args[2], args[3]);
	    /**Si nos pasan la bandera 'u'*/
	}else if(args[0].equals("u")){
	    /**Si nos pasan la bandera 'u' pero con los argumentos erroneos*/
	    if(args.length == 4){
		System.out.println("Esteganografia: Error en argumentos.");
		System.out.println("Esteganografia: Para DEVELAR un archivo intente");
		System.out.println("'Esteganografia u imagenConArchivosOcultos.png nuevaImagen.png'");
		System.exit(1);
	    }
	    //DEVELA
	    dev.develarMensaje(args[1], args[2]);
	    /**Si no nos pasan alguna de las dos banderas*/
	}else{
	    System.out.println("Esta bandera '" + args[0] + "' no es una opcion.");
	    System.exit(1);
	}
    }
}
