
package Modelo;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LibroDAO {
    Conexion conectar= new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public List listar(){
        List<Libro>datos=new ArrayList<>();
        String sql="SELECT * FROM libros";
        try {
            con=conectar.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                Libro l= new Libro();
                l.setISBN(rs.getInt(1));
                l.setTitulo(rs.getString(2));
                l.setAutor(rs.getString(3));
                l.setCalificacion(rs.getString(4));
                datos.add(l);
            }
        } catch (Exception e) {
        }
        return datos;
    }
    
    public int Agregar(Libro l){
        String sql="INSERT INTO libros(Titulo, Autor, Calificacion) VALUES(?,?,?)";
        try {
            con=conectar.getConnection();
            ps=con.prepareStatement(sql);
            ps.setString(1, l.getTitulo());
            ps.setString(2, l.getAutor());
            ps.setString(3, l.getCalificacion());
            //ps.setInt(1,l.getISBN());
            ps.executeUpdate();
        } catch (Exception e) {
        }
        return 1;
    }
    
    public int Actualizar(Libro l){
        String sql="UPDATE libros SET Titulo=?,Autor=?,Calificacion=? WHERE ISBN =?";
        int r=0;
        try {
            con=conectar.getConnection();
            ps=con.prepareStatement(sql);
            ps.setString(1, l.getTitulo());
            ps.setString(2, l.getAutor());
            ps.setString(3, l.getCalificacion());
            ps.setInt(4,l.getISBN());
            r=ps.executeUpdate();
            if(r==1){
                return 1;
            }else{
                return 0;
            }
        } catch (Exception e) {
        }
        return r;
    }
    
    public void eliminar(int ISBN){
        String sql="DELETE FROM libros WHERE ISBN="+ISBN;
        try {
            con=conectar.getConnection();
            ps=con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
}
