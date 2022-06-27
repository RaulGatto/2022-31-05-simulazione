package it.polito.tdp.nyc.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

import it.polito.tdp.nyc.model.CittaVicina;
import it.polito.tdp.nyc.model.City;
import it.polito.tdp.nyc.model.CityAdj;
import it.polito.tdp.nyc.model.Hotspot;
import it.polito.tdp.nyc.model.Provider;

public class NYCDao {
	
	public List<Hotspot> getAllHotspotProviderCity(Provider p, City city){
		String sql = "SELECT * FROM nyc_wifi_hotspot_locations where Provider = ? AND City = ?";
		List<Hotspot> result = new ArrayList<>();
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, p.getName());
			st.setString(2, city.getName());
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.add(new Hotspot(res.getInt("OBJECTID"), res.getString("Borough"),
						res.getString("Type"), res.getString("Provider"), res.getString("Name"),
						res.getString("Location"),res.getDouble("Latitude"),res.getDouble("Longitude"),
						res.getString("Location_T"),res.getString("City"),res.getString("SSID"),
						res.getString("SourceID"),res.getInt("BoroCode"),res.getString("BoroName"),
						res.getString("NTACode"), res.getString("NTAName"), res.getInt("Postcode")));
			}
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return result;
	}
	
	public List<Provider> getProviders(){
		
		final String sql = "SELECT distinct provider "
				+ "FROM nyc_wifi_hotspot_locations";
		
		List<Provider> result = new ArrayList<>();
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Provider provider = new Provider(res.getString("provider"));
				result.add(provider);
			}
			
			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}
		
	}
	
	public List<City> getCitiesByProvider(Provider p, Map<String, City> idMapCities){
		final String sql = "SELECT DISTINCT City, AVG(Latitude) AS lat, AVG(Longitude) AS longit "
				+ "FROM nyc_wifi_hotspot_locations "
				+ "WHERE provider = ? "
				+ "GROUP BY City";
		
		List<City> result = new ArrayList<>();
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, p.getName());
			ResultSet res = st.executeQuery();

			while (res.next()) {
				if(!idMapCities.containsKey(res.getString("City"))) {
					City city = new City(res.getString("City"), res.getDouble("lat"), res.getDouble("longit"));
					idMapCities.put(city.getName(), city);
					result.add(city);
				}else {
					City city = idMapCities.get(res.getString("City"));
					result.add(city);
				}
			}
			
			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}
	}
	
	public List<CityAdj> getAdiacenze(Provider p,  Map<String, City> idMapCities){
		final String sql = "SELECT n1.City as c1, n2.City as c2 "
				+ "FROM nyc_wifi_hotspot_locations n1, nyc_wifi_hotspot_locations n2 "
				+ "WHERE n1.Provider = ? AND n2.Provider = n1.Provider AND n1.OBJECTID > n2.OBJECTID AND n1.City != n2.City "
				+ "GROUP BY n1.City, n2.city";

		List<CityAdj> result = new ArrayList<>();
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, p.getName());
			ResultSet res = st.executeQuery();

			while (res.next()) {
				City c1 = idMapCities.get(res.getString("c1"));
				City c2 = idMapCities.get(res.getString("c2"));
				double dist = LatLngTool.distance(c1.getCoord(), c2.getCoord(), LengthUnit.KILOMETER);
				CityAdj adj = new CityAdj(c1, c2, dist);
				result.add(adj);
			}
			
			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}
		
		
	}
	
}
