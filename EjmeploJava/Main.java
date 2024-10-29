import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // Ruta del archivo .txt a manejar
        String rutaArchivo = "archivoEjemplo.txt"; // Archivo en la raíz
        OperacionesArchivo operaciones = new OperacionesArchivo(rutaArchivo);

        // Ruta de la carpeta de destino
        String carpetaDestino = "Archivos";
        File carpeta = new File(carpetaDestino);

        // Crear la carpeta si no existe
        if (!carpeta.exists()) {
            carpeta.mkdir(); // Crea la carpeta "Archivos"
            System.out.println("Carpeta 'Archivos' creada.");
        }

        try {
            // 1. Crear el archivo
            operaciones.crearArchivo();

            // 2. Agregar contenido al archivo
            operaciones.agregarArchivo("Este es un nuevo contenido agregado.\n");

            // 3. Leer el archivo
            System.out.println("Contenido después de agregar:");
            operaciones.leerArchivo();

            // 4. Hacer una copia del archivo
            String rutaCopia = "copiaArchivoEjemplo.txt"; // Nombre de la copia
            operaciones.copiarArchivo(rutaCopia);
            System.out.println("Copia del archivo creada: " + rutaCopia);

            // 5. Mover la copia a la carpeta "Archivos"
            operaciones.moverArchivo(carpetaDestino + "/copiaArchivoEjemplo.txt");
            System.out.println("Copia del archivo movida a la carpeta 'Archivos'.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
