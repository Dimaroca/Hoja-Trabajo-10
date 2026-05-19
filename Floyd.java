import java.util.*;

public class Floyd {

    private int[][] distancias;
    private int[][] siguiente;
    private ArrayList<String> ciudades;

    public Floyd(int[][] matriz, ArrayList<String> ciudades) {
        this.ciudades = ciudades;
        int n = matriz.length;

        distancias = new int[n][n];
        siguiente = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                distancias[i][j] = matriz[i][j];

                if (matriz[i][j] != Grafo.INF && i != j) {
                    siguiente[i][j] = j;
                } else {
                    siguiente[i][j] = -1;
                }
            }
        }

        ejecutar();
    }

    private void ejecutar() {
        int n = distancias.length;

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {

                    if (distancias[i][k] != Grafo.INF &&
                        distancias[k][j] != Grafo.INF &&
                        distancias[i][k] + distancias[k][j] < distancias[i][j]) {

                        distancias[i][j] = distancias[i][k] + distancias[k][j];
                        siguiente[i][j] = siguiente[i][k];
                    }
                }
            }
        }
    }

    public void mostrarRuta(String origen, String destino, Map<String, Integer> indices) {
        if (!indices.containsKey(origen) || !indices.containsKey(destino)) {
            System.out.println("Una de las ciudades no existe.");
            return;
        }

        int i = indices.get(origen);
        int j = indices.get(destino);

        if (distancias[i][j] == Grafo.INF) {
            System.out.println("No existe ruta entre esas ciudades.");
            return;
        }

        System.out.println("Distancia más corta: " + distancias[i][j] + " KM");
        System.out.println("Ruta: " + obtenerRuta(i, j));
    }

    private String obtenerRuta(int origen, int destino) {
        if (siguiente[origen][destino] == -1) {
            return "No hay ruta";
        }

        StringBuilder ruta = new StringBuilder();
        int actual = origen;

        ruta.append(ciudades.get(actual));

        while (actual != destino) {
            actual = siguiente[actual][destino];
            ruta.append(" -> ").append(ciudades.get(actual));
        }

        return ruta.toString();
    }

    public String centroGrafo() {
        int n = distancias.length;
        int menorExcentricidad = Grafo.INF;
        String centro = "";

        for (int i = 0; i < n; i++) {
            int excentricidad = 0;

            for (int j = 0; j < n; j++) {
                if (distancias[i][j] != Grafo.INF) {
                    excentricidad = Math.max(excentricidad, distancias[i][j]);
                }
            }

            if (excentricidad < menorExcentricidad) {
                menorExcentricidad = excentricidad;
                centro = ciudades.get(i);
            }
        }

        return centro;
    }
}