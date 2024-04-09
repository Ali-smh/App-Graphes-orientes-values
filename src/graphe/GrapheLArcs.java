package graphe;

import java.util.ArrayList;
import java.util.List;

import arcs.Arc;

public class GrapheLArcs extends Graphe{
	private ArrayList<String> sommets;
	private ArrayList<Arc> arcs;
	
	public GrapheLArcs() {
		sommets = new ArrayList<String>();
		arcs = new ArrayList<Arc>();
	}
	
	public GrapheLArcs(String str) {
		this();
		peupler(str);
	}

	@Override
	public void ajouterSommet(String noeud) {
		if(!contientSommet(noeud) && noeud != "")
			sommets.add(noeud);
	}

	@Override
	public void ajouterArc(String source, String destination, Integer valeur) {
		if(valeur < 0)
			throw new IllegalArgumentException("L'arc "+source+"-"+destination+" admet une valuation négative: "+valeur);
		if(this.contientArc(source, destination))
			throw new IllegalArgumentException("L'arc "+source+"-"+destination+" est déjà présent");
		Arc arc = new Arc(source, destination, valeur);
		arcs.add(arc);
		if (!this.contientSommet(source))
			ajouterSommet(source);
		if (!this.contientSommet(destination))
			ajouterSommet(destination);
	}

	@Override
	public void oterSommet(String noeud) {
		sommets.remove(noeud);
		for(int i = 0; i < arcs.size(); ++i) {
			if(arcs.get(i).getSource().equals(noeud) || arcs.get(i).getDestination().equals(noeud))
				arcs.remove(i);
		}
	}

	@Override
	public void oterArc(String source, String destination) {
		if(!this.contientArc(source, destination))
			throw new IllegalArgumentException("L'arc "+source+"-"+destination+ " n'est pas présent");
		for(int i = 0; i < arcs.size(); ++i) {
			if(arcs.get(i).getSource().equals(source) && arcs.get(i).getDestination().equals(destination)) {
				arcs.remove(i);
				break;
			}
		}
	}

	@Override
	public List<String> getSommets() {
		return sommets;
	}

	@Override
	public List<String> getSucc(String sommet) {
		ArrayList<String> successeurs = new ArrayList<String>();
		for(Arc arc : arcs)
			if (arc.getSource().equals(sommet))
				successeurs.add(arc.getDestination().toString());
		return successeurs;
	}

	@Override
	public int getValuation(String src, String dest) {
		if(contientArc(src, dest))
			for(int i = 0; i < arcs.size(); ++i)
				if(arcs.get(i).getSource().equals(src) && arcs.get(i).getDestination().equals(dest))
					return arcs.get(i).getValuation();
		return -1;
	}

	@Override
	public boolean contientSommet(String sommet) {
		return sommets.contains(sommet);
	}

	@Override
	public boolean contientArc(String src, String dest) {
		if(contientSommet(src) && contientSommet(dest))
			return getSucc(src).contains(dest);
		return false;
	}	

}
