package it.polito.tdp.nyc.model;

public class Evento {

	private EventType type;
	private int idTecnico;
	private int tempo;

	public enum EventType {
		INIZIO_HS, // tecnico inizia inizia a lavorare su un h.s.
		FINE_HS, // tecnico termina il lavoro su un h.s.
		NUOVO_QUARTIERE, // la squadra si sposta in un nuovo quartiere
	}

	public Evento(int tempo, int idTecnico, EventType type) {
		super();
		this.type = type;
		this.tempo = tempo;
		this.idTecnico = idTecnico;
	}

	public EventType getType() {
		return type;
	}

	public void setType(EventType type) {
		this.type = type;
	}

	public int getIdTecnico() {
		return idTecnico;
	}

	public void setIdTecnico(int idTecnico) {
		this.idTecnico = idTecnico;
	}

	public int getDurata() {
		return tempo;
	}

	public void setDurata(int durata) {
		this.tempo = durata;
	}

}
