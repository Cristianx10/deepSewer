# deepSewer
Deep Sewer
=======
Es un juego basado en el escapismo de una alcantarrilla, en la cual dos jugadores tendran que competir por salir esquivando barriles hasta llegar a la salida


Documentacion de metodos
========================

# Logica
Atributos: 
- app: PApplet // variable de referencia a la clase main
Metodos:
+ Logica(PApplet) // constructor de la clase logica

# Main
Atributos:
- log: Logica // añade toda la funcionalidad de la aplicacion
Metodos:
+ settings (): void // inicializa el tamaño de la pantalla de la aplicacion
+ setup (): void // inicializa todas las variables y clases creadas
+ draw (): void // pinta todos los componentes que se visualizan en la aplicacion
+ mousePressed(): void // se encarga de todas las acciones de presion del mouse
+ mouseReleased(): void // se encarga de todas las acciones cuando el mouse no esta presionado

# Personaje
Atributos:
- pos : PVector // se encarga de gestionar la posicion del personaje en el lienzo
- vel : PVector // comprueba y modifica la velocidad del personaje
- isSalto: boolean // valida si el personaje esta saltando
- isMove : boolean // comprueba si el personaje se esta moviendo
- move : booelan[] // comprueba hacia donde va el personaje
- vida: int // cuantifica la vida del personaje
- vida : boolean // comprueba si esta vivo
- puntaje: int // muestra el puntaje del personaje

# Metodos:
+ Personaje() // inicializador del constructor del personaje
+ pintar() : void // se encarga de la visualizacion del personaje
+ salto() : void // se encarga de la interaccion del salto del personaje
+ escalar() : void // se encarga de la programacion que le permite subir escaleras al personaje 


# Barril
Atributos: 
- pos: PVector // describe la posicion del objeto del lienzo
- vel: PVector // controla la velocidad del objeto
- isSobre: boolean // comprueba si algo esta sobre el objeto

Metodos:
+ Barril(): void // constructor de la clase
+ pintar(): void // se encarga de la visaulizacion del objeto en el lienzo
+ mover(): void // se encarga del movimiento del barril en los ejes de lienzo

# Escalera
Atributos:
- pos: PVector // describe la posicion del objeto del lienzo
Metodos:
+ Escaleras() // constructor de la clase
+ pintar(): void // se encarga de la visaulizacion del objeto en el lienzo

# Camara
Atributos:
- pos: PVector // enfoca la posicion de una parte de la pantalla 
- pos: PVecto  // velocidad del desplazamiento de la camara
- limites: int[] // limita los alcances maximos que pueda tener la pantalla en el lienzo 

Metodos:
+ Camara() // constructor del objeto
+ moverRight(): void // se encarga de las propiedades que le permiten a la camara moverse hacia la derecha
+ moverLeft(): void // se encarga de las propiedades que le permiten a la camara moverse hacia la izquierda
+ moverUp(): void //se encarga de las propiedades que le permiten a la camara moverse hacia arriba
+ moverDown(): void // se encarga de las propiedades que le permiten a la camara moverse hacia abajo
+ start(): void // inicializa los objetos necesarios para mover la camara
+ end(): void //  inicializa los objetos necesarios para finalizar la camara


# Escenario
Atributos:
- pos: PVector; // describe la posicion del lienzo
- lienzo: PImage // almacena el escenario para posteriormente ser pintado
Metodos:
+ Escenario() // constructor de clase
+ pintar(): void // pinta lienzo en la pantalla


# Hueco
Atributos:
- pos : int; //Se encarga de monitoria la posicion en el lienzo
Metodos:
+ pintar(): void
