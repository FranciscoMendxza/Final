/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto_agenda;

import ConexionDAO.Conexion;

/**
 *
 * @author panch
 */
public class Proyecto_Agenda {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new Principal().setVisible(true);
        
        Conexion conexion = new Conexion();
        conexion.conecta();
        
        conexion.cerrar();
    }
    
}
