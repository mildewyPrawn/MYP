#include <stdio.h>
#include <stdlib.h>
#include <iostream>
#include <iomanip>
#include <sstream>
#include <cstring>
#include <string>
#include <curses.h>
#include <ctype.h>
#include <fstream>

using namespace std;

#include <openssl/sha.h>

string sha256(const string str){ //sha256
  unsigned char hash[SHA256_DIGEST_LENGTH];
  SHA256_CTX sha256;
  SHA256_Init(&sha256);
  SHA256_Update(&sha256, str.c_str(), str.size());
  SHA256_Final(hash, &sha256);
  stringstream ss;
  for(int i = 0; i < SHA256_DIGEST_LENGTH; i++)
    ss << hex << setw(2) << setfill('0') << (int)hash[i];
  return ss.str();
}

string getpass(const char *prompt){
  printw(prompt);
  noecho();
  
  char buff[64];
  getnstr(buff, sizeof(buff));
  
  echo();
  return buff;
}

void validaNum(int evaluacion, int minimo){
  if(evaluacion <= 2){
    printf("Error en las evaluaciones intente: n > 2\n");
    exit(EXIT_FAILURE);
  }
  if(evaluacion < minimo){
    printf("Error en el mínimo de evaluaciones requeridas, intente: 1 < t ≤ n\n");
    exit(EXIT_FAILURE);
  }
}

void valida(int argc){
  if(argc > 6 || argc < 4 || argc == 5){
    printf("Shamir: Error en argumentos.\n");
    printf("Shamir: para cifrar un archivo intente:\n");
    printf("'Shamir c nombreArchivo numEvaluaciones numMinimo nomArchDoc'\n");
    printf("Shamir: para develar un archivo intente:\n");
    printf("'Shamir d nomArch nomArchCifrado'\n");
    exit(EXIT_FAILURE);
  }
}

int independiente(string sha256){ //Suma todos los caracteres de la cadena en ASCII
  int k = 0;
  for(int i = 0; i < sha256.length(); i++){
    string s = sha256.substr(i,i+1);
    char c = s[0];
    int j;
    if(isdigit(c))
      //      j = c - '0'; //suma el digito
      j = (int)c;
    else
      j = (int)c; //suma su valor el ASCII
    k +=j;
    //    cout << c  << " actualiza k " << k << endl;
  }
  return k;
}

bool file_exists(const char *filename){
  ifstream infile(filename);
  return infile.good();
}

string file_reader(const char *filename){
  ifstream in(filename);
  string s, line;
  while(getline(in,line))
    s += line + "\n";
  return s;
}

string file_check(const char *filename){
  string s = "";
  if(file_exists(filename)){
    s = file_reader(filename);
  }else{
    cout << "No existe el fichero: '" << filename << "'." << endl;
    exit(EXIT_FAILURE);
  }
  return s;
}

main(int argc, char *argv[]){
  valida(argc);
  if(strcmp(argv[1],"c") == 0){
    if(argc != 6){
      printf("Shamir: Error en argumentos.\n");
      printf("Shamir: para cifrar un archivo intente:\n");
      printf("'Shamir c nombreEvaluaciones numEvaluaciones numMinimo nomArchivo'\n");
      exit(EXIT_FAILURE);
    }
    printf("CIFRAR\n");
    //COSAS PARA CIFRAR
    int ev = atoi(argv[3]);
    int min = atoi(argv[4]);
    validaNum(ev, min);
    string textocipher = file_check(argv[5]);
    string pwd;
    initscr();
    pwd = getpass("A continuacion escribe la puta contraseña: ");//Contraseña
    endwin();
    string sha = sha256(pwd);
    int k = independiente(sha);
    cout << k << " Es el termino independiente" << endl;
    cout << sha << " contraseña de aca" << endl;
    cout << textocipher << endl;
    
    exit(0);
  }else if(strcmp(argv[1],"d") == 0){
    if(argc != 4){
      printf("Shamir: Error en argumentos.\n");
      printf("Shamir: para develar un archivo intente:\n");
      printf("'Shamir d nomArch nomArchCifrado'\n");
      exit(EXIT_FAILURE);
    }
    printf("DEVELAR\n");
    //COSAS PARA DEVELAR
    exit(0);
  }
  printf("Shamir: '%s' no es una bandera válida.\n",argv[1]);
  exit(EXIT_FAILURE);
}
