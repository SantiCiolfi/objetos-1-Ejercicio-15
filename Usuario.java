package Objetos1.Ejercicio_15;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
	
	private String nombre;
	private int DNI;
	private String direccion;
	private int propiedadesAlquiladas;
	private List<Propiedad> propiedadesEnAlquiler;
	private List<Propiedad> propiedadesReservadas;
	
	public Usuario(String nombre, String direccion, int DNI) {
		this.direccion=direccion;
		this.DNI=DNI;
		this.nombre=nombre;
		this.propiedadesAlquiladas=0;
		this.propiedadesEnAlquiler=new ArrayList<>();
		this.propiedadesReservadas=new ArrayList<>();
	}
	
	public void agregarPropiedadParaAlquilar(Propiedad propiedad) {
		this.propiedadesEnAlquiler.add(propiedad);
	}
	
	public void reservar(Propiedad propiedadReservada) {
		this.propiedadesReservadas.add(propiedadReservada);
		this.propiedadesAlquiladas++;
	}
	
	public List<Propiedad> reservasDelUsuario(){
		return this.propiedadesReservadas;
	}
	
	public double calcularIngresosPropietario(DateLapse lapso) {
		return this.propiedadesEnAlquiler.stream()
				.mapToDouble(propiedades -> propiedades.calcularPrecioLapsoDeTiempo(lapso))
				.sum();
	}
	
}
