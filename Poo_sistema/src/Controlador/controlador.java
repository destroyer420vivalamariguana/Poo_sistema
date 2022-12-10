
package Controlador;

import Modelo.Libro;
import Modelo.LibroDAO;
import Vista.vista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class controlador implements ActionListener{
    
    LibroDAO dao=new LibroDAO();
    Libro l=new Libro();
    vista vista=new vista();
    DefaultTableModel modelo= new DefaultTableModel();
    
    public controlador(vista v){
        this.vista=v;
        this.vista.btnListar.addActionListener(this);
        this.vista.btnGuardar.addActionListener(this);
        this.vista.btnEditar.addActionListener(this);
        this.vista.btnOk.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==vista.btnListar){
            limpiarTabla();
            listar(vista.tabla);
        }
        if(e.getSource()==vista.btnGuardar){
            agregar();
            limpiarTabla();
            listar(vista.tabla);
        }
        if(e.getSource()==vista.btnEditar){
            int fila=vista.tabla.getSelectedRow();
            if(fila==-1){
                JOptionPane.showMessageDialog(vista, "Seleccione una fila");
            }else{
                int ISBN=Integer.parseInt((String)vista.tabla.getValueAt(fila, 0).toString());
                String Titulo=(String)vista.tabla.getValueAt(fila, 1);
                String Autor=(String)vista.tabla.getValueAt(fila, 2);
                String Calificacion=(String)vista.tabla.getValueAt(fila, 3);
                vista.txtIsbn.setText(""+ISBN);
                vista.txtTitulo.setText(Titulo);
                vista.txtAutor.setText(Autor);
                vista.txtCalificacion.setText(Calificacion);
            }
        }
        if(e.getSource()==vista.btnOk){
            actualizar();
            limpiarTabla();
            listar(vista.tabla);
        }
        if(e.getSource()==vista.btnEliminar){
            eliminar();
            limpiarTabla();
            listar(vista.tabla);
        }
    }
    
    public void eliminar(){
        int fila = vista.tabla.getSelectedColumn();
            if(fila==-1){
                JOptionPane.showMessageDialog(vista, "Selecione para eliminar");
            }else{
                int ISBN=Integer.parseInt((String)vista.tabla.getValueAt(fila, 0).toString());
                dao.eliminar(ISBN);
                JOptionPane.showMessageDialog(vista, "Se eliminó conexito!!");
            }
    }
    
    public void actualizar(){
        int ISBN=Integer.parseInt(vista.txtIsbn.getText());
        String Titutlo=vista.txtTitulo.getText();
        String Autor=vista.txtAutor.getText();
        String Calificacion=vista.txtCalificacion.getText();
        l.setISBN(ISBN);
        l.setTitulo(Titutlo);
        l.setAutor(Autor);
        l.setCalificacion(Calificacion);
        int r=dao.Actualizar(l);
        if(r==1){
            JOptionPane.showMessageDialog(vista, "Se actualizó con exito!!");
        }else{
            JOptionPane.showMessageDialog(vista,"Error");
        }
    }
    
    public void agregar(){
        String Titutlo=vista.txtTitulo.getText();
        String Autor=vista.txtAutor.getText();
        String Calificacion=vista.txtCalificacion.getText();
        l.setTitulo(Titutlo);
        l.setAutor(Autor);
        l.setCalificacion(Calificacion);
        int r=dao.Agregar(l);
        if(r==1){
            JOptionPane.showMessageDialog(vista, "Se agrego con exito!!");
        }else{
            JOptionPane.showMessageDialog(vista,"Error");
        }
        
    }
    public void listar(JTable tabla){
        modelo=(DefaultTableModel)tabla.getModel();
        List<Libro>lista=dao.listar();
        Object[] ob=new Object[4];
        for (int i = 0; i < lista.size(); i++) {
            ob[0]=lista.get(i).getISBN();
            ob[1]=lista.get(i).getTitulo();
            ob[2]=lista.get(i).getAutor();
            ob[3]=lista.get(i).getCalificacion();
            modelo.addRow(ob);
        }
        vista.tabla.setModel(modelo);
    }
    
    void limpiarTabla(){
        for (int i = 0; i < vista.tabla.getRowCount(); i++) {
            modelo.removeRow(i);
            i=i-1;
        }
    }
}
