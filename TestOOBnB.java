package Objetos1.Ejercicio_15;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestOOBnB {
	
	OOBnB sis;
	Usuario user1;
	Usuario user2;
	Propiedad prop1;
	Propiedad prop2;
	DateLapse reser1;
	DateLapse reser2;
	DateLapse reser3;
	DateLapse reser4;
	
	@BeforeEach
	public void  setUp() {
		sis = new OOBnB();
		
		user1 = new Usuario("pepe1", "calle 1", 111);
		user2 = new Usuario("pepe2", "calle 2", 222);
		
		prop1 = new Propiedad ("casita 1", "grande", 10000, "calle 1", user1);
		prop2 = new Propiedad ("casita 2", "chica", 1000, "calle 2", user2);
		
		reser1 = new DateLapse(LocalDate.of(2023, 10, 1), LocalDate.of(2023, 10, 4));
		reser2 = new DateLapse(LocalDate.of(2023, 10, 5), LocalDate.of(2023, 10, 8));
		reser3 = new DateLapse(LocalDate.of(2023, 10, 8), LocalDate.of(2023, 10, 12));
		reser4 = new DateLapse(LocalDate.of(2023, 9, 30), LocalDate.of(2023, 10, 1));
	}
	
	@Test
	public void testResgistrarUser () {
		Usuario userTest = sis.registrarUsuario("pepe1", "calle 1", 111);
		assertTrue(sis.reservasDeUnUsuario(userTest).isEmpty());
	}
	
	@Test
	public void testRegistrarUnaPropiedadEnAlquiler () {
		Propiedad propTest = sis.registrarUnaPropiedadEnAlquiler("casita 1", "grande", 10000, "calle 1", user1);
		sis.realizarReserva(propTest, reser1, user1);
		assertFalse(sis.reservasDeUnUsuario(user1).isEmpty());
	}
	
	@Test
	public void testBuscarPropiedadesDisponiblesEnUnPeriodo() {
		assertTrue(sis.buscarPropiedadesDisponiblesEnUnPeriodo(LocalDate.of(2023, 10, 8), LocalDate.of(2023, 10, 12)).isEmpty());
		prop1.reservar(reser1);
		prop1.reservar(reser2);
		sis.registrarUnaPropiedadEnAlquiler("casita 1", "grande", 10000, "calle 1", user1);
		assertTrue(sis.buscarPropiedadesDisponiblesEnUnPeriodo(LocalDate.of(2023, 10, 7), LocalDate.of(2023, 10, 12)).isEmpty());
		assertTrue(sis.buscarPropiedadesDisponiblesEnUnPeriodo(LocalDate.of(2023, 9, 30), LocalDate.of(2023, 10, 1)).isEmpty());
		assertFalse(sis.buscarPropiedadesDisponiblesEnUnPeriodo(LocalDate.of(2023, 10, 9), LocalDate.of(2023, 10, 12)).isEmpty());
	}
	
	@Test
	public void testRealizarReserva () {
		sis.registrarUnaPropiedadEnAlquiler("casita 1", "grande", 10000, "calle 1", user1);
		sis.registrarUnaPropiedadEnAlquiler("casita 2", "chica", 1000, "calle 2", user2);
		assertTrue(sis.realizarReserva(prop1, reser1, user1));
		assertTrue(sis.realizarReserva(prop1, reser2, user2));
		assertFalse(sis.realizarReserva(prop1, reser3, user1));
	}
	
	@Test
	public void testCalcularPrecioDeUnaReserva () {
		Propiedad propTest = sis.registrarUnaPropiedadEnAlquiler("casita 1", "grande", 10000, "calle 1", user1);
		sis.realizarReserva(propTest, reser1, user1);
		assertEquals(30000,sis.calcularPrecioDeUnaReserva(reser1));
	}
	
	@Test
	public void testReservasDeUnUsuario () {
		Usuario userTest = sis.registrarUsuario("pepe1", "calle 1", 111);
		Propiedad propTest = sis.registrarUnaPropiedadEnAlquiler("casita 1", "grande", 10000, "calle 1", user1);
		sis.realizarReserva(propTest, reser1, userTest);
		assertFalse(sis.reservasDeUnUsuario(userTest).isEmpty());
	}
}
