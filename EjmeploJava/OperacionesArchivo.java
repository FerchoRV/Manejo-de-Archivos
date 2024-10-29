import java.io.*;

public class OperacionesArchivo {
    private String rutaArchivo;

    public OperacionesArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    // Crear un archivo
    public void crearArchivo() throws IOException {
        File archivo = new File(rutaArchivo);
        if (!archivo.exists()) {
            archivo.createNewFile();
            System.out.println("Archivo creado: " + rutaArchivo);
        }
    }

    // Agregar contenido al archivo
    public void agregarArchivo(String contenido) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo, true))) {
            writer.write(contenido);
        }
    }

    // Leer el archivo
    public void leerArchivo() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                System.out.println(linea);
            }
        }
    }

    // Copiar el archivo
    public void copiarArchivo(String rutaCopia) throws IOException {
        File origen = new File(rutaArchivo);
        File destino = new File(rutaCopia);

        try (InputStream in = new FileInputStream(origen);
             OutputStream out = new FileOutputStream(destino)) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
        }
    }

    // Mover el archivo
    public void moverArchivo(String rutaDestino) throws IOException {
        File archivoOrigen = new File(rutaArchivo);
        File archivoDestino = new File(rutaDestino);
        
        if (archivoOrigen.renameTo(archivoDestino)) {
            System.out.println("Archivo movido a: " + rutaDestino);
        } else {
            System.out.println("Error al mover el archivo.");
        }
    }

    // Eliminar un archivo
    public void eliminarArchivo() {
        File archivo = new File(rutaArchivo);
        if (archivo.delete()) {
            System.out.println("Archivo eliminado: " + rutaArchivo);
        } else {
            System.out.println("No se pudo eliminar el archivo: " + rutaArchivo);
        }
    }
}
