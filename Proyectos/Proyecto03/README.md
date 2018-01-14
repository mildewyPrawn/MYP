Proyecto03
=============================

Materia
------------------------------
Modelado y Programación.

Autores
------------------------------
[Ángeles Martínes Ángela Janín.](https://github.com/AngelaJanin)  
[Galeana Araujo Emiliano.](https://github.com/mildewyPrawn)

Programa
------------------------------
1. Para compilar el programa:  
` ant compile.shamir`  `ant shamir.jar`  
2. Para correr el programa:  
> Para cifrar:  `java -jar shamir.jar c archivo.frg n t archivo.aes`  
> Para decifrar:  `jav -jar shamir.jar d archivo.frg archivo.aes`  
3. Para correr las pruebas unitarias:  
`ant test`  
4. Para generar la documentación:  
`ant doc`  
5. Para limpiar:  
`ant clean`

Notas
------------------------------
1. Descargar el [jce_policy-8.zip.](http://wwww.oracle.com/technetwork/java/javase/downloads/jce8-download-2133166.html)
2. En la carpeta donde está guardado:  
> `unzip jce_policy-8.zip`
> `cd UnlimitedJCEPolicyJDK8/`
> `echo $JAVA_HOME`
> `sudo cp -f local_policy.jar <LaRutaDe'echo $JAVA_HOME'>/jre/lib/security`  
			SIN LOS PICOPARÉNTESIS.
> `sudo cp -f US_export_policy.jar <LaRutaDe'echo $JAVA_HOME'>/jre/lib/security`  
			SIN LOS PICOPARÉNTESIS.

