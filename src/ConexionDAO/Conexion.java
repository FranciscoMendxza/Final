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
        String password="";
        String url="jdbc:mysql://localhost:3306/nominas?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
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
            PreparedStatement ps = conexion.prepareStatement("insert into prueba (nombre, matricula, ocupacion, identificacion, dias, total) values (?, ?, ?, ?, ?, ?)");
            ps.setString(1, datos.getNombre());
            ps.setInt(2, datos.getMatricula());
            ps.setString(3, datos.getOcupacion());
            ps.setString(4, datos.getIdentificacion());
            ps.setInt(5, datos.getDias());
            ps.setInt(6, datos.getTotal());
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
            PreparedStatement ps = conexion.prepareStatement("select * from prueba");
            ResultSet rs = ps.executeQuery();
            Datos datos = new Datos();
            
            while(rs.next()){
                datos = new Datos(rs.getString("nombre"),rs.getInt("matricula"), rs.getString("ocupacion"), rs.getString("identificacion"), rs.getInt("dias"), rs.getInt("total"));
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
            PreparedStatement ps = conexion.prepareCall("update prueba set nombre = ?, ocupacion = ?, identificacion = ?, dias = ?, total = ? where matricula = ?");
            
            ps.setString(1, datos.getNombre());
            ps.setInt(2, datos.getMatricula());
            ps.setString(3, datos.getOcupacion());
            ps.setString(4, datos.getIdentificacion());
            ps.setInt(5, datos.getDias());
            ps.setInt(6, datos.getTotal());
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
            PreparedStatement ps = conexion.prepareStatement("delete from prueba where matricula = ?");
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
