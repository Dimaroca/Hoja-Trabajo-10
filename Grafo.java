import java.io.*;
import java.util.*;

public class Grafo {
    /*OpenAI. (2026, 19 de mayo). Respuesta generada por ChatGPT sobre la implementación en Java del algoritmo de Floyd
    [Modelo de lenguaje de gran escala]. ChatGPT. https://chatgpt.com/ 
    Unicamente proporciono la base del algoritmo, editado en base a ello*/    
    public static final int INF = 999999;

    private Map<String, Integer> indices;
    private ArrayList<String> ciudades;
    private int[][] matriz;

    public Grafo() {
        indices = new HashMap<>();
        ciudades = new ArrayList<>();
    }

    public void leerArchivo(String nombre) throws IOException {
        ArrayList<String[]> lineas = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader(nombre));
        String linea;

        while ((linea = br.readLine()) != null) {
            if (!linea.trim().isEmpty()) {
                String[] partes = linea.split("\\s+");

                String origen = partes[0];
                String destino = partes[1];
                String distancia = partes[2];

                agregarCiudad(origen);
                agregarCiudad(destino);

                lineas.add(partes);
            }
        }

        br.close();

        inicializarMatriz();

        for (String[] partes : lineas) {
            agregarArco(partes[0], partes[1], Integer.parseInt(partes[2]));
        }
    }

    private void agregarCiudad(String ciudad) {
        if (!indices.containsKey(ciudad)) {
            indices.put(ciudad, ciudades.size());
            ciudades.add(ciudad);
        }
    }

    private void inicializarMatriz() {
        int n = ciudades.size();
        matriz = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    matriz[i][j] = 0;
                } else {
                    matriz[i][j] = INF;
                }
            }
        }
    }

    public void agregarArco(String origen, String destino, int distancia) {
        boolean cambio = false;

        if (!indices.containsKey(origen)) {
            agregarCiudad(origen);
            cambio = true;
        }

        if (!indices.containsKey(destino)) {
            agregarCiudad(destino);
            cambio = true;
        }

        if (cambio) {
            redimensionarMatriz();
        }

        int i = indices.get(origen);
        int j = indices.get(destino);

        matriz[i][j] = distancia;
    }

    public void eliminarArco(String origen, String destino) {
        if (indices.containsKey(origen) && indices.containsKey(destino)) {
            int i = indices.get(origen);
            int j = indices.get(destino);

            matriz[i][j] = INF;
        }
    }

    private void redimensionarMatriz() {
        int n = ciudades.size();
        int[][] nueva = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    nueva[i][j] = 0;
                } else {
                    nueva[i][j] = INF;
                }
            }
        }

        if (matriz != null) {
            for (int i = 0; i < matriz.length; i++) {
                for (int j = 0; j < matriz.length; j++) {
                    nueva[i][j] = matriz[i][j];
                }
            }
        }

        matriz = nueva;
    }

    public int[][] getMatriz() {
        return matriz;
    }

    public ArrayList<String> getCiudades() {
        return ciudades;
    }

    public Map<String, Integer> getIndices() {
        return indices;
    }

    public void mostrarMatriz() {
        System.out.println("Matriz de adyacencia:");

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                if (matriz[i][j] == INF) {
                    System.out.print("INF ");
                } else {
                    System.out.print(matriz[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}
