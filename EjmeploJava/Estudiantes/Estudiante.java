public class Estudiante {
    private String nombre;
    private String apellido;
    private String materia;
    private double nota;

    public Estudiante(String nombre, String apellido, String materia, double nota) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.materia = materia;
        this.nota = nota;
    }

    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getMateria() { return materia; }
    public double getNota() { return nota; }
    public void setNota(double nota) { this.nota = nota; }

    @Override
    public String toString() {
        return nombre + "," + apellido + "," + materia + "," + nota;
    }
}
