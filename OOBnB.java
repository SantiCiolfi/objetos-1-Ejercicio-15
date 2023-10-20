package Objetos1.Ejercicio_15;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OOBnB {
	
	private List<Propiedad> propiedades;
	private List<Usuario> usuarios;
	
	public OOBnB() {
		this.propiedades = new ArrayList<>();
		this.usuarios = new ArrayList<>();
	}
	
	public Usuario registrarUsuario(String nombre, String direccion, int DNI) {
		Usuario user;
		user = new Usuario(nombre, direccion, DNI);
		this.usuarios.add(user);
		return user;
	}
	
	public Propiedad registrarUnaPropiedadEnAlquiler(String nombre, String descripcion, double precioPorNoche, String direccion, Usuario propietario) {
		Propiedad propiedad;
		propiedad = new Propiedad (nombre, descripcion, precioPorNoche, direccion, propietario);
		this.propiedades.add(propiedad);
		propietario.agregarPropiedadParaAlquilar(propiedad);
		return propiedad;
	}
	
	public List<Propiedad> buscarPropiedadesDisponiblesEnUnPeriodo(LocalDate fechaDeInicio, LocalDate fechaDeFin){
		DateLapse lapso = new DateLapse (fechaDeInicio, fechaDeFin);
		return this.propiedades.stream()
				.filter(propiedad -> propiedad.disponibleEnPerido(lapso))
				.collect(Collectors.toList());
	}
	
	public boolean realizarReserva(Propiedad propiedad, DateLapse lapso, Usuario usuario) {
		boolean libre = false;
		if(propiedad.disponibleEnPerido(lapso)) {
			libre = true;
			propiedad.reservar(lapso);
			usuario.reservar(propiedad);
		}
		return libre;
	}
	
	public double calcularPrecioDeUnaReserva(DateLapse reserva) {
		return this.propiedades.stream()
				.mapToDouble(propiedad -> propiedad.calcularPrecioReserva(reserva))
				.sum();
	}
	
	public List<Propiedad> reservasDeUnUsuario(Usuario user){
		return user.reservasDelUsuario();
	}
	
}
