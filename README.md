
﻿# 
=======
>>>>>>> 6a61768956fcd8152441a74c6b87da31163ce4a1

Deep Sewer
=======
Es un juego basado en el escapismo de una alcantarrilla, en la cual dos jugadores tendran que competir por salir esquivando barriles hasta llegar a la salida


Documentacion de metodos
========================

# Logica
## Atributos: 
- app: PApplet // variable de referencia a la clase main
Metodos:
+ Logica(PApplet) // constructor de la clase logica
+ pintar

# Main
## Atributos:
- log: Logica // añade toda la funcionalidad de la aplicacion
## Metodos:
+ settings (): void // inicializa el tamaño de la pantalla de la aplicacion
+ setup (): void // inicializa todas las variables y clases creadas
+ draw (): void // pinta todos los componentes que se visualizan en la aplicacion
+ mousePressed(): void // se encarga de todas las acciones de presion del mouse
+ mouseReleased(): void // se encarga de todas las acciones cuando el mouse no esta presionado

# Personaje
## Atributos:
- pos : PVector // se encarga de gestionar la posicion del personaje en el lienzo
- vel : PVector // comprueba y modifica la velocidad del personaje
- isSalto: boolean // valida si el personaje esta saltando
- isMove : boolean // comprueba si el personaje se esta moviendo
- move : booelan[] // comprueba hacia donde va el personaje
- vida: int // cuantifica la vida del personaje
- vida : boolean // comprueba si esta vivo
- puntaje: int // muestra el puntaje del personaje

## Metodos:
+ Personaje(Logica) // inicializador del constructor del personaje
+ pintar() : void // se encarga de la visualizacion del personaje
+ salto() : void // se encarga de la interaccion del salto del personaje
+ escalar() : void // se encarga de la programacion que le permite subir escaleras al personaje 


# Barril
## Atributos: 
- pos: PVector // describe la posicion del objeto del lienzo
- vel: PVector // controla la velocidad del objeto
- isSobre: boolean // comprueba si algo esta sobre el objeto

## Metodos:
+ Barril(Logica): void // constructor de la clase
+ pintar(): void // se encarga de la visaulizacion del objeto en el lienzo
+ mover(): void // se encarga del movimiento del barril en los ejes de lienzo

# Escalera
## Atributos:
- pos: PVector // describe la posicion del objeto del lienzo
## Metodos:
+ Escaleras(Logica) // constructor de la clase
+ pintar(): void // se encarga de la visaulizacion del objeto en el lienzo

# Camara
## Atributos:
- pos: PVector // enfoca la posicion de una parte de la pantalla 
- pos: PVecto  // velocidad del desplazamiento de la camara
- limites: int[] // limita los alcances maximos que pueda tener la pantalla en el lienzo 

## Metodos:
+ Camara(Logica) // constructor del objeto
+ moverRight(): void // se encarga de las propiedades que le permiten a la camara moverse hacia la derecha
+ moverLeft(): void // se encarga de las propiedades que le permiten a la camara moverse hacia la izquierda
+ moverUp(): void //se encarga de las propiedades que le permiten a la camara moverse hacia arriba
+ moverDown(): void // se encarga de las propiedades que le permiten a la camara moverse hacia abajo
+ start(): void // inicializa los objetos necesarios para mover la camara
+ end(): void //  inicializa los objetos necesarios para finalizar la camara


# Escenario
## Atributos:
- pos: PVector; // describe la posicion del lienzo
- lienzo: PImage // almacena el escenario para posteriormente ser pintado
## Metodos:
+ Escenario(Logica) // constructor de clase
+ pintar(): void // pinta lienzo en la pantalla


# Hueco
## Atributos:
- pos : int; //Se encarga de monitoria la posicion en el lienzo
## Metodos:
+ Hueco(): void
+ pintar(): void


Servidor
=====

# Inteface MensajeObserver
## Atributos
+ onDataRecever(Object): void //Encargada de gestionar el patron observer

# ServerSingleton
## Atributos
_-_ serverSocket: ServerSocket //Espera la comunicion o solicitud

## Metodos
_+_ ServerSingleton() // Constructor de clase
_+_ getConstructor(): ServerSingleton //Se inicializa a si mismo si detecta que es un nulo
_+_ run(): void //Porgrama la recepcion y envio
_+_ setObservador(): void //Encargada de gestionar el patron observer

# TCPConection
## Atributos
_-_ socket: Socket // Crea canales de comunicacion

## Metodos
_+_ TCPConection() //Constructor de clase
_+_ run(): void // Se encarga de leer constantemente datos de entrada
_+_ setObservador(MensajeObservador): void // Define un observador


# Receptor
## Atributos
_-_ socket: Socket // crea canales de comunicacion para envio o recepcion de datos

## Metodos
_+_ Receptor() // constructor de clase
_+_ run(): void // Se encarga de leer los datos constantemente


