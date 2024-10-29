using System;

public class Program
{
    public static void Main(string[] args)
    {
        GestorCSV gestor = new GestorCSV("notas_estudiantes.csv");
        bool ejecutar = true;

        while (ejecutar)
        {
            Console.WriteLine("\n--- Sistema de Gestión de Notas ---");
            Console.WriteLine("1. Agregar estudiante");
            Console.WriteLine("2. Mostrar estudiantes");
            Console.WriteLine("3. Editar nota de estudiante");
            Console.WriteLine("4. Salir");
            Console.Write("Seleccione una opción: ");
            string? opcion = Console.ReadLine();

            switch (opcion)
            {
                case "1":
                    try
                    {
                        Console.Write("Nombre: ");
                        string? nombre = Console.ReadLine() ?? "Desconocido";
                        Console.Write("Apellido: ");
                        string? apellido = Console.ReadLine() ?? "Desconocido";
                        Console.Write("Materia: ");
                        string? materia = Console.ReadLine() ?? "Sin materia";

                        Console.Write("Nota: ");
                        string? notaInput = Console.ReadLine();
                        double nota = double.TryParse(notaInput, out double tempNota) ? tempNota : 0.0;

                        Estudiante nuevoEstudiante = new Estudiante(nombre, apellido, materia, nota);
                        gestor.AgregarEstudiante(nuevoEstudiante);
                        Console.WriteLine("Estudiante agregado correctamente.");
                    }
                    catch (Exception ex)
                    {
                        Console.WriteLine($"Error al agregar el estudiante: {ex.Message}");
                    }
                    break;

                case "2":
                    try
                    {
                        Console.WriteLine("\n--- Lista de Estudiantes ---");
                        gestor.MostrarEstudiantes();
                    }
                    catch (Exception ex)
                    {
                        Console.WriteLine($"Error al mostrar los estudiantes: {ex.Message}");
                    }
                    break;

                case "3":
                    try
                    {
                        Console.Write("Nombre del estudiante a editar: ");
                        string? nombre = Console.ReadLine() ?? "";
                        Console.Write("Apellido: ");
                        string? apellido = Console.ReadLine() ?? "";
                        Console.Write("Materia: ");
                        string? materia = Console.ReadLine() ?? "";

                        Console.Write("Nueva Nota: ");
                        string? nuevaNotaInput = Console.ReadLine();
                        double nuevaNota = double.TryParse(nuevaNotaInput, out double tempNuevaNota) ? tempNuevaNota : 0.0;

                        gestor.EditarEstudiante(nombre, apellido, materia, nuevaNota);
                    }
                    catch (Exception ex)
                    {
                        Console.WriteLine($"Error al editar el estudiante: {ex.Message}");
                    }
                    break;

                case "4":
                    ejecutar = false;
                    Console.WriteLine("Saliendo del sistema...");
                    break;

                default:
                    Console.WriteLine("Opción inválida. Intente nuevamente.");
                    break;
            }
        }
    }
}
