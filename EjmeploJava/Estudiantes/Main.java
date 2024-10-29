import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GestorCSV gestor = new GestorCSV("notas_estudiantes.csv");
        Scanner scanner = new Scanner(System.in);
        boolean ejecutar = true;

        while (ejecutar) {
            System.out.println("\n--- Sistema de Gestión de Notas ---");
            System.out.println("1. Agregar estudiante");
            System.out.println("2. Mostrar estudiantes");
            System.out.println("3. Editar nota de estudiante");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    System.out.print("Nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Apellido: ");
                    String apellido = scanner.nextLine();
                    System.out.print("Materia: ");
                    String materia = scanner.nextLine();
                    System.out.print("Nota: ");
                    double nota = Double.parseDouble(scanner.nextLine());

                    Estudiante nuevoEstudiante = new Estudiante(nombre, apellido, materia, nota);
                    gestor.agregarEstudiante(nuevoEstudiante);
                    System.out.println("Estudiante agregado correctamente.");
                    break;

                case "2":
                    System.out.println("\n--- Lista de Estudiantes ---");
                    gestor.mostrarEstudiantes();
                    break;

                case "3":
                    System.out.print("Nombre del estudiante a editar: ");
                    nombre = scanner.nextLine();
                    System.out.print("Apellido: ");
                    apellido = scanner.nextLine();
                    System.out.print("Materia: ");
                    materia = scanner.nextLine();
                    System.out.print("Nueva Nota: ");
                    double nuevaNota = Double.parseDouble(scanner.nextLine());

                    gestor.editarEstudiante(nombre, apellido, materia, nuevaNota);
                    break;

                case "4":
                    ejecutar = false;
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
                    break;
            }
        }

        scanner.close();
    }
}

