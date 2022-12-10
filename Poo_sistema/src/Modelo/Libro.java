
package Modelo;


public class Libro {
    int ISBN;
    String Titulo;
    String Autor;
    String Calificacion;

    public Libro() {
    }

    public Libro(int ISBN, String Titulo, String Autor, String Calificacion) {
        this.ISBN = ISBN;
        this.Titulo = Titulo;
        this.Autor = Autor;
        this.Calificacion = Calificacion;
    }

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String Titulo) {
        this.Titulo = Titulo;
    }

    public String getAutor() {
        return Autor;
    }

    public void setAutor(String Autor) {
        this.Autor = Autor;
    }

    public String getCalificacion() {
        return Calificacion;
    }

    public void setCalificacion(String Calificacion) {
        this.Calificacion = Calificacion;
    }
    
}
