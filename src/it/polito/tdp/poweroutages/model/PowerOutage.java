package it.polito.tdp.poweroutages.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class PowerOutage {
	private int id;
	private Nerc nerc;
	private LocalDateTime outageInizio;
	private LocalDateTime outageFine;
	private int affectedPeople;
	private long outageDurata;
	private int anno;
	
	public PowerOutage(int id, Nerc nerc, LocalDateTime outageInizio, LocalDateTime outageFine, int affectedPeople) {
		super();
		this.id = id;
		this.nerc = nerc;
		this.outageInizio = outageInizio;
		this.outageFine = outageFine;
		this.affectedPeople = affectedPeople;
		
		
		this.anno=outageInizio.getYear();
		
		LocalDateTime tmp = LocalDateTime.from(outageInizio);
		this.outageDurata= tmp.until(outageFine, ChronoUnit.HOURS);

		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Nerc getNerc() {
		return nerc;
	}

	public void setNerc(Nerc nerc) {
		this.nerc = nerc;
	}

	public LocalDateTime getOutageInizio() {
		return outageInizio;
	}

	public void setOutageInizio(LocalDateTime outageInizio) {
		this.outageInizio = outageInizio;
	}

	public LocalDateTime getOutageFine() {
		return outageFine;
	}

	public void setOutageFine(LocalDateTime outageFine) {
		this.outageFine = outageFine;
	}

	public int getAffectedPeople() {
		return affectedPeople;
	}

	public void setAffectedPeople(int affectedPeople) {
		this.affectedPeople = affectedPeople;
	}

	public long getOutageDurata() {
		return outageDurata;
	}

	public void setOutageDurata(long outageDurata) {
		this.outageDurata = outageDurata;
	}

	public int getAnno() {
		return anno;
	}

	public void setAnno(int anno) {
		this.anno = anno;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PowerOutage other = (PowerOutage) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(id);
		return builder.toString();
	}

	
}
