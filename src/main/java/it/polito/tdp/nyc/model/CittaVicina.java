package it.polito.tdp.nyc.model;

public class CittaVicina {
	private City city;
	private double distanza;

	public CittaVicina(City city, double distanza) {
		super();
		this.city = city;
		this.distanza = distanza;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public double getDistanza() {
		return distanza;
	}

	public void setDistanza(double distanza) {
		this.distanza = distanza;
	}

}
