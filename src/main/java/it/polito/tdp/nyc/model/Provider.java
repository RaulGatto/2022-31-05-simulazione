package it.polito.tdp.nyc.model;

import java.util.Objects;

public class Provider {

	private String provider;

	public Provider(String provider) {
		super();
		this.provider = provider;
	}

	public String getName() {
		return provider;
	}

	public void setName(String provider) {
		this.provider = provider;
	}

	@Override
	public int hashCode() {
		return Objects.hash(provider);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Provider other = (Provider) obj;
		return Objects.equals(provider, other.provider);
	}

	@Override
	public String toString() {
		return provider;
	}
	
	
}
