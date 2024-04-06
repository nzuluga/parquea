# Parquea
Data Structures Project


Este proyecto busca solucionar un problema de un parqueadero el cual va a manejar un numero limitado de cupos, que se iran ocupando según los vehiculos que entran o salen a este.

Por siguiente se va a manejar los tiempos de entrada y salida, reconocimiento de la placa de los vehiculos y el tipo de vehiculo, en adición se va a trabajar el sistema automatico de permiso de entrada o salida y el cobro automatico por tiempo de estancia.

Para resolver este problema se utiliza el manejo de datos por el paradigma programación orientada a objetos y la aplicación de las estructuras de datos a este problema. 

## BIG O
![grafik](https://github.com/nzuluga/parquea/assets/144562439/f2339fcc-97bb-4a35-ac91-b174ec8dc5e3)

En esta parte se realizo una prueba con la inserción de datos para el algoritmo planteado en el proyecto Parquea, con ayuda de software Excel microsoft, la curva que mejor se aproxima al rednimiento de añadri, busqueda de datos es de forma lineal que se aprecia previamente, los datos obtenidos son los siguientes en la tabla procendente

![grafik](https://github.com/nzuluga/parquea/assets/144562439/b5b4e281-c97f-43d8-bd44-933b661f9c00)


A pesar de que en los datos no se observa un crecimiento lineal respecto a la cantidad de datos que se estan trabajando , pero ya en la curva previa si se observa que le mejor comportamiento es lineal, debido a errores de cantidad de memoria, no se logro soobrepasar los 40 millones de daots. por lo cual se dejo hasta 30 millones. por otro punto si se observa detenemiante la grafica se observa que si se trabaja en escala logaritmica la canitdad de datos , nos da la siguiente curva

![grafik](https://github.com/nzuluga/parquea/assets/144562439/38ba2a03-1186-433b-8859-4f4c89c8160a)

Se puede observar un cremimiento tipo cuadrado en escala logaritmica de los datos trabajados. 

Inicialmente se procesa y se puede ajustar una curva de comportamiento lineal Big O (n), pero cuando se trabaja en escala logaritmica se observa un crecimiento lineal O(n)
