/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelview;


import view.formularioAutomatico;
import view.formularioBusqueda;
import view.formularioIngreso;
import view.formularioSalida;
import view.formularioMenu;

public class principal {
	
    static formularioIngreso operaciones = new formularioIngreso();
    static formularioBusqueda busqueda = new formularioBusqueda();
    static formularioSalida salida = new formularioSalida();
    static formularioAutomatico automatico = new formularioAutomatico();
    static formularioMenu menu = new formularioMenu();
    public static void main(String args[]) {
    //principal op = new principal();
    menu.setVisible(true);
    
//    long t0 = System.nanoTime();
//    for (int i = 0; i < Holder.q.size; i++) {
//		 String placa = generarPlaca(i);
//		 operaciones op = new operaciones(placa);
//		 
//		Holder.q.enQueue(op);
//	}
//    long tf = System.nanoTime();
//    System.out.println(tf-t0);
//}
    
    for (int i = 0; i <30000000; i++) {
		 String placa = generarPlaca(i);
		 operaciones op = new operaciones(placa);
		 
		Holder.q.enQueue(op);
	}
  System.out.println("Done");
}
    
    


public static String generarPlaca(int numeroPlaca) {
   // Asegurar que el número de placa esté dentro del rango permitido
   numeroPlaca = Math.max(0, Math.min(175759999, numeroPlaca)); // 175,759,999 es el mayor número para ZZZ999

   // Obtener las letras de la placa
   char letra1 = (char) ('A' + (numeroPlaca / (26 * 26 * 1000)) % 26);
   char letra2 = (char) ('A' + (numeroPlaca / (26 * 1000)) % 26);
   char letra3 = (char) ('A' + (numeroPlaca / 1000) % 26);

   // Obtener los dígitos del número de serie
   int numeroSerie = numeroPlaca % 1000;

   // Construir y devolver la placa completa
   return String.format("%c%c%c%03d", letra1, letra2, letra3, numeroSerie);
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
    
   
}

    
    

