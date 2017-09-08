#include <stdio.h>
#include <string.h>
#include <stdlib.h>
 
 /*
  * Multiplica dos numeros.
  */
double multiplica(double entrada_uno, double entrada_dos){
  return entrada_uno*entrada_dos;
} 

/*
 * recibe un entero, lo imprime e imprime el impuesto aplicado a esa cantidad
 * en Mexico.
 */
void imprimeMex(double i){
  printf("Pagar al SAT: %f \n", i);
  double total = multiplica(i, 16);
  printf("IVA: %f \n", total);
}


/*
 * recibe un entero, lo imprime e imprime el impuesto aplicado a esa cantidad
 * en Colombia.
 */
void imprimeCol(double i){
  printf("Pagar al Ministerio de Hacienda: %f \n", i);
  double total = multiplica(i, 15);
  printf("Renta: %f \n", total);
}


/*
 * recibe un entero, lo imprime e imprime el impuesto aplicado a esa cantidad
 * en EUA.
 */
void imprimeEUA(double i){  
  printf("Pay to US Government: %f \n", i);
  double total = multiplica(i, 3.45); 
  printf("Tax: %f \n", total);
}


int main(int argc, char** argv){

  //Si nos dan menos de dos parametros, terminamos.
  if(argc < 2){
    printf("Faltan parametros:\nIntente con un pais y un monto.\n");
    exit(0);
    //Si nos dan mas de tres terminamos.
  }else if(argc > 3){
    printf("Sobran parametros:\nIntente con un pais y un monto.\n");
    exit(0);    
  }
  //Si el segundo parametro, que es la cantidad es cero, terminamos.
  if(atof(argv[2]) == 0){
    printf("No se adeuda nada.\n");
    exit(0);
  }

  //evaluamos el primero parametro, si es algun pais conocido, manda a llamar a imprimeX.
  if(strcmp(argv[1], "MEXICO") == 0)
    imprimeMex(atof(argv[2]));
  else if(strcmp(argv[1],"EUA") == 0)
    imprimeEUA(atof(argv[2]));
  else if(strcmp(argv[1],"COLOMBIA") == 0)
    imprimeCol(atof(argv[2]));
  return 0;
}
