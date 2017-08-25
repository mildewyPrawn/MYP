{-
- Modelado y Programación 2018-1
- Semanal: Combinaciones
-}


--Función que devuelve el factorial de un entero n

factorial :: Integer->Integer
factorial 0 = 1
factorial n = n * factorial (n-1)

--Función que devuelve la combinación de dos enteros n k

comb :: Integer -> Integer -> Integer
comb n r = (factorial n)`div` (factorial( n-r)* factorial(r))



    


