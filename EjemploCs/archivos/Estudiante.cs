public class Estudiante
{
    public string Nombre { get; set; }
    public string Apellido { get; set; }
    public string Materia { get; set; }
    public double Nota { get; set; }

    public Estudiante(string nombre, string apellido, string materia, double nota)
    {
        Nombre = nombre;
        Apellido = apellido;
        Materia = materia;
        Nota = nota;
    }

    public override string ToString()
    {
        return $"{Nombre},{Apellido},{Materia},{Nota}";
    }
}
