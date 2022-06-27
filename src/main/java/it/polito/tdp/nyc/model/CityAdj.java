package it.polito.tdp.nyc.model;

import com.javadocmd.simplelatlng.window.SortWrapper.DistanceComparator;

public class CityAdj {

	private City c1;
	private City c2;
	private double dist;

	public CityAdj(City c1, City c2, double dist) {
		super();
		this.c1 = c1;
		this.c2 = c2;
		this.dist = dist;
	}

	public City getC1() {
		return c1;
	}

	public void setC1(City c1) {
		this.c1 = c1;
	}

	public City getC2() {
		return c2;
	}

	public void setC2(City c2) {
		this.c2 = c2;
	}

	public double getDist() {
		return dist;
	}

	public void setDist(double dist) {
		this.dist = dist;
	}

}
