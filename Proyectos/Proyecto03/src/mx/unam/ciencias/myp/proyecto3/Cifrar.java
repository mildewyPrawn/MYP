package mx.unam.ciencias.myp.proyecto3;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import javax.crypto.Cipher;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.security.MessageDigest;
import java.io.FileNotFoundException;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import static java.lang.Character.digit;
import javax.crypto.spec.IvParameterSpec;

public class Cifrar{

    /**
     * Método principal de la clase, controla todo lo que ocurre.
     * @param <code>archNT</code> nombre en el que se van a guardar las claves.
     * @param <code>ene</code> numero de claves.
     * @param <code>te</code> mínimo número de claves.
     * @param <code>arch</code> ruta de texto a cifrar.
     * @param <code>pwd</code> contraseña introducida por el usuario.
     */
    public void cifrarMensaje(String archNT, String ene, String te, String arch, String pwd){
        int n = getInt(ene);
        int t = getInt(te);
        validaNums(n, t);//n > t
        validaArg1(archNT);//.frg
        validaNom(archNT, arch);//mismo nombre sin formato
        String textocipher = "";
        try{
            textocipher = lecturaArchivo(arch);
        }catch(IOException ioe){
            System.out.println("Error en la lectura del archivo.");
            System.exit(1);
        }
        String sha256 = getSHA256Hash(pwd);
        byte[] key = stringToBytes(sha256); 
        byte[] iv = "0000000000000000".getBytes();
        String encriptado = encriptar(key, iv, textocipher );//guardar esto en el otro argumento
        escribirArchivo(encriptado, recorta(arch) + ".aes");
        Polinomio p = new Polinomio(sha256, recorta(arch) + ".frg", n ,t);
        p.calcula();
    }

    /**
     * Metodo que escribe un archivo con el mensaje que recibe.
     * @param el mensaje que vamos a escribir y la ruta del mensaje.
     */
    private void escribirArchivo(String msje, String arch){
        File f = new File(arch);
        try{
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.write(msje);
            pw.close();
            bw.close();
        }catch(IOException ioe){
            System.out.println("Hubo un error al crear el archivo " + arch);
            System.exit(1);
        }
    }
    
    /**
     * Método que verifica que el primer argumento del usuario sea un
     * nombre válido.
     * @param <code>argumento</code> el primer argumento despupes de la 
     *        bandera.
     */
    public void validaArg1(String argumento){
        String valido = ".frg";
        int index = argumento.indexOf(valido);
        if(index < 0){
            System.out.println("Nombre no válido, " + argumento +
                               " tiene que terminar con '" + valido + "'." );
            System.exit(1);
        }
    }

    /**
     * Método que verifica los nombres de los archivos introducidos por el 
     * usuario (sin formato, es decir, solo el nombre).
     * @param <code>arch</code> primer archivo.
     * @param <code>archi</code> segundo archivo.
     * Si no son iguales, el programa termina.
     */
    public void validaNom(String arch, String archi){
        if(!compara(arch, archi)){
            System.out.println("Shamir: Nombres inválidos " + arch + " y " + archi +
                               " no tienen el mismo nombre (SIN FORMATO)");
            System.exit(1);
        }
    }

    /**
     * Método que compara dos cadenas.
     * @param <code>uno</code> cadena.
     * @param <code>dos</code> otra cadena.
     * @return <code>false</code> si <code>uno</code> y <code>dos</code> son
     *         cadenas distintas. <code>true</code> en otro caso.
     */
    public boolean compara(String uno, String dos){
        return recorta(uno).equals(recorta(dos));
    }

    /**
     * Método que voltea una cadena.
     * @param <code>cadena</code> que se quiere voltear.
     * @return <code>cadena</code> al revés.
     */
    public String voltea(String cadena){
        String invertida = "";
        for(int i = cadena.length() - 1; i >= 0; i--)
            invertida += cadena.charAt(i);
        return invertida;
    }

    /**
     * Método que regresa el nombre de un archivo sin formato.
     * @param <code>arch</code> cadena con caracteres especiales.
     * @return cadena con el nombre de un archivo.
     */
    private String recorta(String arch){
        String v = voltea(arch);
        int i = v.indexOf(".");
        int s = v.indexOf("/");
        if(s < 0){
            String ve = v.substring(i + 1, v.length());
            ve = voltea(ve);
            return ve;
        }else{
            String ve = v.substring(i + 1,s);
            ve = voltea(ve);
            return ve;
        }
    }

    /**
     * Método que guarda los bytes en un arreglo. Los guarda en hexadecimal.
     * @param <code>clavehash</code> SHA256 que funciona como nuestra clave.
     * @return arreglo de bytes.
     */
    public static byte[] stringToBytes(String clavehash){
        int length = clavehash.length();
        byte[] output = new byte[length / 2];
        for (int i = 0; i < length; i += 2) 
            output[i / 2] = (byte) ((digit(clavehash.charAt(i), 16) << 4) | digit(clavehash.charAt(i+1), 16));
        return output;
    }

    /**
     * Método que encripta un archivo.
     * @param <code>key</code> arreglo de bytes con la clave en hexadecimal.
     * @param <code>iv</code> es como una semilla para poder encriptar el texto.
     * @param <code>value</code> el texto que se quiere encriptar.
     * @return una cadena con el texto encriptado.
     */
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

    /**
     * Método para obtener una dispersión con SHA256.
     * @param cadena <code>data</code> de la que se quiere su dispersión con
     *        SHA256.
     * @return cadena con la dispersión de SHA256.
     */
    public String getSHA256Hash(String data){
        String result = null;
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(data.getBytes("UTF-8"));
            return bytesToHex(hash);
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Método que transforma un arreglo de bytes en una cadena en hexadecimal.
     * @param arreglo de bytes, del que se quiere su representación en
     *        hexadecimal.
     * @return cadena en hexadecimal.
     */
    private String bytesToHex(byte[] hash){
        String s = DatatypeConverter.printHexBinary(hash);
        s = s.toLowerCase();
        return s;
    }

    /**
     * Lee un archivo 
     * @param la ruta de donde se encuentra el archivo.
     * @return el contenido en cadena del mensaje en el archivo.
     */
    private String lecturaArchivo(String archivo) throws FileNotFoundException, IOException{
        String mensaje = "";
        FileReader fr = null;
        BufferedReader br = null;
        try{
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            String cadena;
            while((cadena = br.readLine()) != null)
                mensaje += cadena + " ";
        }catch(IOException ioe){
            System.out.println("Error en la lectura del archivo");
            System.exit(1);
        }
        return mensaje;
    }

    /**
     * Método igual a {@link #validaNums}, se hizo para pruebas unitarias.
     * @param dos enteros.
     * @return <code>false</code> si <code>n</code> es menor o igual a 2,
     *         <code>false</code> si <code>t</code> es mayor a n,
     *         <code>true</code> en otro caso.
     */
    public boolean validaNUms(int n, int t){
        if(n <= 2)
            return false;
        if(n < t)
            return false;
        return true;
    }
    
    /**
     * Método es igualque {@link #validaNUms }que valida que los enteros sean 
     * del tamaño correspondiente.
     * En caso de no serlo, el programa termina.
     */
    public void validaNums(int n, int t){
        if(n <= 2){
            System.out.println("Error en las evaluaciones intente: n > 2");
            System.exit(1);
        }
        if(n < t){
            System.out.println("Error en el mínimo de evaluaciones requeridas, intente: 1 < t ≤ n");
            System.exit(1);
        }
    }

    /**
     * Método que cambia <code>num</code> a su representación en entero.
     * @param <code>num</num> numero en su representación en cadena.
     * @return int, representación de la cadena.
     */
    public int getInt(String num){
        int i = 0;
        try{
            i = Integer.parseInt(num);
        }catch(NumberFormatException nfe){
            System.out.println("'" + num + "'" + " No es un número valido");
            System.exit(1);
        }
        return i;
    }
}
