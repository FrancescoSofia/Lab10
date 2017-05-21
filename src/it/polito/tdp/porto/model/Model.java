package it.polito.tdp.porto.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import it.polito.tdp.porto.db.PortoDAO;

public class Model {
	
	
	private UndirectedGraph<Author, DefaultEdge> grafo  ;
	private AuthorIdMap authorIdMap ;
	private PaperIdMap paperIdMap ;
	private List<Author> autori ;
	private List<Paper> articoli;
	private Map<Author, Author> alberoVisita ;
	private PortoDAO dao;
	
	public Model() {
		this.authorIdMap = new AuthorIdMap();
		this.paperIdMap = new PaperIdMap();
		dao = new PortoDAO();
	}
	
	public List<Author> getAllAutori(){
		if(this.autori== null){
		this.autori = dao.getAllAutori(authorIdMap);
		}
		return this.autori;
	}

	public List<Paper> getAllArticoli(){
		if(this.articoli== null){
		this.articoli= dao.getAllArticoli(paperIdMap);
		}
		return this.articoli;
	}
	
	private UndirectedGraph<Author, DefaultEdge> getGrafo() {
		if(this.grafo==null) {
			this.provaCreaGrafo();
		}
		return this.grafo ;
	}
	
	public void provaCreaGrafo(){
		
		grafo = new SimpleGraph<Author, DefaultEdge>(DefaultEdge.class);
		//autori = this.getAllAutori();
		for(Author a: autori){
			grafo.addVertex(a);
			a.getArticoli().addAll(dao.getArticoliAutore(a.getId(), paperIdMap));
		}
		articoli = this.getAllArticoli();
		for(Paper p: articoli){
			p.getAutori().addAll(dao.getAutoriArticolo(p.getEprintid(), authorIdMap));
		}
		
		for(Author a: grafo.vertexSet()){
			for(Paper p:a.getArticoli()){
				for(Author b :p.getAutori()){
					if(grafo.getEdge(a, b)==null && !a.equals(b)){
						grafo.addEdge(a, b);
					}
				}
			}
		}
	
	}
	
	public List<Author> trovaCoAutori(Author autore){
		this.getGrafo();
		boolean flag= false;
		List<Author> coAutori = new ArrayList<Author>();
		for(Author vertice : grafo.vertexSet()){
			if(vertice.equals(autore) && flag == false){
				flag = true;
		for(DefaultEdge edge : grafo.edgesOf(vertice)){
		coAutori.add(Graphs.getOppositeVertex(grafo,edge,autore));//aggiunge i vertici del grafo collegati al vertice selzionato
		}}}
		return  coAutori;
		
	}
	
  public List<Paper> trovaSequenza(Author autore1,Author autore2){
	
	DijkstraShortestPath<Author,DefaultEdge> dsp = new DijkstraShortestPath<Author,DefaultEdge>(grafo,autore1,autore2);
	List<DefaultEdge> archi = new ArrayList<DefaultEdge>(dsp.findPathBetween(grafo, autore1, autore2));
	List<Paper> artemp = new ArrayList<Paper>();
	boolean flag = false;
	
	for(DefaultEdge arco : archi){
		flag = false;
		for(Paper p1 :grafo.getEdgeSource(arco).getArticoli()){
			for(Paper p2:grafo.getEdgeTarget(arco).getArticoli()){
				if(p1.equals(p2) && flag == false){
					artemp.add(p1);
					flag = true;
				}
			}
		}
	}
	return artemp;
	
  }
}
