import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestorCSV {
    private String rutaArchivo;

    public GestorCSV(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;

        // Crear el archivo si no existe
        File archivo = new File(rutaArchivo);
        if (!archivo.exists()) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(archivo, true))) {
                writer.println("Nombre,Apellido,Materia,Nota"); // Encabezados
            } catch (IOException e) {
                System.out.println("Error al crear el archivo: " + e.getMessage());
            }
        }
    }

    // Agregar un nuevo estudiante al archivo
    public void agregarEstudiante(Estudiante estudiante) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(rutaArchivo, true))) {
            writer.println(estudiante.toString());
        } catch (IOException e) {
            System.out.println("Error al agregar estudiante: " + e.getMessage());
        }
    }

    // Leer todos los estudiantes del archivo
    public List<Estudiante> leerEstudiantes() {
        List<Estudiante> estudiantes = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            reader.readLine(); // Saltar encabezado
            while ((linea = reader.readLine()) != null) {
                String[] campos = linea.split(",");
                if (campos.length == 4) {
                    Estudiante estudiante = new Estudiante(campos[0], campos[1], campos[2], Double.parseDouble(campos[3]));
                    estudiantes.add(estudiante);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer estudiantes: " + e.getMessage());
        }

        return estudiantes;
    }

    // Mostrar estudiantes en consola
    public void mostrarEstudiantes() {
        List<Estudiante> estudiantes = leerEstudiantes();
        for (Estudiante estudiante : estudiantes) {
            System.out.println(estudiante.getNombre() + " " + estudiante.getApellido() +
                    " - " + estudiante.getMateria() + ": " + estudiante.getNota());
        }
    }

    // Editar la nota de un estudiante
    public void editarEstudiante(String nombre, String apellido, String materia, double nuevaNota) {
        List<Estudiante> estudiantes = leerEstudiantes();
        boolean encontrado = false;

        // Buscar el estudiante y actualizar la nota
        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getNombre().equals(nombre) && estudiante.getApellido().equals(apellido) &&
                estudiante.getMateria().equals(materia)) {
                estudiante.setNota(nuevaNota);
                encontrado = true;
                break;
            }
        }

        if (encontrado) {
            // Reescribir el archivo con los datos actualizados
            try (PrintWriter writer = new PrintWriter(new FileWriter(rutaArchivo))) {
                writer.println("Nombre,Apellido,Materia,Nota"); // Encabezados
                for (Estudiante estudiante : estudiantes) {
                    writer.println(estudiante.toString());
                }
                System.out.println("Estudiante actualizado correctamente.");
            } catch (IOException e) {
                System.out.println("Error al actualizar estudiante: " + e.getMessage());
            }
        } else {
            System.out.println("Estudiante no encontrado.");
        }
    }
}
