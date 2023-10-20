package Objetos1.Ejercicio_15;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Propiedad {
	
	private String nombre;
	private String descripcion;
	private String direccion;
	private Usuario propietario;
	private double precioPorNoche;
	private List<DateLapse> reservas;
	
	public Propiedad(String nombre, String descripcion, double precioPorNoche, String direccion, Usuario propietario) {
		this.descripcion=descripcion;
		this.direccion=direccion;
		this.nombre=nombre;
		this.precioPorNoche=precioPorNoche;
		this.propietario=propietario;
		this.reservas = new ArrayList<>();
	}
	
	public boolean disponibleEnPerido(DateLapse lapso) {
		return this.reservas.stream()
				.noneMatch(reserva -> reserva.overlaps(lapso));
	}
	
	public void reservar(DateLapse lapso) {
		this.reservas.add(lapso);
	}
	
	public double calcularPrecioReserva(DateLapse reserva) {
		if(this.reservas.contains(reserva)) {
			return reserva.sizeInDays() * this.precioPorNoche;
		}
		return 0;
	}
	
	public void eliminarReservas(DateLapse reserva) {
		if(this.reservas.contains(reserva)) {
			if(LocalDate.now().isAfter(reserva.getTo())) {
				this.reservas.remove(reserva);
			}
		}
	}
	
	public double calcularPrecioLapsoDeTiempo(DateLapse lapso) {
		return this.reservas.stream().filter(reserva -> !reserva.overlaps(lapso)).mapToInt(reserva -> reserva.sizeInDays()).sum() * this.precioPorNoche;
	}
	
}
