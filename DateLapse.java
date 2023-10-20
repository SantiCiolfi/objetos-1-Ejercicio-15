package Objetos1.Ejercicio_15;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DateLapse {
	
	private LocalDate from;
	private long sizeInDays;
	
	public DateLapse(LocalDate from, LocalDate to) {
		this.from=from;
		this.sizeInDays=ChronoUnit.DAYS.between(this.getFrom(), to);
	}
	
	public LocalDate getFrom() {
		return this.from;
	}
	
	public LocalDate getTo() {
		return this.from.plusDays(sizeInDays);
	}
	
	public int sizeInDays() {
		int aux = Math.toIntExact(this.sizeInDays);;
		return aux; 
	}
	
	public boolean includesDate(LocalDate other) {
		return other.isEqual(getFrom()) || other.isBefore(getTo()) && other.isAfter(from) || other.isEqual(getTo());
	}
	
	public boolean overlaps(DateLapse anotherDateLapse) {
		boolean superpuesto = !this.getTo().isBefore(anotherDateLapse.getFrom()) && !this.getFrom().isAfter(anotherDateLapse.getTo())
		           || this.getTo().equals(anotherDateLapse.getFrom()) || this.getFrom().equals(anotherDateLapse.getTo());
	    return superpuesto;
	}
	
}
