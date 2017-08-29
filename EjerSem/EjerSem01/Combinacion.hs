--Biblioteca para leer entradas
import Text.Read

--Modelado y programaci+on 2018-1
--Semanal 01: Programa que implementa la solución de 
--la combinación de n en r.

--Función factorial.
fac :: Int -> Int
fac 0 = 1
fac 1 = 1
fac n = n*(fac (n-1))

--Función de combinaciones de n en r.
comb :: (Int,Int) -> Int
comb (n,r) = if ((r <= n) && (n <= 1000))
               then (div (fac n) ((fac (n-r))*(fac r)))
               else error ((show r)++" debe ser menor o igual a "++(show n))

--Función que solicita al usuario un par de números enteros.
obtenInt :: IO (Maybe (Int,Int))
obtenInt = do
  putStrLn "Ingresa el primer entero:"
  line <- getLine
  case readMaybe line of
    Just x -> do
      putStrLn "Ingresa el segundo entero:"
      line2 <- getLine
      case readMaybe line2 of
        Just y -> return (Just (x,y))
        Nothing -> return Nothing
    Nothing -> return Nothing


--Función principal
main = do
  n <- obtenInt
  case n of
    Just (n,r) -> let res = comb (n,r) in
      putStrLn ("Resultado: "++(show res))
    Nothing -> error "Argumentos inválidos."
