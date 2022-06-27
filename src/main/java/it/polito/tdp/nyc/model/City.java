package it.polito.tdp.nyc.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.javadocmd.simplelatlng.LatLng;

public class City {

	private String name;
	private LatLng coord;
	private List<CittaVicina> vicini;

	public City(String name, double lat, double longit) {
		super();
		this.name = name;
		coord = new LatLng(lat, longit);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LatLng getCoord() {
		return coord;
	}

	public void setCoord(LatLng coord) {
		this.coord = coord;
	}


	public List<CittaVicina> getVicini() {
		return vicini;
	}

	public void addVicino(CittaVicina citta) {
		if(vicini == null) {
			vicini = new ArrayList<>();
			vicini.add(citta);
		}else {
			vicini.add(citta);
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		City other = (City) obj;
		return Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return name;
	}


	
	
	
}
