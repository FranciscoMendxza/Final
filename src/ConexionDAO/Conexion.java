/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ConexionDAO;

import DatosDTO.Datos;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author panch
 */
public class Conexion {
    
    Connection conexion;    
    List <Datos> listaDatos = new ArrayList <Datos>();
    
    public void conecta(){
        
        String user="root";
        String password="12345";
        String url="jdbc:mysql://localhost:3306/agenda?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        try{
           Class.forName("com.mysql.cj.jdbc.Driver");
           conexion = DriverManager.getConnection(url,user,password);
        } catch (ClassNotFoundException | SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public void cerrar(){
        try {
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean inserta(Datos datos){
        boolean estado = true;
        
        try{
            conecta();
            PreparedStatement ps = conexion.prepareStatement("insert into datos (nombre, telefono, direccion, celular) values (?, ?, ?, ?)");
            ps.setString(1, datos.getNombre());
            ps.setInt(2, datos.getTelefono());
            ps.setString(3, datos.getDireccion());
            ps.setInt(4, datos.getCelular());
            
            ps.execute();
            
        }catch(SQLException ex) {
            estado = false;
        }finally{
            cerrar();
        }
        
        return estado;
    }
    
    
    public boolean consultartodos(){
        boolean estado = true;
        try{
            
            conecta();
            PreparedStatement ps = conexion.prepareStatement("select * from Datos");
            ResultSet rs = ps.executeQuery();
            Datos datos;
            
            while(rs.next()){
                datos = new Datos(rs.getString("nombre"),rs.getInt("telefono"), rs.getString("direccion"), rs.getInt("celular"), rs.getInt("id"));
                //datos.setNombre(rs.getString("nombre"));
                listaDatos.add(datos);
            }
            
        }catch(SQLException ex){
            ex.printStackTrace();
            estado = false;
        }finally{
            cerrar();
        }
        return estado;
    }
   
    
    public boolean actualizar(Datos datos){
        boolean estado = true;
        
        try{
            conecta();
            PreparedStatement ps = conexion.prepareCall("update datos set nombre = ?, telefono = ?, direccion = ?, celular = ? where id = ?");
            
            ps.setString(1, datos.getNombre());
            ps.setInt(2, datos.getTelefono());
            ps.setString(3, datos.getDireccion());
            ps.setInt(4, datos.getCelular());
            
            ps.setInt(5, datos.getId());
            
            ps.executeUpdate();
            
        }catch(SQLException ex) {
            ex.printStackTrace();
            estado = false;
        }finally{
            cerrar();
        }
        
        return estado;
    }
    
    public boolean eliminar(int id){
        boolean estado = true;
        try{
            conecta();
            PreparedStatement ps = conexion.prepareStatement("delete from Datos where id = ?");
            ps.setInt(1, id);
            ps.execute();            
        }catch(SQLException ex){
            ex.printStackTrace();
            estado = false;
        }finally{
            cerrar();
        }
        return estado;
    }
    
    public List<Datos> getListaDatos(){
        return listaDatos;
    }
    
}
