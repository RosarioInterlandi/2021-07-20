package it.polito.tdp.yelp.model;

import java.util.HashMap;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.itunes.model.Album;
import it.polito.tdp.yelp.db.YelpDao;

public class Model {
	private YelpDao dao;
	private Map<String, User> userIdMap;
	private Graph<User, DefaultWeightedEdge> grafo;

	public Model() {
		this.dao = new YelpDao();
	}

	public void buildGraph(int anno, int nPrenotazioni) {
		this.grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);

		// Creazione vertici
		Graphs.addAllVertices(this.grafo, this.dao.getVertici(nPrenotazioni));
		// Popola idMap
		this.userIdMap = new HashMap<>();
		for (User u : this.grafo.vertexSet()) {
			this.userIdMap.put(u.getUserId(), u);
		}

		for (User u1 : this.grafo.vertexSet()) {
			for (User u2 : this.grafo.vertexSet()) {
				if (u1.compareTo(u2) > 0) {
					int similarita = this.dao.calcolaSimilarita(u1, u2, anno);
					if (similarita != 0) {
						Graphs.addEdge(this.grafo, u1, u2, similarita);
					}
				}
			}
		}
		System.out.println(this.grafo.vertexSet().size()+"-"+ this.grafo.edgeSet().size());

	}

}
