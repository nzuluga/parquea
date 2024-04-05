/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelview;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Time;
import java.time.Duration;
import java.time.LocalTime;
import model.Parquea;

/**
 *
 * @author David
 */

public class operaciones extends Vehiculo{
    
	 
	
    public operaciones(String placa) {
        super(placa);
        
    }

    @Override
    public void setplaca(String placa) {
        this.placa= placa; 
    }

    @Override
    public String getplaca() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        
        return placa;
    }

    @Override
    public void setfecha(java.util.Date fecha) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        this.fecha= fecha;
    }

    @Override
    public java.util.Date getfecha() {
        
        return fecha;
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void sethoraIngreso(Time horaIngreso) {
        this.horaIngreso= horaIngreso;

        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Time gethoraIngreso() {
        
        return horaIngreso;
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void sethoraSalida(Time horaSalida) {
        
        this.horaSalida= horaSalida;
//throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Time gethoraSalida() {
        return horaSalida;
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void settipoVechiculo(String tipoVehiculo) {
        this.tipoVehiculo= tipoVehiculo;
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String gettipoVechiculo() {
        return tipoVehiculo;
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void settarifa(Integer tarifa) {
        this.tarifa= tarifa;

        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Integer gettarifa() {
        
        return tarifa;

        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    @Override
    public void settiempo(Double tiempo) {
        this.tiempo= tiempo;
    }

    @Override
    public Double gettiempo() {
        
        return tiempo;
    }

    @Override
    public void setpago(Double pago) {
        this.pago = pago;
    }

    @Override
    public Double getpago() {
        return pago;
    }
    public void guardar(Vehiculo vehiculo) throws SQLException{
        Parquea db = new Parquea();
        db.guardar(vehiculo);
    
    }
    public ResultSet Recuperar() throws SQLException{
        Parquea db = new Parquea();
        ResultSet vehiculos = db.recuperarDatos();
        return vehiculos;
    }
    public Vehiculo consulta(String placa) throws SQLException{
        Parquea buscar = new Parquea();
         
        ResultSet vehiculo = buscar.consulta(placa);
        operaciones vehiculoaSalir = new operaciones("");
        if(vehiculo.next()){
            vehiculoaSalir.setplaca(vehiculo.getString("placa"));
            vehiculoaSalir.setfecha(vehiculo.getDate("fecha"));
            vehiculoaSalir.sethoraIngreso(vehiculo.getTime("horaIngreso"));
            vehiculoaSalir.sethoraSalida(vehiculo.getTime("horaSalida"));
            vehiculoaSalir.settipoVechiculo(vehiculo.getString("tipoVehiculo"));
            vehiculoaSalir.settarifa(vehiculo.getInt("tarifa"));
            vehiculoaSalir.settiempo(vehiculo.getDouble("tiempo"));
            vehiculoaSalir.setpago(vehiculo.getDouble("pago"));
        
        }
            
        return vehiculoaSalir;
        
    }

   
    public void Cobro (Vehiculo vehiculoaSalir){
        
        vehiculoaSalir.sethoraSalida(new Time(System.currentTimeMillis()));
        
        LocalTime horaIngreso = vehiculoaSalir.gethoraIngreso().toLocalTime();
        LocalTime horaSalida = vehiculoaSalir.gethoraSalida().toLocalTime();
        
        Duration tiemp = Duration.between(horaIngreso,horaSalida);
        double tiempo_en_minutos = (double)tiemp.toMinutes();
        vehiculoaSalir.settiempo(tiempo_en_minutos);
        
        vehiculoaSalir.setpago(tiempo_en_minutos*vehiculoaSalir.gettarifa());
        
        
    }
    public void modificar (Vehiculo vehiculo,String placaCambio) throws SQLException{
        Parquea db = new Parquea();
        db.modificar(vehiculo,placaCambio);
    
    }
    public void Borrar (String vehiculo) throws SQLException{
        Parquea db = new Parquea();
        db.borrar(vehiculo);
    
    }

    

    public class Queue<T> { //Queue class implemented using a Circular array 

    	T[] queue;//Queue values constructor
    	int size;
    	int head;
    	int tail;
    	int length;

    	public Queue(int size) {//Queue initializer
    		if (size > 0) {
    			this.size = size;

    			queue = (T[]) new Object[size]; // creating array of generic type
    		} else
    			System.out.println("Error: Invalid Size");//Must be bigger than 0
    	}

    	public void enQueue(T data) {
    		if (!isFull()) {
    			queue[tail] = data;
    			tail = (tail + 1) % size;
    			length++;
    		} else
    			System.out.println("enQueue(" + data + ")-Error: Queue is Full");
    	}

    	public T deQueue() {

    		T data = queue[head];

    		if (!isEmpty()) {
    			head = (head + 1) % size;
    			length--;
    		} else {
    			System.out.println("deQueue-Error: Queue is Empty");
    		}
    		return data;

    	}

    	public T peek() {
    		if (!isEmpty()) {

    			T data = queue[head];
    			return data;
    		} else
    			return null;
    	}

    	public int getLength() {//Queue current length
    		return length;
    	}

    	public boolean isEmpty() {
    		return getLength() == 0;
    	}

    	public boolean isFull() {
    		return getLength() == size;
    	}

    	public void print() {
    		for (int i = 0; i < length; i++) {
    			System.out.print(queue[(head + i) % size] + " ");
    		}
    		System.out.println();
    	}
    }


    
    
    
    
    
}
