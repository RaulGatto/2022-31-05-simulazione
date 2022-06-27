package it.polito.tdp.nyc.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.nyc.db.NYCDao;

public class Model {
	
	private NYCDao dao;
	private Graph<City, DefaultWeightedEdge> graph;
	private Map<String, City> idMapCities;
	private List<City> vertici;
	
	public Model() {
		dao = new NYCDao();
		idMapCities = new HashMap<>();
	}

	public List<Provider> getProviders() {
		// TODO Auto-generated method stub
		return this.dao.getProviders();
	}

	public List<City> getVertici() {
		return vertici;
	}

	public String creaGrafo(Provider p) {
		this.graph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		this.vertici = this.dao.getCitiesByProvider(p, idMapCities);
		
		Graphs.addAllVertices(this.graph, vertici);
		
		for(CityAdj adj : this.dao.getAdiacenze(p, idMapCities)) {
			if(!this.graph.containsEdge(adj.getC1(), adj.getC2())){
				Graphs.addEdge(this.graph, adj.getC1(), adj.getC2(), adj.getDist());
			}
		}
		
		return String.format("E' stato creato un grafo con %d vertici e %d archi", this.graph.vertexSet().size(), this.graph.edgeSet().size());
	}

	public List<CittaVicina> quartieriAdiacenti(City city) {
		
		List<City> citta = new ArrayList<>(Graphs.neighborListOf(this.graph, city));
		List<CittaVicina> result = new ArrayList<>();
		
		for(City c : citta) {
			result.add(new CittaVicina(c, this.graph.getEdgeWeight(this.graph.getEdge(city, c))));
		}
		Collections.sort(result, new Comparator<CittaVicina>(){

			@Override
			public int compare(CittaVicina o1, CittaVicina o2) {
				// TODO Auto-generated method stub
				return Double.compare(o1.getDistanza(), o2.getDistanza());
			}
			
		});
		return result;
	
	}
	
	
}
