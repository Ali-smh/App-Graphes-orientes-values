package graphe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import arcs.Arc;

public class GrapheLAdj extends Graphe {
	private Map<String, List<Arc>> lAdj;
	
	public GrapheLAdj() {
		lAdj = new HashMap<String, List<Arc>>();
	}
	
	public GrapheLAdj(String str) {
		this();
		peupler(str);
	}


	@Override
	public List<String> getSommets() {
		return new ArrayList<String>(lAdj.keySet());
	}

	@Override
	public List<String> getSucc(String sommet) {
		ArrayList<String> succs = new ArrayList<String>();
		
		for (Arc arc: lAdj.get(sommet) ) {
			succs.add(arc.getDestination());
		}
							
		return succs;
	}

	@Override
	public int getValuation(String src, String dest) {
		if(this.contientArc(src, dest))
			for(Arc arc : lAdj.get(src))
				if(arc.getDestination().equals(dest))
					return arc.getValuation();
		return -1;
	}

	@Override
	public boolean contientSommet(String sommet) {
		return lAdj.containsKey(sommet);
	}

	@Override
	public boolean contientArc(String src, String dest) {
		if(this.contientSommet(src) && this.contientSommet(dest))
			return getSucc(src).contains(dest);
		return false;
	}

	@Override
	public void ajouterSommet(String noeud) {
		if(!this.contientSommet(noeud))
			lAdj.put(noeud, new ArrayList<Arc>());
	}

	@Override
	public void ajouterArc(String source, String destination, Integer valeur) {
		if(valeur < 0)
			throw new IllegalArgumentException("L'arc "+source+"-"+destination+" admet une valuation négative: "+valeur);
		if(this.contientArc(source, destination))
			throw new IllegalArgumentException("L'arc "+source+"-"+destination+" est déjà présent");
		
		if (!this.contientSommet(source)) {
	        this.ajouterSommet(source);
	    }
	    if (!this.contientSommet(destination)) {
	        this.ajouterSommet(destination);
	    }
		lAdj.get(source).add(new Arc(source, destination, valeur));
		
	}

	@Override
	public void oterSommet(String noeud) {
		if(this.contientSommet(noeud)) {
			lAdj.remove(noeud);
			
			for(String sommet : lAdj.keySet())
				for(Arc arc : lAdj.get(sommet))
					if(arc.getDestination().equals(sommet))
						lAdj.get(sommet).remove(arc);
		}

	}

	@Override
	public void oterArc(String source, String destination) {
		if(!this.contientArc(source, destination))
			throw new IllegalArgumentException("L'arc "+source+"-"+destination+ " n'est pas présent");
		
		for (Arc arc : lAdj.get(source))
			if(arc.getDestination().equals(destination))
				lAdj.get(source).remove(arc);
	}

}
