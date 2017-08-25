
 mensaje:- nl,
    write('Saludo:"Bienvenido".'),
    nl,
    write('Instrucciones:'),
    nl,
    write('La siguiente función es el número de combinaciones de la siguiente forma:'),
    nl,
    write('|n|'),
    nl,
    write('|r|'),
    nl,
    write('"n" debe ser mayor a "r" y solo lo calcula con valores enteros y en el siguiente orden '),
    nl,
    write('"comb(X,Y,Z)." donde X son los valores de "n", Y los valores de "r" y Z una variable en mayúscula'),
    nl,
    write('Z es la letra que será representado su resultado Ejemplo: Z = "resultado", NO OLVIDE PONER EL PUNTO DESPUÉS DE'),
    nl,
    write('la funcion comb() si pone una variable la cual n>r entonces la función le devolvera FALSE.').
    
  saludo:- write('Hola...mundo').
    :- mensaje.
    
factorial(0,1). 
factorial(N,F) :-  
   N>0, 
   N1 is N-1, 
   factorial(N1,F1), 
   F is N * F1.

comb(N,R,F):- N>0, R>0, N > R, RESTA is N-R, factorial(RESTA,Z2), factorial(N,Z1), factorial(R,Z3), F is Z1/(Z2*Z3). 
   
