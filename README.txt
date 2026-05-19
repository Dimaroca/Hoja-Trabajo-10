Sistema de Rutas para Hospitales de Guatemala usando Grafos y Floyd-Warshall
Descripción

Este programa implementa un grafo dirigido utilizando una matriz de adyacencia para representar conexiones entre ciudades de Guatemala. Cada ciudad corresponde a un vértice y cada carretera corresponde a un arco con un peso asociado que representa la distancia en kilómetros.

El sistema permite calcular la ruta más corta entre cualquier par de ciudades utilizando el algoritmo de Floyd-Warshall. Además, permite identificar el centro del grafo, modificar conexiones existentes y recalcular automáticamente las rutas más cortas cuando la red vial cambia.

El proyecto fue desarrollado como parte del curso CC2003 - Algoritmos y Estructura de Datos.

Problema que resuelve

Debido a cierres de carreteras, derrumbes o restricciones sanitarias, las conexiones entre ciudades pueden cambiar constantemente.

El programa ayuda a determinar:

La ruta más corta entre dos ciudades.
La distancia total de dicha ruta.
Las ciudades intermedias por las que se debe pasar.
La ciudad que ocupa el centro del grafo.
Los efectos de agregar o eliminar conexiones dentro de la red vial.
Estructura del proyecto
Proyecto
│
├── Main.java
├── Grafo.java
├── Floyd.java
├── guategrafo.txt
└── README.md
Formato del archivo de entrada

El programa lee un archivo llamado:

guategrafo.txt

Cada línea representa una conexión dirigida entre dos ciudades.

Formato:

CiudadOrigen CiudadDestino Distancia

Ejemplo:

Mixco Antigua 30
Antigua Escuintla 25
Escuintla SantaLucia 15
Guatemala Mixco 10

Esto significa:

Mixco -> Antigua = 30 km
Antigua -> Escuintla = 25 km
Escuintla -> SantaLucia = 15 km
Guatemala -> Mixco = 10 km
Funcionalidades
1. Consultar ruta más corta

Solicita:

Ciudad origen
Ciudad destino

Y muestra:

Distancia mínima
Ruta completa
Ciudades intermedias

Ejemplo:

Origen: Mixco
Destino: SantaLucia

Distancia más corta: 70 KM

Ruta:
Mixco -> Antigua -> Escuintla -> SantaLucia
2. Mostrar centro del grafo

Calcula la ciudad con la menor excentricidad.

El centro del grafo corresponde al vértice cuya distancia máxima hacia las demás ciudades es la menor posible.

Ejemplo:

Centro del grafo: Antigua
3. Modificar el grafo

Permite:

Eliminar una conexión

Ejemplo:

Antigua -> Escuintla

La conexión deja de existir.

Agregar una conexión

Ejemplo:

Mixco -> Escuintla = 20 km

Luego de cada modificación:

Se recalculan todas las rutas más cortas
Se recalcula el centro del grafo
4. Mostrar matriz de adyacencia

Muestra la representación interna del grafo.

Ejemplo:

0 30 INF INF
INF 0 25 INF
INF INF 0 15
INF INF INF 0

Donde:

INF = No existe conexión directa
5. Finalizar programa

Cierra la ejecución.

Algoritmo utilizado
Floyd-Warshall

El algoritmo de Floyd-Warshall encuentra el camino más corto entre todos los pares de vértices del grafo.

Ventajas:

Calcula todas las rutas mínimas en una sola ejecución.
Permite reconstruir el camino completo.
Funciona sobre grafos dirigidos.
Facilita el cálculo del centro del grafo.

Complejidad temporal:

O(n
3
)

donde:

n = cantidad de ciudades
Representación del grafo

Se utiliza una matriz de adyacencia:

int[][] matriz;

Cada posición:

matriz[i][j]

representa la distancia desde la ciudad i hacia la ciudad j.

Ejemplo de ejecución
--- MENU ---

1. Buscar ruta más corta
2. Mostrar centro del grafo
3. Modificar grafo
4. Mostrar matriz de adyacencia
5. Salir

Consulta:

Seleccione una opcion: 1

Ciudad origen: Mixco
Ciudad destino: SantaLucia

Resultado:

Distancia más corta: 70 KM

Ruta:
Mixco -> Antigua -> Escuintla -> SantaLucia
Requisitos
Java JDK 8 o superior.
Archivo guategrafo.txt ubicado en el mismo directorio de ejecución.
Compilación
javac *.java
Ejecución
java Main