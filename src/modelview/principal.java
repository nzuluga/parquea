/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelview;

//PRIMER COMENTARIO GIT
import view.formularioAutomatico;
import view.formularioBusqueda;
import view.formularioIngreso;
import view.formularioSalida;
import view.formularioMenu;

public class principal {
    static conjuntos parqueadero = new conjuntos(4,3,4);
    static formularioIngreso operaciones = new formularioIngreso(parqueadero);
    static formularioBusqueda busqueda = new formularioBusqueda();
    static formularioSalida salida = new formularioSalida(parqueadero);
    static formularioAutomatico automatico = new formularioAutomatico();
    static formularioMenu menu = new formularioMenu();
    
    
    public static void main(String args[]) {
    //principal op = new principal();
    parqueadero.setVisible(false);
    menu.setVisible(true);
//    formularioIngreso ingreso = new formularioIngreso(parqueadero);
//    ingreso.setVisible(true);
//    
//    formularioSalida salida = new formularioSalida(parqueadero);
//    salida.setVisible(true);
    
    
    
    }
    public static void conjuntos(){
        parqueadero.setVisible(true);
    }
    public static void formularioIngreso(){
        operaciones.setVisible(true);
    }
    public static void formularioBusqueda(){
        busqueda.setVisible(true);
    }
    public static void formularioSalida(){
        salida.setVisible(true);
    }
    public static void formularioAutomatico(){
        automatico.setVisible(true);
    }
    public static void formularioBusquedaDesa(){
        busqueda.setVisible(false);
    }
    public static void formularioSalidaDesa(){
        salida.setVisible(false);
    }
    public static void formularioAutomaticoDesa(){
        automatico.setVisible(false);
    }
    public static void conjuntosdesa(){
        parqueadero.setVisible(false);
    }
    
   
}

    
    

