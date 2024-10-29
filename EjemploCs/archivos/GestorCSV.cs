using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;

public class GestorCSV
{
    private string rutaArchivo;

    public GestorCSV(string ruta)
    {
        rutaArchivo = ruta;

        // Crear el archivo si no existe
        try
        {
            if (!File.Exists(rutaArchivo))
            {
                using (StreamWriter writer = new StreamWriter(rutaArchivo, true))
                {
                    writer.WriteLine("Nombre,Apellido,Materia,Nota"); // Encabezados
                }
            }
        }
        catch (Exception ex)
        {
            Console.WriteLine($"Error al crear o verificar el archivo: {ex.Message}");
        }
    }

    // Agregar un nuevo estudiante al archivo
    public void AgregarEstudiante(Estudiante estudiante)
    {
        try
        {
            using (StreamWriter writer = new StreamWriter(rutaArchivo, true))
            {
                writer.WriteLine(estudiante.ToString());
            }
            Console.WriteLine("Estudiante agregado correctamente.");
        }
        catch (Exception ex)
        {
            Console.WriteLine($"Error al agregar estudiante: {ex.Message}");
        }
    }

    // Leer todos los estudiantes del archivo
    public List<Estudiante> LeerEstudiantes()
    {
        List<Estudiante> estudiantes = new List<Estudiante>();

        try
        {
            using (StreamReader reader = new StreamReader(rutaArchivo))
            {
                reader.ReadLine(); // Omitir encabezado
                string? linea;
                while ((linea = reader.ReadLine()) != null)
                {
                    string[] campos = linea.Split(',');
                    if (campos.Length == 4 && double.TryParse(campos[3], out double nota))
                    {
                        Estudiante estudiante = new Estudiante(campos[0], campos[1], campos[2], nota);
                        estudiantes.Add(estudiante);
                    }
                    else
                    {
                        Console.WriteLine("Error en el formato de línea del archivo.");
                    }
                }
            }
        }
        catch (Exception ex)
        {
            Console.WriteLine($"Error al leer estudiantes: {ex.Message}");
        }

        return estudiantes;
    }

    // Mostrar estudiantes en consola
    public void MostrarEstudiantes()
    {
        try
        {
            var estudiantes = LeerEstudiantes();
            if (estudiantes.Count == 0)
            {
                Console.WriteLine("No hay estudiantes registrados.");
            }
            else
            {
                foreach (var estudiante in estudiantes)
                {
                    Console.WriteLine($"{estudiante.Nombre} {estudiante.Apellido} - {estudiante.Materia}: {estudiante.Nota}");
                }
            }
        }
        catch (Exception ex)
        {
            Console.WriteLine($"Error al mostrar estudiantes: {ex.Message}");
        }
    }

    // Editar la nota de un estudiante
    public void EditarEstudiante(string nombre, string apellido, string materia, double nuevaNota)
    {
        try
        {
            var estudiantes = LeerEstudiantes();
            bool encontrado = false;

            // Buscar el estudiante y actualizar la nota
            foreach (var estudiante in estudiantes)
            {
                if (estudiante.Nombre == nombre && estudiante.Apellido == apellido && estudiante.Materia == materia)
                {
                    estudiante.Nota = nuevaNota;
                    encontrado = true;
                    break;
                }
            }

            if (encontrado)
            {
                // Reescribir el archivo con los datos actualizados
                using (StreamWriter writer = new StreamWriter(rutaArchivo))
                {
                    writer.WriteLine("Nombre,Apellido,Materia,Nota"); // Encabezados
                    foreach (var est in estudiantes)
                    {
                        writer.WriteLine(est.ToString());
                    }
                }

                Console.WriteLine("Estudiante actualizado correctamente.");
            }
            else
            {
                Console.WriteLine("Estudiante no encontrado.");
            }
        }
        catch (Exception ex)
        {
            Console.WriteLine($"Error al editar estudiante: {ex.Message}");
        }
    }
}
