/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatosDTO;
/**
 *
 * @author panch
 */
public class Datos {
    
    String nombre;
    String ocupacion;
    String identificacion;
    int matricula;
    int dias;
    int total;
    

    public Datos() {
    }

    public Datos(String nombre, int matricula, String ocupacion, String identificacion, int dias, int total) {
        this.nombre = nombre;
        this.matricula = matricula;
        this.ocupacion = ocupacion;
        this.identificacion = identificacion;
        this.dias = dias;
        this.total = total;


    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }
    
    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    

    public String [] getArray(){
        String[] datos = {nombre, String.valueOf(matricula), ocupacion, identificacion, String.valueOf(dias), String.valueOf(total)};
         return datos;
    }
    
}
    
    

