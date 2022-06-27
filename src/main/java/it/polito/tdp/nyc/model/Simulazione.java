package it.polito.tdp.nyc.model;

import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import it.polito.tdp.nyc.db.NYCDao;
import it.polito.tdp.nyc.model.Evento.EventType;

public class Simulazione {

	private int n; //numero tecnici
	private List<Hotspot> hotspots;
	private List<Hotspot> giaRevisionati;
	private Map<Integer, Integer> tecniciHotspot;
	private Provider provider;
	private double probExtraTime = 0.10;
	private int extraTime = 15;
	private int time = 10;
	private int durataCambio;
	private City firstCity;
	private List<City> cittaDaVisitare;
	private NYCDao dao;
	private int tecniciOccupati = 0;
	private int giornataLavorativa = 60*24;
	
	private PriorityQueue<Evento> queue;
	
	public Simulazione(int n, City firstCity, Provider provider, List<City> cities) {
		this.firstCity = firstCity;
		this.n = n;
		this.cittaDaVisitare = cities;
		this.provider = provider;
		this.dao = new NYCDao();
		hotspots = getHotspot(provider, firstCity);
	}

	private List<Hotspot> getHotspot(Provider provider, City city){
		return this.dao.getAllHotspotProviderCity(provider, firstCity);
	}
	
	private void run() {
		for(int i = 0; i< n; i++) {
			tecniciHotspot.put(i, 0);
		}
		this.cittaDaVisitare.remove(this.firstCity);
		this.queue = new PriorityQueue<>();
		int i = 0 ;
		while(this.tecniciOccupati < this.n && this.hotspots.size() > 0) {
			queue.add(new Evento(0, i, EventType.INIZIO_HS));
			this.tecniciOccupati--;
			this.hotspots.remove(this.hotspots.size()-1);
			i++;
		}
	}
}
