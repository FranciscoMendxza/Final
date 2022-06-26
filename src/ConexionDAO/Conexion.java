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
            PreparedStatement ps = conexion.prepareStatement("insert into datos (nombre, categoria, ocupacion, salario, bono) values (?, ?, ?, ?, ?)");
            ps.setString(1, datos.getNombre());
            ps.setString(2, datos.getCategoria());
            ps.setString(3, datos.getOcupacion());
            ps.setInt(4, datos.getSalario());
            ps.setInt(5, datos.getBono());
            
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
                datos = new Datos(rs.getString("nombre"),rs.getString("categoria"), rs.getString("ocupacion"), rs.getInt("salario"), rs.getInt("bono"),rs.getInt("matricula"));
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
            PreparedStatement ps = conexion.prepareCall("update datos set nombre = ?, categoria = ?, ocupacion = ?, salario = ?, bono = ? where matricula = ?");
            
            ps.setString(1, datos.getNombre());
            ps.setString(2, datos.getCategoria());
            ps.setString(3, datos.getOcupacion());
            ps.setInt(4, datos.getSalario());
            ps.setInt(5, datos.getBono());
            
            ps.setInt(6, datos.getMatricula());
            
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
            PreparedStatement ps = conexion.prepareStatement("delete from Datos where matricula = ?");
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
