/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aquelarre.java;

import java.sql.CallableStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author ILCE MILENA
 */
public class CUsuarios {

     int codigo;
    String nombreUsuario;
    String aliasUsuario;
    String emailUsuario;
    String contrasenaUsuario;
    String rolUsuario;

    public String getRolUsuario() {
        return rolUsuario;
    }

    public void setRolUsuario(String rolUsuario) {
        this.rolUsuario = rolUsuario;
    }
       
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getAliasUsuario() {
        return aliasUsuario;
    }

    public void setAliasUsuario(String aliasUsuario) {
        this.aliasUsuario = aliasUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getContrasenaUsuario() {
        return contrasenaUsuario;
    }

    public void setContrasenaUsuario(String contrasenaUsuario) {
        this.contrasenaUsuario = contrasenaUsuario;
    }
    
   public void InsertarUsuario(JTextField paramEmail, JTextField paramAlias, JTextField paramNombre, JTextField paramContrasena){
       
       setEmailUsuario(paramEmail.getText());
       setAliasUsuario(paramAlias.getText());
       setNombreUsuario(paramNombre.getText());
       setContrasenaUsuario(paramContrasena.getText());
       
       CConexion objetoConexion = new CConexion();
       String consulta = ("INSERT INTO usuario (email, nombre, alias, contrasena, id_rol) VALUES (?, ?, ?, ?, 2);");
         try{
                CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
                cs.setString(1, getEmailUsuario());
                cs.setString(2, getAliasUsuario());
                cs.setString(3, getNombreUsuario());
                cs.setString(4, getContrasenaUsuario());
                cs.execute();
                
                JOptionPane.showMessageDialog(null,"Se agrego correctamente el usuario");}
            catch(Exception e){
                    JOptionPane.showMessageDialog(null, "No se agrego el usuario correctamente, erro: "+ e.toString());
                    }
              }
   
   public void MostrarUsuarios(JTable paramTablaTotalUsuarios){
   
       CConexion objetoConexion = new CConexion();
       DefaultTableModel modelo = new DefaultTableModel();
       TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(modelo);
       paramTablaTotalUsuarios.setRowSorter(OrdenarTabla);
       String sql = "";
       modelo.addColumn("Id");
       modelo.addColumn("Email");
       modelo.addColumn("Alias");
       modelo.addColumn("Nombre");
       modelo.addColumn("contraseña");
       
       paramTablaTotalUsuarios.setModel(modelo);
       
       sql = "SELECT * FROM usuario;";
       String[] datos = new String[5];
       Statement st;
       
       try{
               st = objetoConexion.estableceConexion().createStatement();
               ResultSet rs = st.executeQuery(sql);
               while(rs.next()){
               datos[0] = rs.getString(1);
               datos[1] = rs.getString(2);
               datos[2] = rs.getString(3);
               datos[3] = rs.getString(4);
               datos[4] = rs.getString(5);
               
               modelo.addRow(datos);
                            
               }
                paramTablaTotalUsuarios.setModel(modelo);
       }
        catch(Exception e){
               JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros, erro: "+ e.toString());
                    }
                
   }
   
   public void SeleccionarUsuarios(JTable paramTablaUsuarios, JTextField paramId, JTextField paramEmail, JTextField paramAlias, JTextField paramNombre, JTextField paramContrasena){
        try{
              int fila = paramTablaUsuarios.getSelectedRow();
              
              if (fila >=0){
              paramId.setText(paramTablaUsuarios.getValueAt(fila, 0).toString());
              paramEmail.setText(paramTablaUsuarios.getValueAt(fila, 1).toString());
              paramAlias.setText(paramTablaUsuarios.getValueAt(fila, 2).toString());
              paramNombre.setText(paramTablaUsuarios.getValueAt(fila, 3).toString());
              paramContrasena.setText(paramTablaUsuarios.getValueAt(fila, 4).toString());
              }
                            
              else{
              JOptionPane.showMessageDialog(null, "Fila no seleccionada");
              }                    
       }
        catch(Exception e){
               JOptionPane.showMessageDialog(null, "Error de seleccion, erro: "+ e.toString());
                    }
   }
   
   public void ModificarUsuarios(JTextField paramCodigo, JTextField paramEmail, JTextField paramAlias, JTextField paramNombre, JTextField paramContrasena){
   setCodigo(Integer.parseInt(paramCodigo.getText()));
   setEmailUsuario(paramEmail.getText());
   setAliasUsuario(paramAlias.getText());
   setNombreUsuario(paramNombre.getText());
   setContrasenaUsuario(paramContrasena.getText());
   
   CConexion objetoConexion = new CConexion();
   String consulta = "update usuario set usuario.email = ?, usuario.nombre = ?, usuario.alias = ?, usuario.contrasena = ?, usuario.id_rol = 2 where usuario.id_usuario = ?;";
   
   try{
             CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
             
             cs.setString(1, getEmailUsuario());
             cs.setString(2, getAliasUsuario());
             cs.setString(3, getNombreUsuario());
             cs.setString(4, getContrasenaUsuario());
             cs.setInt(5, getCodigo());
             
             cs.execute();
              JOptionPane.showMessageDialog(null, "Modificacion Exitosa");
       }
        catch(SQLException e){
               JOptionPane.showMessageDialog(null, "No se modifico, erro: "+ e.toString());
                    }
   }
   
   public void EliminarUsuarios(JTextField paramCodigo){
   
       setCodigo(Integer.parseInt(paramCodigo.getText()));
       CConexion objetoConexion = new CConexion();
       String consulta = "delete from usuario where usuario.id_usuario = ?;";
       
        try{
             CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
             cs.setInt(1, getCodigo());
             cs.execute();
           
             JOptionPane.showMessageDialog(null, "Se elimino correctamente el usuario ");
       }
        catch(SQLException e){
               JOptionPane.showMessageDialog(null, "No se pudo eliminar usuario, erro: "+ e.toString());
                    }
   }
}
