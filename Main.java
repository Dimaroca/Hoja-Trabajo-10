import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Grafo grafo = new Grafo();

        try {
            grafo.leerArchivo("guategrafo.txt");
        } catch (Exception e) {
            System.out.println("Error al leer el archivo");
            return;
        }

        Floyd floyd = new Floyd(grafo.getMatriz(), grafo.getCiudades());

        int opcion;

        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Buscar ruta más corta");
            System.out.println("2. Mostrar centro del grafo");
            System.out.println("3. Modificar grafo");
            System.out.println("4. Mostrar matriz de adyacencia");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opcion: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {

                case 1:
                    System.out.print("Ciudad origen: ");
                    String origen = scanner.nextLine();

                    System.out.print("Ciudad destino: ");
                    String destino = scanner.nextLine();

                    floyd.mostrarRuta(origen, destino, grafo.getIndices());
                    break;

                case 2:
                    System.out.println("Centro del grafo: " + floyd.centroGrafo());
                    break;

                case 3:
                    System.out.println("1. Eliminar ruta");
                    System.out.println("2. Agregar conexion");
                    System.out.print("Seleccione una opcion: ");
                    int tipo = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Ciudad origen: ");
                    String ciudad1 = scanner.nextLine();

                    System.out.print("Ciudad destino: ");
                    String ciudad2 = scanner.nextLine();

                    if (tipo == 1) {
                        grafo.eliminarArco(ciudad1, ciudad2);
                        System.out.println("Conexion eliminada");
                    } else if (tipo == 2) {
                        System.out.print("Distancia en KM: ");
                        int distancia = scanner.nextInt();
                        scanner.nextLine();

                        grafo.agregarArco(ciudad1, ciudad2, distancia);
                        System.out.println("Conexion agregada");
                    }

                    floyd = new Floyd(grafo.getMatriz(), grafo.getCiudades());
                    break;

                case 4:
                    grafo.mostrarMatriz();
                    break;

                case 5:
                    System.out.println("Programa finalizado");
                    break;

                default:
                    System.out.println("Opcion invalida");
                    break;
            }

        } while (opcion != 5);

        scanner.close();
    }
}