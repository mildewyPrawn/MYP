/**
 *@date 24 Ago 2017
 *
 *Programa que calcula las combinaciones de n en r.
 * <gmp.h> Biblioteca que permite hacer operaciones con números "muy grandes".
*/
#include <gmp.h>
#include <stdio.h>
#include <stdlib.h>
 
int main(int argc, char **argv){

	mpz_t n1, n2,n3,n4,n5;
	mpz_init(n1);
	mpz_init(n2);
	mpz_init(n3);
	mpz_init(n4);
	mpz_init(n5);

/*
 *Función que obtiene el factorial de un número positivo n.
 *@param Un número entero positivo n.
 *@return Un número entero positivo que corresponde a n!.
*/
	printf("\nSea A un conjunto de n elementos y 0 <= r <= n.\n");
	printf("A los subconjuntos de A que tienen r elementos,\n");
	printf("se les llama combinaciones de A tomadas de r en r.\n");
	printf("El número de combinaciones esta dado por n!/((n-r)!r!)\n\n");
	printf("Para cálcular las combinaciones de n tomadas de r en r. \n");
	printf("Dame un número entero positivo n.\n");
	int n = 0;
	fscanf(stdin,"%d",&n);
	mpz_fac_ui(n1, n);
	
	

/*
 *Función que obtiene el factorial de un número positivo r.
 *@param Un número entero positivo r.
 *@return Un número entero positivo que corresponde a r!.
*/
	printf("Dame un número entero positivo r\n");
	int r = 0;
	fscanf(stdin,"%d",&r);
	mpz_fac_ui(n2,r);
	
	
/*
 *Función que obtiene el factorial de la diferencia n-r;.
 *@param Un número entero positivo n-r.
 *@return Un número entero positivo que corresponde a (n-r)!.
*/
	int dif = n-r;
	mpz_fac_ui(n3,dif);
	
	
	
/*
 *Función que obtiene que multiplica (n-r)!r!;.
 *@param Dos números enteros positivos "n-r" y "r".
 *@return Un número entero positivo que corresponde a (n-r)!r!.
*/
	mpz_mul(n4,n3,n2);
	
	
/*
 *Función que obtiene que multiplica (n-r)!r!;.
 *@param Dos números enteros positivos "n-r" y "r".
 *@return Un número entero positivo que corresponde a (n-r)!r!.
*/
	mpz_div(n5,n1,n4);
	gmp_printf("Las combinaciones de %d tomadas de %d en %d = %Zd\n",n,r,r,n5);	

	mpz_clear(n1);
	mpz_clear(n2);
	mpz_clear(n3);

        return 0;
	
	
}
